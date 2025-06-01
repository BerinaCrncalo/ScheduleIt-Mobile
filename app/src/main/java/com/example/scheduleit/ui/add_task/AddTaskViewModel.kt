package com.example.scheduleit.ui.add_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.scheduleit.ui.tasks.Task
import org.threeten.bp.LocalDate

class AddTaskViewModel : ViewModel() {

    // Use backing property for mutable state, private setter for encapsulation
    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    var date by mutableStateOf(LocalDate.now())
        private set

    /**
     * Updates the task title.
     */
    fun onTitleChange(newTitle: String) {
        title = newTitle
    }

    /**
     * Updates the task description.
     */
    fun onDescriptionChange(newDesc: String) {
        description = newDesc
    }

    /**
     * Updates the task date.
     */
    fun onDateChange(newDate: LocalDate) {
        date = newDate
    }

    /**
     * Attempts to add a task.
     * Returns true if added successfully, false otherwise.
     */
    fun addTask(onTaskAdded: (Task) -> Unit): Boolean {
        if (title.isBlank()) return false // Validation: title must not be blank

        val task = Task(
            id = 0, // You might want to assign IDs outside this VM (e.g. DB)
            title = title.trim(), // Trim inputs to avoid trailing spaces
            description = description.trim(),
            date = date
        )

        onTaskAdded(task) // Callback to send task to caller (e.g., repository or UI)
        clear() // Reset inputs after adding
        return true
    }

    /**
     * Clears the current state.
     */
    private fun clear() {
        title = ""
        description = ""
        date = LocalDate.now()
    }
}
