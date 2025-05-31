package com.example.scheduleit.ui.edit_task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun EditTaskScreen(viewModel: EditTaskViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Text("Edit task", fontSize = 28.sp, color = Color.White, modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF5C3DA7), RoundedCornerShape(12.dp))
            .padding(12.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Task name
        OutlinedTextField(
            value = viewModel.taskName.value,
            onValueChange = { viewModel.taskName.value = it },
            label = { Text("Enter task name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Task description
        OutlinedTextField(
            value = viewModel.taskDescription.value,
            onValueChange = { viewModel.taskDescription.value = it },
            label = { Text("Enter task description") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Change date button
        Button(onClick = {
            viewModel.changeDate("June 1") // Replace with date picker
        }) {
            Icon(Icons.Default.DateRange, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(viewModel.taskDate.value)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Edit task button
        Button(onClick = { viewModel.updateTask() }) {
            Icon(Icons.Default.Check, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Edit task")
        }
    }
}
