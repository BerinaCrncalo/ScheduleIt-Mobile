package com.example.scheduleit.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.scheduleit.ui.tasks.TaskViewModel
import java.text.SimpleDateFormat
import java.util.Locale

object ScheduleItDetailsDestination {
    const val route = "task_details"
    const val taskIdArg = "taskId"

    val routeWithArgs = "$route/{$taskIdArg}"

    fun createRoute(taskId: Int): String = "$route/$taskId"
}

@Composable
fun TaskDetailScreen(
    taskId: Int,
    navController: NavController,
    viewModel: TaskViewModel = viewModel()
) {
    val task = viewModel.tasks.find { it.id == taskId }

    if (task == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Task not found", style = MaterialTheme.typography.h6)
        }
        return
    }

    val scaffoldState = rememberScaffoldState()
    var showCompletionMessage by remember { mutableStateOf(false) }

    val dateString = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(task.date)

    Scaffold(
        scaffoldState = scaffoldState
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Task Details", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(16.dp))

            Text("Title: ${task.title}", style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(8.dp))

            Text("Description: ${task.description}", style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.height(8.dp))

            Text("Due Date: $dateString", style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.height(24.dp))

            // Complete Task
            Button(
                onClick = {
                    showCompletionMessage = true
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Mark as Completed")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Edit Task
            Button(
                onClick = { navController.navigate("edit_task/${task.id}") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Edit Task")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Delete Task
            Button(
                onClick = {
                    viewModel.deleteTask(task)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
            ) {
                Text("Delete Task", color = Color.White)
            }
        }

        // Show Snackbar after completion
        if (showCompletionMessage) {
            LaunchedEffect(Unit) {
                scaffoldState.snackbarHostState.showSnackbar("Successfully completed, congratulations! 🎉")
                showCompletionMessage = false
            }
        }
    }
}
