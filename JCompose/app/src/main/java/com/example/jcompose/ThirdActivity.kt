package com.example.jcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Divider
import androidx.compose.material.DrawerValue
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalDrawer
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcompose.ui.theme.JComposeTheme
import kotlinx.coroutines.launch
import androidx.compose.material.*
import androidx.compose.material.icons.filled.AccountBox


class ThirdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
         ThirdComponents()
        }
    }
}

@Composable
fun ThirdComponents(){
    // Properly initialize the expanded state using remember and mutableStateOf
    val scaffoldState = rememberScaffoldState()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {

                // Add profile image (use a placeholder here)
                Icon(
                    imageVector = Icons.Default.AccountBox, // Use an appropriate image vector for the profile
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(64.dp)
                        .padding(bottom = 8.dp)
                )

                // User name or account info
                Text(text = "John Doe", style = MaterialTheme.typography.h6)
                Text(text = "johndoe@gmail.com", style = MaterialTheme.typography.body2)
            }

            Divider(modifier = Modifier.padding(vertical = 8.dp))
        },
        content = {
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
                        val intent = Intent(context, MainActivity::class.java)
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
                            text = "Working on SocialM",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            )
        }
    )
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
   ThirdComponents()
}