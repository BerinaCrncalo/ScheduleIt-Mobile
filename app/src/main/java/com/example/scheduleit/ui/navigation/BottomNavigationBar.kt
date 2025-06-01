package com.example.scheduleit.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BottomNavigationBar(
    onCustomizeClick: () -> Unit,
    onRemindersClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    NavigationBar(
        containerColor = Color(0xFFB4A7D6),
        modifier = Modifier.fillMaxWidth()
    ) {
        NavigationBarItem(
            selected = false,
            onClick = onCustomizeClick,
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Customize") },
            label = { Text("Customize") },
            alwaysShowLabel = true
        )
        NavigationBarItem(
            selected = false,
            onClick = onRemindersClick,
            icon = { Icon(Icons.Filled.Notifications, contentDescription = "Reminders") },
            label = { Text("Reminders") },
            alwaysShowLabel = true
        )
        NavigationBarItem(
            selected = false,
            onClick = onLogoutClick,
            icon = { Icon(Icons.Filled.Logout, contentDescription = "Logout") },
            label = { Text("Logout") },
            alwaysShowLabel = true
        )
    }
}
