package com.example.jcompose

import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcompose.ui.theme.JComposeTheme
import com.example.jcompose.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JComposeTheme() {
                Greeting(name = "Anderea")
                // A surface container using the 'background' color from the theme

            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    Surface(color=MaterialTheme.colors.primary){
        Column (){
            Text(text = "Hello !$name",
                modifier = Modifier.padding(24.dp)

                //    color = Color.Red
            )

        }

    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JComposeTheme {
       Column (modifier = Modifier.fillMaxSize()){
           Surface(modifier = Modifier
               .width(200.dp)
               .height(80.dp),
               color = MaterialTheme.colors.primary ) {
                   
                }

       }

    }

    @Composable
    fun MyApp(
        modifier: Modifier=Modifier,
        names: List<String> = listOf("World","Jiko")
    ){
    Column(modifier) {
        for (name in names)
            Greeting(name = name)
    }

    }
}