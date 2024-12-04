import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


fun loadParentTips(context: Context): List<ParentTip> {
    val json = context.assets.open("parent_tips.json")
        .bufferedReader().use { it.readText() }
    val type = object : TypeToken<List<ParentTip>>() {}.type
    return Gson().fromJson(json, type)
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ParentingTipScreen(context: Context) {
    val coroutineScope = rememberCoroutineScope()
    val parentTips = remember { mutableListOf<ParentTip>() }

    coroutineScope.launch {
        val tips = withContext(Dispatchers.IO) { loadParentTips(context) }
        parentTips.addAll(tips)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF8A2BE2),
                        Color(0xFFE6E6FA)
                    )
                )
            )
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Parenting Wisdom 👨‍👩‍👧‍👦",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            items(parentTips) { tip ->
                ParentTipCard(tip)
            }
        }
    }
}

@Composable
fun ParentTipCard(tip: ParentTip) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(15.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFE1BEE7),
                            Color(0xFFD1C4E9),
                            Color(0xFFF8BBD0)
                        )
                    )
                )
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = tip.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    ),
                    color = Color(0xFF0D47A1)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = tip.description,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp,
                        lineHeight = 20.sp
                    ),
                    color = Color.DarkGray
                )
            }
        }
    }
}

