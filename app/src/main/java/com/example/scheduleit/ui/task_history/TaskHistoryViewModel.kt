package com.example.scheduleit.ui.task_history

import androidx.lifecycle.ViewModel
import com.example.scheduleit.data.models.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*

data class TaskHistoryUiState(
    val workTasks: List<Task> = emptyList(),
    val personalTasks: List<Task> = emptyList(),
    val healthTasks: List<Task> = emptyList(),
    val wellnessTasks: List<Task> = emptyList(),
    val appointmentTasks: List<Task> = emptyList(),
    val errandTasks: List<Task> = emptyList(),
    val learningTasks: List<Task> = emptyList(),
    val otherTasks: List<Task> = emptyList(),
    val finishedTasks: List<Task> = emptyList(),

    val openDeleteDialog: Boolean = false,
    val confirmDelete: Boolean = false,
    val taskToDelete: Task? = null
)

class TaskHistoryViewModel : ViewModel() {

    private val _state = MutableStateFlow(TaskHistoryUiState(
        workTasks = sampleTasks.filter { it.category == "Work" },
        personalTasks = sampleTasks.filter { it.category == "Personal" },
        healthTasks = sampleTasks.filter { it.category == "Health" },
        wellnessTasks = sampleTasks.filter { it.category == "Wellness" },
        appointmentTasks = sampleTasks.filter { it.category == "Appointments" },
        errandTasks = sampleTasks.filter { it.category == "Errands" },
        learningTasks = sampleTasks.filter { it.category == "Learning" },
        otherTasks = sampleTasks.filter { it.category == "Others" },
        finishedTasks = sampleTasks.filter { it.status == "Completed" }
    ))

    val state: StateFlow<TaskHistoryUiState> = _state

    fun assignTaskForDeletion(task: Task) {
        _state.value = _state.value.copy(taskToDelete = task, openDeleteDialog = true)
    }

    fun closeDeleteDialog() {
        _state.value = _state.value.copy(openDeleteDialog = false)
    }

    fun confirmDeletion() {
        val task = _state.value.taskToDelete ?: return
        _state.value = _state.value.copy(
            workTasks = _state.value.workTasks.filterNot { it.id == task.id },
            personalTasks = _state.value.personalTasks.filterNot { it.id == task.id },
            healthTasks = _state.value.healthTasks.filterNot { it.id == task.id },
            wellnessTasks = _state.value.wellnessTasks.filterNot { it.id == task.id },
            appointmentTasks = _state.value.appointmentTasks.filterNot { it.id == task.id },
            errandTasks = _state.value.errandTasks.filterNot { it.id == task.id },
            learningTasks = _state.value.learningTasks.filterNot { it.id == task.id },
            otherTasks = _state.value.otherTasks.filterNot { it.id == task.id },
            finishedTasks = _state.value.finishedTasks.filterNot { it.id == task.id },
            confirmDelete = true,
            taskToDelete = null
        )
    }

    fun denyDeletion() {
        _state.value = _state.value.copy(confirmDelete = false)
    }

    fun updateTaskStatus(task: Task, isChecked: Boolean) {
        val newStatus = if (isChecked) "Completed" else "Pending"
        val updatedTask = task.copy(status = newStatus)
        val updatedTasks = { list: List<Task> ->
            list.map { if (it.id == task.id) updatedTask else it }
        }

        _state.value = _state.value.copy(
            workTasks = updatedTasks(_state.value.workTasks),
            personalTasks = updatedTasks(_state.value.personalTasks),
            healthTasks = updatedTasks(_state.value.healthTasks),
            wellnessTasks = updatedTasks(_state.value.wellnessTasks),
            appointmentTasks = updatedTasks(_state.value.appointmentTasks),
            errandTasks = updatedTasks(_state.value.errandTasks),
            learningTasks = updatedTasks(_state.value.learningTasks),
            otherTasks = updatedTasks(_state.value.otherTasks)
        )
    }
}

// Temporary sample data
val sampleTasks = listOf(
    Task(
        id = 1,
        title = "Complete report",
        dueDate = Date(),
        category = "Work",
        description = "Finish the quarterly financial report",
        durationMinutes = 120,
        status = "Completed",
        priority = 1,
        userId = 1001
    ),
    Task(
        id = 2,
        title = "Buy groceries",
        dueDate = Date(),
        category = "Errands",
        description = "Get vegetables, fruits, and dairy",
        durationMinutes = 60,
        status = "Pending",
        priority = 2,
        userId = 1001
    ),
    Task(
        id = 3,
        title = "Yoga session",
        dueDate = Date(),
        category = "Health",
        description = "Morning yoga for flexibility",
        durationMinutes = 45,
        status = "Pending",
        priority = 3,
        userId = 1001
    ),
    Task(
        id = 4,
        title = "Read a book",
        dueDate = Date(),
        category = "Learning",
        description = "Read 50 pages of Kotlin programming book",
        durationMinutes = 90,
        status = "Pending",
        priority = 4,
        userId = 1001
    ),
    Task(
        id = 5,
        title = "Dentist appointment",
        dueDate = Date(),
        category = "Appointments",
        description = "Routine dental checkup",
        durationMinutes = 30,
        status = "Scheduled",
        priority = 1,
        userId = 1001
    )
)