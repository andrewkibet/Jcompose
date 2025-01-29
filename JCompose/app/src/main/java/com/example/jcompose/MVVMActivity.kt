package com.example.jcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.jcompose.view.HomePage
import com.example.jcompose.viewmodel.HomeViewModel
import com.example.jcompose.ui.theme.JComposeTheme

class MVVMActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Correct ViewModel instantiation
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        setContent {
            JComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomePage(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = homeViewModel
                    )
                }
            }
        }
    }
}

// Preview function for UI testing
@Preview(showBackground = true)
@Composable
fun PreviewHomePage() {
    val homeViewModel = HomeViewModel()
    HomePage(viewModel = homeViewModel)
}