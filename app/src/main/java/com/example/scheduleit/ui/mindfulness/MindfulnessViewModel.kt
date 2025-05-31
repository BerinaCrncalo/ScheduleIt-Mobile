package com.example.scheduleit.ui.mindfulness

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class MindfulnessItem(val title: String, val expanded: Boolean = false)

class MindfulnessViewModel : ViewModel() {
    val mindfulnessItems = mutableStateListOf(
        MindfulnessItem("Guided meditation"),
        MindfulnessItem("Breathing exercises"),
        MindfulnessItem("Suggested sports and relaxation strategies"),
        MindfulnessItem("Budget goals"),
        MindfulnessItem("Suggestions for stress reduction"),
        MindfulnessItem("Add personalized mindfulness plan"),
        MindfulnessItem("Delete personalized mindfulness plan")
    )

    fun toggleExpanded(index: Int) {
        mindfulnessItems[index] = mindfulnessItems[index].copy(expanded = !mindfulnessItems[index].expanded)
    }
}
