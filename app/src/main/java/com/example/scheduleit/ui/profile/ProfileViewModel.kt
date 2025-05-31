package com.example.scheduleit.ui.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.scheduleit.data.models.UserProfile

class ProfileViewModel : ViewModel() {
    private val _profile = mutableStateOf(
        UserProfile(
            id = 0,
            email = "name.surname@gmail.com",
            phoneNumber = "+387 61 111 946",
            tasksCompleted = 10,
            tasksUpcoming = 5
        )
    )
    val profile: State<UserProfile> = _profile

    fun updateProfile(email: String, phoneNumber: String) {
        _profile.value = _profile.value.copy(email = email, phoneNumber = phoneNumber)
    }

    fun logout() {
        // TODO: Add real logout logic here, e.g., clearing user data, tokens etc.
        println("User logged out")
    }
}
