import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kidslearn.Content


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LittleLearnersScreen(onNavigateToProgress: () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(modifier = Modifier.padding(top = 35.dp)) {
                        Text(
                            text = "Little Learners 🚀",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 36.sp,
                                color = Color.White
                            )
                        )
                        Text(
                            text = "Your child's learning adventure!",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                },
                actions = {
                    Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFFA500)),
                modifier = Modifier.height(120.dp)
            )
        }
    ) { padding ->
        Content(modifier = Modifier.padding(padding),
            onNavigateToProgress = onNavigateToProgress)


    }
}
