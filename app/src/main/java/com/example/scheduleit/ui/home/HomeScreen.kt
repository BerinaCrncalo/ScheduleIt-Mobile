package com.example.scheduleit.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.scheduleit.R
import com.example.scheduleit.ui.navigation.NavigationDestination
import com.example.scheduleit.ui.navigation.BottomNavigationBar


/**
 * Navigation-destination object for the Home screen.
 */
object HomeDestination : NavigationDestination {
    override val route: String = "home"
    override val titleRes: Int = R.string.home_top_bar
    override val icon = Icons.Default.Home
}

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = viewModel(),
    navigateToAddTask: () -> Unit,
    navigateToDetails: (String) -> Unit,
    onCustomizeClick: () -> Unit,
    onRemindersClick: () -> Unit,
    onLogoutClick: () -> Unit,
) {
    val tasks by viewModel.tasks.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Hello User!", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text("📅", fontSize = 28.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Top Buttons
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { /* View date logic */ }) {
                Text("📅 View date")
            }
            if (tasks.isNotEmpty()) {
                Button(onClick = { /* View tasks logic */ }) {
                    Text("📋 Tasks")
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Task List or Add Task
        if (tasks.isEmpty()) {
            AddTaskCard(onClick = navigateToAddTask)
        } else {
            tasks.forEach { task ->
                TaskCard(
                    task = task,
                    onRemove = { viewModel.removeTask(task) },
                    onClick = { navigateToDetails(task.id) }
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            AddTaskCard(onClick = navigateToAddTask)
        }
        Button(onClick = { viewModel.clearTasks() }) {
            Text("🧹 Clear All")
        }


        Spacer(modifier = Modifier.height(24.dp))

        FloatingActionButton(
            onClick = { /* AI interaction */ },
            backgroundColor = Color.White,
            modifier = Modifier
                .size(56.dp)
                .align(Alignment.End)
                .padding(8.dp)
        ) {
            Text("AI")
        }

        // Mindfulness Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            backgroundColor = Color(0xFF6A4FB6)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Mindfulness and Stress",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("• Motivation quotes", color = Color.White)
                Text("• Stress relief", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

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

@Composable
fun AddTaskCard(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Color(0xFF6A4FB6)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(32.dp)
        ) {
            Text("+ Add task", color = Color.White)
        }
    }
}

@Composable
fun TaskCard(task: Task, onRemove: () -> Unit, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Color(0xFF6A4FB6)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(task.category, color = Color.White, fontWeight = FontWeight.Bold)
                Text("❌", color = Color.White, modifier = Modifier.clickable { onRemove() })
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(task.name, color = Color.White)
            Text(task.details, color = Color.White)
        }
    }
}
