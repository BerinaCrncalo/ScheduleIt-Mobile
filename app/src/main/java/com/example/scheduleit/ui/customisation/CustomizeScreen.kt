package com.example.scheduleit.ui.customisation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scheduleit.ui.navigation.BottomNavigationBar

@Composable
fun CustomizeScreen(
    viewModel: CustomizeViewModel = viewModel(),
    onNavigateToAI: () -> Unit = {},
    onNavigateToDate: () -> Unit = {},
    onNavigateToTasks: () -> Unit = {},
    onNavigateToReminders: () -> Unit = {},
    onNavigateToResetPassword: () -> Unit = {},
    onNavigateToDeleteAccount: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onNavigateToDate) {
                Text("View date")
            }
            Button(onClick = onNavigateToTasks) {
                Text("Tasks")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Customize", style = MaterialTheme.typography.h6)

        OutlinedTextField(
            value = viewModel.email,
            onValueChange = viewModel::updateEmail,
            label = { Text("Edit profile") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = onNavigateToResetPassword) {
                Text("Reset password")
            }
            Button(onClick = onNavigateToDeleteAccount) {
                Text("Delete account")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text("Change color")
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(viewModel.selectedColor)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onNavigateToAI,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("AI")
        }

        BottomNavigationBar(
            onCustomizeClick = {},
            onRemindersClick = onNavigateToReminders,
            onLogoutClick = onLogout
        )
    }
}
