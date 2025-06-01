package com.example.scheduleit.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.scheduleit.ui.navigation.BottomNavigationBar
import com.example.scheduleit.ui.navigation.ScheduleItTopAppBar

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel(),
    navController: NavController
) {
    val profile = viewModel.profile.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // ✅ Top App Bar with navigation icons
        ScheduleItTopAppBar(
            title = "Profile",
            canNavigateBack = true,
            onNavigateHome = { navController.popBackStack() },
            onViewDate = { navController.navigate("calendar") },
            onProfileClick = { /* Already on profile */ },
            onCollabClick = { navController.navigate("collab") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Profile Info",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        profile?.let {
            Text("Email: ${it.email}", style = MaterialTheme.typography.bodyLarge)
            Text("Phone: ${it.phoneNumber}", style = MaterialTheme.typography.bodyLarge)
            Text("Tasks Completed: ${it.tasksCompleted}", style = MaterialTheme.typography.bodyLarge)
            Text("Tasks Upcoming: ${it.tasksUpcoming}", style = MaterialTheme.typography.bodyLarge)
        } ?: Text("Loading...", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(24.dp))

        Text("Finished Tasks (Chart placeholder)", style = MaterialTheme.typography.bodyMedium)
        Box(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Finished/Unfinished Tasks (Pie placeholder)", style = MaterialTheme.typography.bodyMedium)
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Cyan)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { println("AI feature triggered") },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("AI")
        }

        Spacer(modifier = Modifier.height(24.dp))

        BottomNavigationBar(
            onCustomizeClick = { navController.navigate("customize") },
            onRemindersClick = { navController.navigate("reminders") },
            onLogoutClick = {
                navController.navigate("login") {
                    popUpTo("home") { inclusive = true }
                }
            }
        )
    }
}