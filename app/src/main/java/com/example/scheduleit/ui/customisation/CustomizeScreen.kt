package com.example.scheduleit.ui.customisation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

        Text("Customize", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.email,
            onValueChange = viewModel::updateEmail,
            label = { Text("Edit profile") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onNavigateToResetPassword,
                modifier = Modifier.weight(1f)
            ) {
                Text("Reset password")
            }
            Button(
                onClick = onNavigateToDeleteAccount,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError
                )
            ) {
                Text("Delete account")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Change color", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(viewModel.selectedColor, shape = MaterialTheme.shapes.small)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onNavigateToAI,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("AI")
        }

        Spacer(modifier = Modifier.height(16.dp))

        BottomNavigationBar(
            onCustomizeClick = {},
            onRemindersClick = onNavigateToReminders,
            onLogoutClick = onLogout
        )
    }
}
