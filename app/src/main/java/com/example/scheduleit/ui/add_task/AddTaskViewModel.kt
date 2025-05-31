package com.example.scheduleit.ui.add_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.scheduleit.ui.tasks.Task
import org.threeten.bp.LocalDate

class AddTaskViewModel : ViewModel() {
    var title by mutableStateOf("")
        private set
    var description by mutableStateOf("")
        private set
    var date by mutableStateOf(LocalDate.now())
        private set

    fun onTitleChange(newTitle: String) {
        title = newTitle
    }

    fun onDescriptionChange(newDesc: String) {
        description = newDesc
    }

    fun onDateChange(newDate: LocalDate) {
        date = newDate
    }

    fun addTask(onTaskAdded: (Task) -> Unit) {
        if (title.isBlank()) return
        val task = Task(
            id = 0,
            title = title,
            description = description,
            date = date
        )
        onTaskAdded(task)
        clear()
    }

    private fun clear() {
        title = ""
        description = ""
        date = LocalDate.now()
    }
}
