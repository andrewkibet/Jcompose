package com.example.jcompose

import android.content.Intent
import android.os.Bundle
import androidx.compose.material.TopAppBar
import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.Color
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcompose.ui.theme.JComposeTheme
import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.pager.ExperimentalPagerApi
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

    // Properly initialize the expanded state using remember and mutableStateOf
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState() // Control the scaffold's drawer state
    val pagerState = rememberPagerState(pageCount = { HomeTabs.entries.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }


    MaterialTheme {
        Scaffold(
            scaffoldState = scaffoldState,  // Pass scaffold state to control drawer
            topBar = {
                TopAppBar(
                    title = { Text(text = "My App") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open() // Open the drawer when menu is clicked
                            }
                        }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu Icon")
                        }
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Filled.Search, contentDescription = "Sch")
                        }
                        IconButton(onClick = { /*TODO*/ }) {

                            Icon(Icons.Filled.Share, contentDescription = "Share")
                        }
                        IconButton(onClick = { expanded = true }) {
                            Icon(Icons.Filled.MoreVert,contentDescription ="Overflow" )
                        }
                        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(onClick = {
                                expanded = false
                                val intent = Intent(context,ThirdActivity::class.java)
                                context.startActivity(intent)
                            })
                            {
                                Text(text = "Settings")

                            }
                            DropdownMenuItem(onClick = { expanded = false })
                            {
                                Text(text = "Privacy")

                            }

                        }
                    },
                    backgroundColor = MaterialTheme.colors.secondary
                )
            },
            bottomBar = {
                BottomAppBar(
                    backgroundColor = MaterialTheme.colors.secondary,
                    content = {
                        IconButton(onClick = { /*TODO*/
                            val intent = Intent(context,ThirdActivity::class.java)
                            context.startActivity(intent)
                        }) {
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
                    val intent = Intent(context, ThirdActivity::class.java)
                    context.startActivity(intent)
                }) {
                    Icon(Icons.Filled.Add, contentDescription = "FAB Icon")
                }
            },
            drawerContent = {
                // Drawer content goes here
                Text(text = "Drawer Item 1", modifier = Modifier.padding(16.dp))
                Text(text = "Drawer Item 2", modifier = Modifier.padding(16.dp))
                Text(text = "Drawer Item 3", modifier = Modifier.padding(16.dp))
                Divider() // Optional: Add a divider for better UI
                Text(text = "Drawer Item 4", modifier = Modifier.padding(16.dp))
            },
            content = { innerPadding ->
                Box(modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()) {
                    Text(
                        text ="""
                            Working on SocialM
                                I have wrapped my drawer content in Scaffold
                                """.trimIndent(),
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        )
    }

    //0762738873- Mama Joy
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
