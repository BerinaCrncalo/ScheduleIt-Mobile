package com.example.scheduleit.ui.edit_task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskScreen(viewModel: EditTaskViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Text(
            text = "Edit task",
            fontSize = 28.sp,
            color = Color.White,
            modifier = Modifier
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
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
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
        Button(
            onClick = { viewModel.changeDate("June 1") }, // Replace with Date Picker later
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5C3DA7))
        ) {
            Icon(Icons.Default.DateRange, contentDescription = "Pick a date", tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text(viewModel.taskDate.value, color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Edit task button
        Button(
            onClick = { viewModel.updateTask() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5C3DA7))
        ) {
            Icon(Icons.Default.Check, contentDescription = "Edit task", tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Edit task", color = Color.White)
        }
    }
}