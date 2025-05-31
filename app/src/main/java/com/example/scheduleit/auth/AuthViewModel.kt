package com.example.scheduleit.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface AuthState {
    object Idle : AuthState
    object Loading : AuthState
    object Success : AuthState
    data class Error(val message: String) : AuthState
}

sealed interface ResetState {
    object Idle : ResetState
    object EmailSent : ResetState
    data class Error(val message: String) : ResetState
}

class AuthViewModel : ViewModel() {

    /* ---------- PUBLIC STATE ---------- */
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    private val _resetState = MutableStateFlow<ResetState>(ResetState.Idle)
    val resetState: StateFlow<ResetState> = _resetState

    /* ---------- REGISTRATION ---------- */
    fun register(email: String, pwd: String, confirmPwd: String) {
        if (email.isBlank() || pwd.isBlank()) {
            _authState.value = AuthState.Error("Fields cannot be empty")
            return
        }
        if (pwd != confirmPwd) {
            _authState.value = AuthState.Error("Passwords do not match")
            return
        }

        viewModelScope.launch {
            _authState.value = AuthState.Loading
            delay(1200)           // fake network call
            _authState.value = AuthState.Success
        }
    }

    /* ---------- LOGIN ---------- */
    fun login(email: String, pwd: String) {
        if (email.isBlank() || pwd.isBlank()) {
            _authState.value = AuthState.Error("Email / password required")
            return
        }

        viewModelScope.launch {
            _authState.value = AuthState.Loading
            delay(900)            // fake network call
            _authState.value = AuthState.Success
        }
    }

    /* ---------- RESET PASSWORD ---------- */
    fun sendResetEmail(email: String) {
        if (email.isBlank()) {
            _resetState.value = ResetState.Error("Email required")
            return
        }

        viewModelScope.launch {
            _resetState.value = ResetState.Idle
            delay(900)
            _resetState.value = ResetState.EmailSent
        }
    }

    /* ---------- HELPERS ---------- */
    fun clearAuthErrors() { _authState.value = AuthState.Idle }
    fun clearResetState() { _resetState.value = ResetState.Idle }
}
