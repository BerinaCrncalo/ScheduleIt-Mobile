package com.example.scheduleit.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationBar(
    onCustomizeClick: () -> Unit,
    onRemindersClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    androidx.compose.material.BottomAppBar(
        backgroundColor = Color(0xFFB4A7D6),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = onCustomizeClick) {
                Text("Customize")
            }
            Button(onClick = onRemindersClick) {
                Text("Reminders")
            }
            Button(onClick = onLogoutClick) {
                Text("Logout")
            }
        }
    }
}
