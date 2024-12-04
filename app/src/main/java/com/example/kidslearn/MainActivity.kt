package com.example.kidslearn

import LittleLearnersScreen
import ParentingTipScreen
import ProgressViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.kidslearn.ui.theme.KidsLearnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KidsLearnTheme {
                MainApp()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") {
                LittleLearnersScreen(
                    onNavigateToProgress = { navController.navigate("progress") },
                )
            }
            composable("progress") {
                ProgressScreen(
                    onNavigateBack = { navController.popBackStack() },
                )
            }
            composable("tips") {
                ParentingTipScreen(context = LocalContext.current)            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(containerColor = Color(0xFFFFE4B5)) {
        NavigationBarItem(
            selected = currentDestination == "home",
            onClick = { navController.navigate("home") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = currentDestination == "progress",
            onClick = { navController.navigate("progress") },
            icon = { Icon(Icons.Default.Palette, contentDescription = "Progress") },
            label = { Text("Progress") }
        )
        NavigationBarItem(
            selected = currentDestination == "tips",
            onClick = { navController.navigate("tips") },
            icon = { Icon(Icons.Default.MenuBook, contentDescription = "Parenting Tips") },
            label = { Text("Tips") }
        )
    }
}

@Composable
fun Content(modifier: Modifier = Modifier, onNavigateToProgress: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFA500), Color(0xFFFFD700))
                )
            )
            .padding(16.dp)
    ) {
        // "This Week's Adventures" Section
        Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE4B5))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                SectionTitle("This Week's Adventures 🌈")
                AdventureCard(
                    title = "Color Sorting Game",
                    description = "Learn colors by sorting fun objects!",
                    icon = Icons.Default.Palette,
                    buttonColor = Color(0xFFFFC0CB),
                    backgroundColor = Color(0xFFFFF0F5),
                    onClick = onNavigateToProgress
                )
                Spacer(modifier = Modifier.height(8.dp))
                AdventureCard(
                    title = "Story Time",
                    description = "Magical adventure with picture books",
                    icon = Icons.Default.MenuBook,
                    buttonColor = Color(0xFFADD8E6),
                    backgroundColor = Color(0xFFE6F7FF),
                    onClick = onNavigateToProgress
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // "Today's Parent Tip" Section
        Card(
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE4B5)),
            shape = MaterialTheme.shapes.medium
        ) {
            Row(modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Notifications,
                    contentDescription = null, modifier = Modifier.size(40.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Encourage your child to explore new things every day!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun AdventureCard(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    buttonColor: Color,
    backgroundColor: Color,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(40.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, style = MaterialTheme.typography.titleMedium, color = Color.Black)
                Text(text = description,
                    style = MaterialTheme.typography.bodyMedium, color = Color.Black)
            }
            Button(onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor)) {
                Text(text = "Done")
            }
        }
    }
}



@Composable
fun SectionTitle(title: String) {
    Text(text = title, style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(vertical = 8.dp), color = Color.Black)
}


