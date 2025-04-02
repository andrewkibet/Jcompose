package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class FomikoUi : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                KunafomScreen()
            }
        }
    }
}

@Composable
fun KunafomScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Home", "Events", "Buzz", "Notifications", "Chats", "Account")

    Scaffold(
        topBar = {
            Column {
                TopAppBar()
                TabsHeader(selectedTab, tabs) { selectedTab = it }
            }
        },
        floatingActionButton = { FloatingActionButton() }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedTab) {
                2 -> BuzzContent()
                // Add other cases for different tabs
                else -> Text("Tab Content ${tabs[selectedTab]}")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    SmallTopAppBar(
        title = {
            Text(
                text = "Kunafom",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
            IconButton(onClick = {}) {
                Icon(Icons.Default.Receipt, contentDescription = "Tickets")
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
            actionIconContentColor = Color.Black
        )
    )
}

@Composable
fun TabsHeader(selectedTab: Int, tabs: List<String>, onTabSelected: (Int) -> Unit) {
    ScrollableTabRow(
        selectedTabIndex = selectedTab,
        edgePadding = 16.dp,
        divider = {},
        indicator = {}
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                text = {
                    Text(
                        text = title,
                        style = if (selectedTab == index) MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        ) else MaterialTheme.typography.labelMedium
                    )
                }
            )
        }
    }
}

@Composable
fun BuzzContent() {
    val posts = listOf(
        Post(
            content = "Hello we just sealed a deal for new partnership\nMost Kenyans are recently ......",
            author = "Collince Omondi Mito",
            date = "3rd Jan 2025"
        ),
        Post(
            content = "Kunafom is undeniably leading...\n#EASTER grab holiday deals",
            author = "Admin",
            date = "2nd Apr 2024"
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Buzz",
                    style = MaterialTheme.typography.headlineMedium,
                    fontSize = 24.sp
                )
                Text(
                    "Explore >",
                    color = Color.Blue,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        items(posts) { post ->
            PostItem(post)
            Divider(color = Color.LightGray, thickness = 0.8.dp)
        }
    }
}

@Composable
fun PostItem(post: Post) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = post.content,
            style = MaterialTheme.typography.bodyLarge,
            lineHeight = 22.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = post.author,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = post.date,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun FloatingActionButton() {
    FloatingActionButton(
        onClick = { /* Handle FAB click */ },
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(Icons.Default.Add, contentDescription = "Add New Post")
    }
}

data class Post(
    val content: String,
    val author: String,
    val date: String
)