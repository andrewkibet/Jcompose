package com.example.jcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcompose.ui.theme.JComposeTheme

class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JComposeTheme {
                NameListScreen()
            }
        }
    }
}

@Composable
fun NameListScreen() {
    val names = List(20) { "Name ${it + 1}" } // Generates 20 names
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(names) { name ->
                Greeting2(name = name)
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello, $name", modifier = modifier.padding(16.dp))
    Text(text = "Hello, $name", modifier = modifier.padding(16.dp))
    Text(text = "Hello, $name", modifier = modifier.padding(16.dp))

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    JComposeTheme {
        NameListScreen()
    }
}
