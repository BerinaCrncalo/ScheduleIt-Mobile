package com.example.scheduleit.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.scheduleit.ui.navigation.NavigationDestination
import com.example.scheduleit.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person

object RegisterDestination : NavigationDestination {
    override val route = "register"
    override val titleRes = R.string.register
    override val icon = Icons.Default.Person
}

@Composable
fun RegisterScreen(
    onRegistered: () -> Unit,
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var pwd by remember { mutableStateOf("") }
    var confirmPwd by remember { mutableStateOf("") }

    val state by viewModel.authState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Create account", style = MaterialTheme.typography.h5)

        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        OutlinedTextField(value = pwd, onValueChange = { pwd = it }, label = { Text("Password") })
        OutlinedTextField(
            value = confirmPwd,
            onValueChange = { confirmPwd = it },
            label = { Text("Confirm password") }
        )

        Button(
            onClick = { viewModel.register(email, pwd, confirmPwd) },
            enabled = state !is AuthState.Loading
        ) {
            Text("Register")
        }

        when (state) {
            is AuthState.Error -> Text(
                (state as AuthState.Error).message,
                color = MaterialTheme.colors.error
            )
            AuthState.Loading -> CircularProgressIndicator()
            else -> {}
        }

        // Navigation side effect for success
        LaunchedEffect(key1 = state) {
            if (state is AuthState.Success) {
                onRegistered()
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Bottom text with clickable "Log in here"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Already have an account? ")
            Text(
                text = "Log in here",
                color = MaterialTheme.colors.primary,
                modifier = Modifier.clickable {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                style = MaterialTheme.typography.body2.copy(textDecoration = TextDecoration.Underline)
            )
        }
    }
}
