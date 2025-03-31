package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MathGame : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameScreen()
        }
    }
}

@Composable
fun GameScreen() {
    var numbers by remember { mutableStateOf(generateNumbers()) }
    var selectedNumbers by remember { mutableStateOf(listOf<Int>()) }
    var score by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Score: $score", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))

        Grid(numbers, selectedNumbers) { number, index ->
            if (selectedNumbers.size < 2) {
                selectedNumbers = selectedNumbers + index
            }

            if (selectedNumbers.size == 2) {
                val sum = selectedNumbers.sumOf { numbers[it] }
                if (sum == 10) {
                    numbers = numbers.toMutableList().apply {
                        selectedNumbers.sortedDescending().forEach { removeAt(it) }
                        addAll(generateNumbers(selectedNumbers.size))
                    }
                    score += 10
                }
                selectedNumbers = listOf()
            }
        }
    }
}

@Composable
fun Grid(numbers: List<Int>, selectedNumbers: List<Int>, onNumberClick: (Int, Int) -> Unit) {
    Column {
        for (i in numbers.indices step 4) {
            Row {
                for (j in i until (i + 4).coerceAtMost(numbers.size)) {
                    val isSelected = j in selectedNumbers
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(if (isSelected) Color.Red else Color.Blue, RoundedCornerShape(8.dp))
                            .clickable { onNumberClick(numbers[j], j) }
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = numbers[j].toString(), color = Color.White, fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

fun generateNumbers(count: Int = 16): List<Int> {
    return List(count) { Random.nextInt(1, 9) }
}
