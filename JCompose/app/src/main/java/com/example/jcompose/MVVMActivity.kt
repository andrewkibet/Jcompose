package com.example.jcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcompose.ui.theme.JComposeTheme

class MVVMActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TaskApp()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun TaskApp(viewModel: TaskViewModel) {
    var newTaskName by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = newTaskName,
            onValueChange = { newTaskName = it },
            label = { Text("New Task") }
        )

        Button(onClick = {
            if (newTaskName.isNotBlank()) {
                viewModel.addTask(newTaskName)
                newTaskName = ""
            }
        }) {
            Text("Add Task")
        }

        LazyColumn {
            items(viewModel.tasks.value) { task ->
                Text(text = task.name)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    JComposeTheme {
        Greeting("Android")
    }
}