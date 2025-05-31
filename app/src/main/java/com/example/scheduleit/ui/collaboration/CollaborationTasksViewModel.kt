package com.example.scheduleit.ui.collaboration

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class CollaborationTasksViewModel : ViewModel() {
    val toDoTasks = mutableStateListOf<String>()
    val inProgressTasks = mutableStateListOf<String>()
    val doneTasks = mutableStateListOf<String>()

    fun addTask(category: String, task: String) {
        when (category) {
            "To do" -> toDoTasks.add(task)
            "In Progress" -> inProgressTasks.add(task)
            "Done" -> doneTasks.add(task)
        }
    }

    fun addComment(comment: String) {
        println("Comment added: $comment")
    }
}
