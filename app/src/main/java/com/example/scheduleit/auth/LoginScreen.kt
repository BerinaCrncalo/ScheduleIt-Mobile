package com.example.scheduleit.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scheduleit.R
import com.example.scheduleit.ui.navigation.NavigationDestination

object LoginDestination : NavigationDestination {
    override val route = "login"
    override val titleRes = R.string.login
    override val icon = Icons.Default.Lock
}

@Composable
fun LoginScreen(
    onLoggedIn: () -> Unit,
    navigateToRegister: () -> Unit,
    navigateToResetPwd: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var pwd by remember { mutableStateOf("") }
    val state by viewModel.authState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Log in", style = MaterialTheme.typography.h5)

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = pwd,
            onValueChange = { pwd = it },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { viewModel.login(email, pwd) },
            enabled = state !is AuthState.Loading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        TextButton(
            onClick = navigateToRegister,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create account")
        }

        TextButton(
            onClick = navigateToResetPwd,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Forgot password?")
        }

        when (state) {
            is AuthState.Error -> Text(
                text = (state as AuthState.Error).message,
                color = MaterialTheme.colors.error,
                modifier = Modifier.fillMaxWidth()
            )
            AuthState.Loading -> Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            AuthState.Success -> LaunchedEffect(Unit) {
                onLoggedIn()
            }
            else -> {}
        }
    }
}
