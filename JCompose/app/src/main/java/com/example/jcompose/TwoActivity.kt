package com.example.jcompose

import android.content.Intent
import android.os.Bundle
import androidx.compose.material.*
import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.Color
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcompose.ui.theme.JComposeTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class TwoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppComponents()
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MyAppComponents() {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val pagerState = rememberPagerState(initialPage = 0) // Updated method
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

    MaterialTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = { Text(text = "My App") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
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
                        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                            DropdownMenuItem(onClick = {
                                expanded = false
                                val intent = Intent(context, ThirdActivity::class.java)
                                context.startActivity(intent)
                            }) {
                                Text(text = "Settings")
                            }
                            DropdownMenuItem(onClick = { expanded = false }) {
                                Text(text = "Privacy")
                            }
                        }
                    },
                    backgroundColor = MaterialTheme.colorScheme.secondary
                )
            },
            bottomBar = {
//                BottomAppBar(
//                    backgroundColor = MaterialTheme.colorScheme.secondary,
//                    content = {
//                        IconButton(onClick = {
//                            val intent = Intent(context, ThirdActivity::class.java)
//                            context.startActivity(intent)
//                        }) {
//                            Icon(Icons.Filled.Call, contentDescription = "Call")
//                        }
//                        IconButton(onClick = { /*TODO*/ }) {
//                            Icon(Icons.Filled.Email, contentDescription = "Email")
//                        }
//                    }
//                )


                BottomNavigation(
                    backgroundColor = MaterialTheme.colorScheme.inverseOnSurface,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    HomeTabs.entries.forEachIndexed { index, currentTab ->
                        BottomNavigationItem(
                            selected = selectedTabIndex.value == index,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (selectedTabIndex.value == index)
                                        currentTab.selectedIcon else currentTab.unselectedIcon,
                                    contentDescription = "Bottom Tab Icon"
                                )
                            },
                            label = { Text(text = currentTab.text) },
                            selectedContentColor = MaterialTheme.colorScheme.inversePrimary,
                            unselectedContentColor = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }




            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    val intent = Intent(context, ThirdActivity::class.java)
                    context.startActivity(intent)
                }) {
                    Icon(Icons.Filled.Add, contentDescription = "FAB Icon")
                }
            },
            drawerContent = {
                Text(text = "Drawer Item 1", modifier = Modifier.padding(16.dp))
                Text(text = "Drawer Item 2", modifier = Modifier.padding(16.dp))
                Text(text = "Drawer Item 3", modifier = Modifier.padding(16.dp))
                Divider()
                Text(text = "Drawer Item 4", modifier = Modifier.padding(16.dp))
            },
            content = { innerPadding ->
                Column(modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()) {


                    HorizontalPager(
                        state = pagerState,
                        count = HomeTabs.entries.size, // Updated page count
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = HomeTabs.entries[selectedTabIndex.value].text)
                        }
                    }
                }
            }
        )
    }
}


enum class HomeTabs(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val text: String
) {
    Shop(
        unselectedIcon = Icons.Outlined.ShoppingCart,
        selectedIcon = Icons.Filled.ShoppingCart,
        text = "Shop"
    ),
    Favourite(
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        selectedIcon = Icons.Filled.Favorite,
        text = "Favourite"
    ),
    Profile(
        unselectedIcon = Icons.Outlined.Person,
        selectedIcon = Icons.Filled.Person,
        text = "Profile"
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    MyAppComponents()
}
