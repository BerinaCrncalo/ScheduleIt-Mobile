package com.example.scheduleit.ui.reminders

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class ReminderItem(val title: String, val expanded: Boolean = false)

class RemindersViewModel : ViewModel() {
    val reminders = mutableStateListOf(
        ReminderItem("Add a reminder"),
        ReminderItem("Delete a reminder"),
        ReminderItem("Spending Reminder"),
        ReminderItem("Collaboration Reminder"),
        ReminderItem("Saving recommendations"),
        ReminderItem("Budget goals"),
        ReminderItem("Sounds & Haptics")
    )

    fun toggleExpanded(index: Int) {
        reminders[index] = reminders[index].copy(expanded = !reminders[index].expanded)
    }
}
