package com.example.jcompose

import android.os.Bundle
import androidx.compose.material.TopAppBar
import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.Color
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcompose.ui.theme.JComposeTheme

class TwoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        MyAppComponents()

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppComponents() {
    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "My App") },
                    navigationIcon = {
                        IconButton(onClick = { /* Handle menu icon click */ }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu Icon")
                        }
                    },
                    actions = {

                       IconButton(onClick = { /*TODO*/ }) {
                           Icon(Icons.Filled.Search, contentDescription = "Sch")
                       }
                        IconButton(onClick = { /*TODO*/ }) {

                            Icon(Icons.Filled.Share, contentDescription = "Share")                       }
                    },
                    backgroundColor = MaterialTheme.colors.primaryVariant
                )
            },
            content = { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    Text(
                        text = "Working on SocialM",
                        modifier = Modifier.padding(16.dp), // Add padding for better layout
                        style = MaterialTheme.typography.h6 // Optional text style
                    )
                }
            }
        )
    }
}




@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    MyAppComponents()
}