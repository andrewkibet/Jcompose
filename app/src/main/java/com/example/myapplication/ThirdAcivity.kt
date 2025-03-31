package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*




class ThirdAcivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThirdComponents()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ThirdComponents() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    val pagerState = rememberPagerState { 4 }
    var tabTitles = listOf("Chats","Updates","Communities","Calls")
    var selectedTabIndex by remember { mutableStateOf(0) }



    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp)
                    .width(280.dp)

            ) {
                Column(modifier = Modifier
                    .fillMaxSize()


                ) {
                    // Add profile image
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            //    .background(MaterialTheme.colors.primary) // Background for account section
                            .padding(16.dp)
                    ){ Column() {
                        Icon(
                            imageVector = Icons.Default.AccountBox,
                            contentDescription = "Profile Image",
                            modifier = Modifier
                                .size(64.dp)
                                .padding(bottom = 8.dp)

                        )

                        // User details
                        Text(text = "John Doe", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "johndoe@gmail.com", style = MaterialTheme.typography.bodySmall)
                    }

                    }

                    Divider(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)


                    )

                    // Drawer menu items
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            Icons.Filled.Email,
                            contentDescription = "Rate",
                            modifier= Modifier
                                .padding(end = 8.dp)
                                .size(40.dp)


                        )

                        Text(text = "Email",
                            style = MaterialTheme.typography.bodySmall, fontSize = 22.sp

                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            Icons.Filled.Refresh,
                            contentDescription = "Refresh",
                            modifier= Modifier
                                .padding(end = 8.dp)
                                .size(40.dp)


                        )

                        Text(text = "Refresh",
                            style = MaterialTheme.typography.bodySmall, fontSize = 22.sp

                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            Icons.Filled.Star,
                            contentDescription = "Rate",
                            modifier= Modifier
                                .padding(end = 8.dp)
                                .size(40.dp)


                        )

                        Text(text = "Favourite",
                            style = MaterialTheme.typography.bodySmall, fontSize = 22.sp

                        )
                    }

                    Divider(
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "Modal Drawer") },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.open()  // Open drawer here
                                }
                            }) {
                                Icon(Icons.Filled.Menu, contentDescription = "Menu Icon")
                            }
                        },
                        actions = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(Icons.Filled.Search, contentDescription = "Search")
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(Icons.Filled.Share, contentDescription = "Share")
                            }
                            IconButton(onClick = { expanded = true }) {
                                Icon(Icons.Filled.MoreVert, contentDescription = "Overflow")
                            }
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }) {
                                DropdownMenuItem(onClick = { expanded = false },
                                    text = { Text("Settings") })

                                DropdownMenuItem(
                                    text = { Text("Privacy") },
                                    onClick = { expanded = false }
                                )
                            }
                        },

                    )
                },
                bottomBar = {
                    BottomAppBar(
                        //backgroundColor = MaterialTheme.colors.primarySurface,
                        content = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(Icons.Filled.Call, contentDescription = "Call")
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(Icons.Filled.Email, contentDescription = "Email")
                            }
                        }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        val intent = Intent(context, ThirdAcivity::class.java)
                        context.startActivity(intent)
                    }) {
                        Icon(Icons.Filled.Add, contentDescription = "FAB Icon")
                    }
                },
                content = { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        TabRow(
                            selectedTabIndex = selectedTabIndex,
                           // backgroundColor = MaterialTheme.colors.primarySurface,
                            contentColor = Color.White,

                            ) {
                            tabTitles.forEachIndexed { index, title ->
                                Tab(
                                    selected = selectedTabIndex == index,
                                    onClick = {
                                        scope.launch {
                                            pagerState.scrollToPage(index)
                                            selectedTabIndex = index
                                        }
                                    },
                                    modifier = Modifier
                                        .background(
                                            if(selectedTabIndex==index) Color.Green else Color.Transparent
                                        )){

                                    Text(
                                        text = title,
                                        color = if (selectedTabIndex == index) Color.Black else Color.White)
                                }



                            }
                        }

                        // HorizontalPager for swiping between tabs
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.fillMaxSize()
                        ) { page ->
                            selectedTabIndex = page
                            when (page) {
                                0 -> PageScreen("Chats", Color.LightGray)
                                1 -> PageScreen("Updates", Color.Cyan)
                                2 -> PageScreen("Communities", Color.Green)
                                3 -> PageScreen("Calls", Color.Magenta)
                            }
                        }
                    }
                }
            )
        }
    )
}


@Composable
fun PageScreen(pageText:String,backgroundColor: Color){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ){
        Text(
            text = pageText,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  ThirdComponents()
}