package com.example.scheduleit.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import com.example.scheduleit.R
import com.example.scheduleit.ui.navigation.NavigationDestination

object ResetPwdDestination : NavigationDestination {
    override val route = "reset_pwd"
    override val titleRes = R.string.reset_password
    override val icon = Icons.Default.Lock
}

@Composable
fun ResetPwdScreen(
    onEmailSent: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    val state by viewModel.resetState.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Reset password", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )

        Button(
            onClick = { viewModel.sendResetEmail(email) },
            enabled = state !is ResetState.EmailSent
        ) {
            Text("Send reset link")
        }

        when (state) {
            is ResetState.Error -> Text(
                (state as ResetState.Error).message,
                color = MaterialTheme.colorScheme.error
            )
            ResetState.EmailSent -> {
                Text("Email sent! Check your inbox.")
                LaunchedEffect(Unit) { onEmailSent() }
            }
            else -> {}
        }
    }
}
