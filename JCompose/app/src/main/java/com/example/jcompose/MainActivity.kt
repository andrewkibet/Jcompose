package com.example.jcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcompose.ui.theme.JComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JComposeTheme {
                val names = listOf("Alice", "Bob", "Charlie", "David", "Eve", "Frank")
                NameList(names = names)
            }
        }
    }
}

@Composable
fun ListItem(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.h6,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            
    )
}

@Composable
fun NameList(names: List<String>) {
    LazyColumn {
        items(names) { name ->
            ListItem(name = name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JComposeTheme {
        val names = listOf("Alice", "Bob", "Charlie", "David", "Eve", "Frank")
        NameList(names = names)
    }
}
