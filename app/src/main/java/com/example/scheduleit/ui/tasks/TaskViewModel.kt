package com.example.scheduleit.ui.tasks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.threeten.bp.LocalDate

data class Task(
    val id: Int,
    val title: String,
    val description: String = "",
    val date: LocalDate = LocalDate.now()
)

class TaskViewModel : ViewModel() {
    var tasks by mutableStateOf(listOf<Task>())
        private set

    var currentTask by mutableStateOf<Task?>(null)
        private set

    fun addTask(task: Task) {
        if (task.title.isNotBlank()) {
            val newTask = task.copy(id = (tasks.maxOfOrNull { it.id } ?: 0) + 1)
            tasks = tasks + newTask
        }
    }

    fun editTask(updatedTask: Task) {
        tasks = tasks.map { if (it.id == updatedTask.id) updatedTask else it }
    }

    fun selectTask(task: Task) {
        currentTask = task
    }

    fun clearCurrentTask() {
        currentTask = null
    }

    fun deleteTask(task: Task) {
        tasks = tasks.filter { it.id != task.id }
    }
}
