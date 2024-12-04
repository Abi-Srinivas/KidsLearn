package com.example.kidslearn

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.kidslearn.ui.theme.KidsLearnTheme


@Composable
fun ProgressScreen(onNavigateBack: () -> Unit, progressViewModel: ProgressViewModel = ProgressViewModel()) {

    val progress = progressViewModel.progress.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFADD8E6),
                        Color(0xFF00008B))
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Learning Journey!",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFADD8E6)),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Weekly Progress",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00008B)
                    )
                )
                Text(
                    text = when {
                        progress == 1.0f -> "You’ve completed the task!"
                        progress > 0.0f -> "Keep going!"
                        else -> "Start your journey!"
                    },
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Circular progress indicator
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(150.dp)
        ) {
            CircularProgressIndicator(
                progress = progress,
                modifier = Modifier.size(150.dp),
                color = Color(0xFF00008B),
                strokeWidth = 8.dp
            )
            Text(
                text = "${(progress * 100).toInt()}%", // Show progress percentage
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onNavigateBack() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Keep Up!")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProgressScreenPreview() {
    ProgressScreen(onNavigateBack = {})
}
