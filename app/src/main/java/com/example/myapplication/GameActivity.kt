package com.example.myapplication

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


import android.os.Bundle

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TapGameScreen()
        }
    }
}

@Composable
fun TapGameScreen() {
    var score by remember { mutableStateOf(0) }
    var timeLeft by remember { mutableStateOf(10) }
    var isGameRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isGameRunning) {
        if (isGameRunning) {
            while (timeLeft > 0) {
                delay(2000)
                timeLeft--
            }
            isGameRunning = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Time Left: $timeLeft s", style = MaterialTheme.typography.headlineMedium)
        Text("Score: $score", style = MaterialTheme.typography.headlineLarge)

        Button(
            onClick = {
                if (!isGameRunning) {
                    score = 0
                    timeLeft = 10
                    isGameRunning = true
                } else {
                    score++
                }
            },
            enabled = timeLeft > 0
        ) {
            Text(if (isGameRunning) "Tap Me!" else "Start Game")
        }
    }
}
