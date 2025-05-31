package com.example.scheduleit.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.People
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ScheduleItTopAppBar(
    title: String = "ScheduleIt",
    canNavigateBack: Boolean = false,
    onNavigateHome: () -> Unit,
    onViewDate: () -> Unit,
    onProfileClick: () -> Unit,
    onCollabClick: () -> Unit
) {
    TopAppBar(
        title = { Text("ScheduleIt", color = Color.White) },
        navigationIcon = {
            IconButton(onClick = onNavigateHome) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
        },
        actions = {
            IconButton(onClick = onViewDate) {
                Icon(Icons.Default.DateRange, contentDescription = "Date", tint = Color.White)
            }
            IconButton(onClick = onProfileClick) {
                Icon(Icons.Default.AccountCircle, contentDescription = "Profile", tint = Color.White)
            }
            IconButton(onClick = onCollabClick) {
                Icon(Icons.Filled.People, contentDescription = "Collaboration")
            }
        },
        backgroundColor = Color(0xFF320064)
    )
}
