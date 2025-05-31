package com.example.scheduleit.ui.ai

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.scheduleit.data.models.AIChatBot
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.Date

class AIScreenViewModel : ViewModel() {

    var aiSuggestion = mutableStateOf("")
        private set

    private val _history = mutableStateListOf<AIChatBot>()
    val history: List<AIChatBot> get() = _history

    private val tasks = listOf(
        Task("Finish report", 60),
        Task("Team meeting", 45),
        Task("Email responses", 60),
        Task("Project work", 90)
    )

    @RequiresApi(Build.VERSION_CODES.O)
    fun generateSuggestions(question: String) {
        val startTime = LocalTime.of(9, 0)
        val suggestions = StringBuilder("✅ Suggested Plan:\n")

        var currentTime = startTime
        for (task in tasks) {
            val endTime = currentTime.plusMinutes(task.durationMinutes.toLong())
            suggestions.append("• ${formatTime(currentTime)} - ${formatTime(endTime)}: ${task.title}\n")
            currentTime = endTime.plusMinutes(15)
        }

        suggestions.append("\nPrioritize: ${tasks.joinToString(" > ") { it.title }}")
        aiSuggestion.value = suggestions.toString()

        val now = LocalDateTime.now()
        val timestamp = Date.from(now.atZone(ZoneId.systemDefault()).toInstant())

        _history.add(
            AIChatBot(
                id = 0,
                taskId = 0,
                question = question,
                response = aiSuggestion.value,
                timestamp = timestamp
            )
        )
    }

    private fun formatTime(time: LocalTime): String = time.toString().substring(0, 5)

    data class Task(val title: String, val durationMinutes: Int)
}
