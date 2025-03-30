@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.Settings
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class TwoActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        if (!hasNotificationListenerAccess(this)) {
//            // Request notification access if not granted
//            openNotificationSettings()
//        } else {
//            // Start the notification listener service
//            startService(Intent(this, MyNotificationListenerService::class.java))
//        }

        setContent {
            MainScreen(viewModel = viewModel)
        }
    }

    private fun openNotificationSettings() {
        val intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
//
//    private fun hasNotificationListenerAccess(context: Context): Boolean {
//        val packageName = context.packageName
//        val enabledListeners = Settings.Secure.getString(
//            context.contentResolver,
//            Settings.Secure.ENABLED_NOTIFICATION_LISTENERS
//        )
//        return enabledListeners.split(":").contains(packageName)
//    }
//}

    class MyNotificationListenerService : NotificationListenerService() {
        private val _notificationCounts = MutableStateFlow<Map<String, Int>>(emptyMap())
        val notificationCounts: StateFlow<Map<String, Int>> = _notificationCounts

        override fun onNotificationPosted(sbn: StatusBarNotification) {
            val packageName = sbn.packageName
            val currentCounts = _notificationCounts.value
            val newCount = currentCounts[packageName]?.plus(1) ?: 1
            _notificationCounts.value = currentCounts + (packageName to newCount)
        }

        override fun onNotificationRemoved(sbn: StatusBarNotification) {
            val packageName = sbn.packageName
            val currentCounts = _notificationCounts.value
            val newCount = currentCounts[packageName]?.minus(1) ?: 0
            if (newCount > 0) {
                _notificationCounts.value = currentCounts + (packageName to newCount)
            } else {
                _notificationCounts.value = currentCounts - packageName
            }
        }
    }

    class MainViewModel(application: Application) : AndroidViewModel(application) {
        private val _notificationCounts = MutableStateFlow<Map<String, Int>>(emptyMap())
        val notificationCounts: StateFlow<Map<String, Int>> = _notificationCounts

        private val listenerService = MyNotificationListenerService()

        init {
            viewModelScope.launch {
                listenerService.notificationCounts.collectLatest { counts ->
                    _notificationCounts.value = counts
                }
            }
        }
    }

    @Composable
    fun SocialMediaAppList(
        apps: List<ResolveInfo>,
        notificationCounts: Map<String, Int>,
        context: Context
    ) {
        LazyColumn {
            items(apps) { app ->
                val packageName = app.activityInfo.packageName
                val appName = app.loadLabel(context.packageManager).toString()
                val icon = app.loadIcon(context.packageManager)
                val notificationCount = notificationCounts[packageName] ?: 0

                SocialMediaAppItem(appName, icon, notificationCount) {
                    val launchIntent = context.packageManager.getLaunchIntentForPackage(packageName)
                    launchIntent?.let { context.startActivity(it) }
                }
            }
        }
    }

    @Composable
    fun SocialMediaAppItem(
        appName: String,
        icon: Drawable,
        notificationCount: Int,
        onClick: () -> Unit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                //.background(black)
                .offset(12.dp,12.dp) // I addedthis but Ihave nottested.
                .clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                bitmap = icon.toBitmap().asImageBitmap(),
                contentDescription = appName,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = appName,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Next",
                style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.weight(1f))
            if (notificationCount >= 0) {
                Text(
                    text = notificationCount.toString(),
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Red),
                    modifier = Modifier
                        .background(Color.Gray, shape = CircleShape)
                        .padding(horizontal = 9.dp, vertical = 4.dp)
                )
            }
        }
    }

    @Composable
    fun MainScreen(viewModel: MainViewModel) {
        val context = LocalContext.current
        val specifiedAppPackages = listOf(
            "com.facebook.katana", // Facebook
            "com.instagram.android", // Instagram
            "com.twitter.android", // Twitter
            "com.whatsapp" // WhatsApp
        )
        val apps = getSpecifiedApps(context, specifiedAppPackages)
        val notificationCounts by viewModel.notificationCounts.collectAsState()

        // Remember the scaffold state (to control the drawer's open/close state)
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)// Use this instead

        // Coroutine scope to launch suspend functions
        val scope = rememberCoroutineScope()

        // Scaffold containing the TopAppBar, Drawer, and Main Content

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(MaterialTheme.colorScheme.surface)
                )
                {
                    DrawerItem(icon = Icons.Filled.Menu, label = "Home")
                }
            }
        ) { }

        Scaffold(
          // Make sure to pass scaffoldState here
            topBar = {
                TopAppBar(
                    title = { Text(text = "Social Media Apps") },
                    navigationIcon = {
                        IconButton(onClick = {
                            // Launch a coroutine to open the drawer
                            scope.launch {
                                drawerState.open() // Open the drawer when menu is clicked
                            }
                        }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            content = { paddingValues ->
                // Main Content inside Scaffold
                Column(
                    modifier = Modifier
                        .fillMaxSize() // Ensure it takes up the full screen size
                        .padding(paddingValues) // Padding added by Scaffold
                ) {
                    SocialMediaAppList(apps = apps, notificationCounts = notificationCounts, context = context)
                }
            }
        )
    }



    @Composable
    fun DrawerItem(icon: ImageVector, label: String) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp), // Add padding around each drawer item
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier.size(24.dp),
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(16.dp)) // Space between icon and text
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge // Use Material theme typography for better text styling
            )
        }
    }

    fun getSpecifiedApps(context: Context, specifiedAppPackages: List<String>): List<ResolveInfo> {
        val packageManager = context.packageManager
        val apps = mutableListOf<ResolveInfo>()

        specifiedAppPackages.forEach { packageName ->
            try {
                val intent = packageManager.getLaunchIntentForPackage(packageName)
                intent?.let {
                    val resolveInfo =
                        packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
                    resolveInfo?.let { apps.add(it) }
                }
            } catch (e: PackageManager.NameNotFoundException) {
                // Handle the case where the app is not found (optional)
            }
        }

        return apps
    }
}
