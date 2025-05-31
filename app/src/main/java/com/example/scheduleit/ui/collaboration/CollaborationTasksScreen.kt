package com.example.scheduleit.ui.collaboration

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CollaborationTasksScreen(viewModel: CollaborationTasksViewModel = viewModel()) {
    var newTask by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Collaboration Tasks", style = MaterialTheme.typography.h5)

        Text("To do: ${viewModel.toDoTasks.joinToString()}")
        Text("In Progress: ${viewModel.inProgressTasks.joinToString()}")
        Text("Done: ${viewModel.doneTasks.joinToString()}")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = newTask,
            onValueChange = { newTask = it },
            label = { Text("New Collaboration Task") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            viewModel.addTask("To do", newTask)
            newTask = ""
        }) {
            Text("Add task")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = comment,
            onValueChange = { comment = it },
            label = { Text("Comment") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            viewModel.addComment(comment)
            comment = ""
        }) {
            Text("Comment")
        }
    }
}
