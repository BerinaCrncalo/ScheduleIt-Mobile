package com.example.scheduleit.ui.add_task

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scheduleit.ui.navigation.BottomNavigationBar
import com.example.scheduleit.ui.tasks.Task
import com.example.scheduleit.ui.tasks.TaskViewModel
import org.threeten.bp.LocalDate
import android.app.DatePickerDialog
import androidx.compose.material.Scaffold
import androidx.compose.ui.platform.LocalContext
import com.example.scheduleit.data.converter.DateTimeConverterUtil
import java.util.Calendar


@Composable
fun AddTaskScreen(
    viewModel: TaskViewModel = viewModel(),
    onSave: () -> Unit,
    onCustomizeClick: () -> Unit,
    onRemindersClick: () -> Unit,
    onLogoutClick: () -> Unit
){
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    val context = LocalContext.current

    val calendar = Calendar.getInstance().apply {
        set(
            selectedDate.year,
            selectedDate.monthValue - 1,
            selectedDate.dayOfMonth
        )
    }

    // DatePickerDialog using ThreeTenABP LocalDate
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            // Convert picked date to org.threeten.bp.LocalDate
            selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    // UI code (same as before)
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                onCustomizeClick = onCustomizeClick,
                onRemindersClick = onRemindersClick,
                onLogoutClick = onLogoutClick
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(padding)
        ) {
            Text("New Task", style = MaterialTheme.typography.h6)

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Enter task name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Enter task description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            Spacer(Modifier.height(8.dp))

            Button(onClick = {
                datePickerDialog.show()
            }) {
                Icon(Icons.Default.DateRange, contentDescription = null)
                Spacer(Modifier.width(4.dp))
                Text("Select date: $selectedDate")
            }

            Spacer(Modifier.height(16.dp))

            Button(
                enabled = title.isNotBlank(),
                onClick = {
                    val dateAsDate = DateTimeConverterUtil.localDateToDate(selectedDate)
                    viewModel.addTask(
                        Task(
                            id = 0,
                            title = title,
                            description = description,
                            date = selectedDate
                        )
                    )
                    onSave()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Check, contentDescription = null)
                Spacer(Modifier.width(4.dp))
                Text("Add Task")
            }
        }
    }
}
