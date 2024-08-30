package com.example.jcompose

import MyNotificationListenerService
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.jcompose.ui.theme.JComposeTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(viewModel = viewModel)
        }
    }
}

fun getInstalledSocialMediaApps(context: Context): List<ResolveInfo> {
    val packageManager = context.packageManager
    val intent = Intent(Intent.ACTION_MAIN, null).apply {
        addCategory(Intent.CATEGORY_LAUNCHER)
    }
    val allApps = packageManager.queryIntentActivities(intent, 0)
    return allApps.filter { app ->
        val packageName = app.activityInfo.packageName
        packageName.contains("facebook") ||
                packageName.contains("twitter") ||
                packageName.contains("whatsapp") ||

                packageName.contains("instagram") ||
                packageName.contains("linkedin") // Add other social media apps here
    }
}

@Composable
fun SocialMediaAppList(apps: List<ResolveInfo>, notificationCounts: Map<String, Int>, context: Context) {
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
fun SocialMediaAppItem(appName: String, icon: Drawable, notificationCount: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            bitmap = icon.toBitmap().asImageBitmap(),
            contentDescription = appName,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = appName, style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.weight(1f))
        if (notificationCount > 0) {
            Text(text = notificationCount.toString(), style = MaterialTheme.typography.body2, color = Color.Red)
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val context = LocalContext.current
    val apps = getInstalledSocialMediaApps(context)
    val notificationCounts by viewModel.notificationCounts.collectAsState()

    SocialMediaAppList(apps, notificationCounts, context)
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

