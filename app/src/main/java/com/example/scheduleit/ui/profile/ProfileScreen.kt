package com.example.scheduleit.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.scheduleit.ui.navigation.BottomNavigationBar

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
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = { /* Navigate */ }) { Text("View date") }
            Button(onClick = { /* Navigate */ }) { Text("Tasks") }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Profile", style = MaterialTheme.typography.h5)

        Text("Email: ${profile.email}")
        Text("Phone: ${profile.phoneNumber}")
        Text("Tasks completed: ${profile.tasksCompleted}")
        Text("Tasks upcoming: ${profile.tasksUpcoming}")

        Spacer(modifier = Modifier.height(20.dp))
        Text("Finished Tasks (Chart placeholder)")
        Box(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.height(10.dp))
        Text("Finished/Unfinished Tasks (Pie placeholder)")
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

        Spacer(modifier = Modifier.height(16.dp))

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
