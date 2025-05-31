package com.example.scheduleit.ui.customisation

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

class CustomizeViewModel : ViewModel() {
    var email by mutableStateOf("name.surname@gmail.com")
        private set

    var selectedColor by mutableStateOf(Color(0xFF563FA2))
        private set

    fun updateEmail(newEmail: String) {
        email = newEmail
    }

    fun resetPassword(navController: NavController) {
        navController.navigate("reset_pwd")
    }

    fun deleteAccount(navController: NavController) {
        navController.navigate("delete_account")
    }

    fun changeColor(color: Color) {
        selectedColor = color
    }
}
