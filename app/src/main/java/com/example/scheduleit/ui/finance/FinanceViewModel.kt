package com.example.scheduleit.ui.finance

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FinanceViewModel : ViewModel() {
    private val _spendingData = MutableStateFlow(listOf(50f, 30f, 20f))
    val spendingData: StateFlow<List<Float>> = _spendingData

    fun updateSpending(newData: List<Float>) {
        _spendingData.value = newData
    }
}
