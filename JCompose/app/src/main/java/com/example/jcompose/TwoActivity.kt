package com.example.jcompose

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.Color
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jcompose.ui.theme.JComposeTheme

class TwoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        MyAppComponents()

        }
    }
}

@Composable
fun MyAppComponents() {
    MaterialTheme {
        Surface (modifier = Modifier.fillMaxSize(),
           // color = MaterialTheme.colors.background
        )
        {
Text(text = "Andrew")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyAppComponents()
}