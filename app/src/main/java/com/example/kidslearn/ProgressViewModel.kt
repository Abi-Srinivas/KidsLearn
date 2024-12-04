import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ProgressViewModel : ViewModel() {
    // Track progress with a mutable state
    var progress = mutableStateOf(0.0f)

    fun updateProgress() {
        if (progress.value < 1.0f) {
            progress.value += 0.25f // Increase progress by 25% each time
        }
    }
}

