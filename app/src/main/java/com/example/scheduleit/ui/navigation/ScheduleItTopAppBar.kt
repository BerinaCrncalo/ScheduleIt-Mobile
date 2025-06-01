package com.example.scheduleit.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
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
        title = {
            Text(
                text = title,
                color = Color.White
            )
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onNavigateHome) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Go back",
                        tint = Color.White
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = onViewDate) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "View date",
                    tint = Color.White
                )
            }
            IconButton(onClick = onProfileClick) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile",
                    tint = Color.White
                )
            }
            IconButton(onClick = onCollabClick) {
                Icon(
                    imageVector = Icons.Filled.People,
                    contentDescription = "Collaboration",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF320064)
        )
    )
}