package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
//import androidx.compose.material3.rememberBottomsheetScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewmodel.compose.viewModel


// ViewModel definition
class ProductViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<String>>(emptyList())
    val products: StateFlow<List<String>> = _products

    init {
        loadProducts()
    }

    private fun loadProducts() {
        _products.value = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
    }
}

class MainActivity : ComponentActivity() {
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
//val productViewModel: ProductViewModel = viewModel() // Initialize ViewModel
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val productViewModel: ProductViewModel = viewModel()



    Scaffold(
//  scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("My App") },
                navigationIcon = @androidx.compose.runtime.Composable {
                    IconButton(onClick = {expanded = true
                    })
                    {
                        Icon(Icons.Default.Menu, contentDescription = "Menu Icon")
                    }
                    Icon(Icons.Default.MoreVert, contentDescription = " Options")
                },



actions = {
    IconButton(onClick = { /*TODO*/ }) {
        Icon(Icons.Default.Search, contentDescription = "Search")
    }
    IconButton(onClick = { /*TODO*/ }) {
        Icon(Icons.Default.Share, contentDescription = "Share")
    }
    IconButton(onClick = { expanded = true}) {

    }
    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
        DropdownMenuItem(text = {Text ("Settings")}, onClick = {
            expanded = false
            val intent = Intent(context, TwoActivity::class.java)
            context.startActivity(intent)
        })
        DropdownMenuItem( text = {Text ("Privacy")}, onClick = { expanded = false })


    }
})

          },
    bottomBar = {
        BottomAppBar(
            content = {
                IconButton(onClick = { /*TODO*/
                    val intent= Intent(context, MathGame::class.java)
                    context.startActivity(intent)
                })
                {
                    Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                }
                Spacer(modifier = Modifier.weight(1f, true))
                IconButton(onClick = {
                    val intent = Intent(context, GameActivity::class.java)
                    context.startActivity(intent)
                }) {
                    Icon(Icons.Default.Call, contentDescription = "Call")
                }
            }
        )
    },

    floatingActionButton = {
        FloatingActionButton(onClick = {
            val intent = Intent(context, ThirdAcivity::class.java)
            context.startActivity(intent)
        }) {
            Icon(Icons.Default.Add, contentDescription = "Add FAB")
        }
    },
        content = { innerPadding ->
            ShopTab(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                productViewModel = productViewModel
            )
        }
    )
}

@Composable
fun ShopTab(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
    val shopItems by productViewModel.products.collectAsState()

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(shopItems) { item ->
            Text(text = item, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyAppComponents() {
    MyAppComponents()
}