package com.example.scheduleit.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val category: String,
    val name: String,
    val details: String
)

class HomeScreenViewModel : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(
        listOf(
            Task(category = "General", name = "Welcome Task", details = "This is your first task.")
        )
    )
    val tasks: StateFlow<List<Task>> = _tasks

    fun addTask(task: Task) {
        _tasks.value = _tasks.value + task
    }

    fun removeTask(task: Task) {
        _tasks.value = _tasks.value.filterNot { it.id == task.id }
    }

    fun clearTasks() {
        _tasks.value = emptyList()
    }
}
