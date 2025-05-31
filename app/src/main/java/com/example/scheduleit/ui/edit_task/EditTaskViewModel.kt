package com.example.scheduleit.ui.edit_task

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class EditTaskViewModel : ViewModel() {
    var taskName = mutableStateOf("")
    var taskDescription = mutableStateOf("")
    var taskDate = mutableStateOf("Select date")

    fun updateTask() {
        // Logic to update the task (e.g., save to DB)
        println("Task Updated: ${taskName.value} - ${taskDescription.value} on ${taskDate.value}")
    }

    fun changeDate(newDate: String) {
        taskDate.value = newDate
    }

    fun clearFields() {
        taskName.value = ""
        taskDescription.value = ""
        taskDate.value = "Select date"
    }
}
