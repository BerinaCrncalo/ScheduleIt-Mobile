package com.example.scheduleit.ui.collaboration

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*  // Material3 import
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CollaborationTasksScreen(viewModel: CollaborationTasksViewModel = viewModel()) {
    var newTask by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Collaboration Tasks", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(12.dp))

        Text("To do: ${viewModel.toDoTasks.joinToString()}", style = MaterialTheme.typography.bodyMedium)
        Text("In Progress: ${viewModel.inProgressTasks.joinToString()}", style = MaterialTheme.typography.bodyMedium)
        Text("Done: ${viewModel.doneTasks.joinToString()}", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = newTask,
            onValueChange = { newTask = it },
            label = { Text("New Collaboration Task") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (newTask.isNotBlank()) {
                    viewModel.addTask("To do", newTask)
                    newTask = ""
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = newTask.isNotBlank()
        ) {
            Text("Add task")
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = comment,
            onValueChange = { comment = it },
            label = { Text("Comment") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (comment.isNotBlank()) {
                    viewModel.addComment(comment)
                    comment = ""
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = comment.isNotBlank()
        ) {
            Text("Comment")
        }
    }
}
