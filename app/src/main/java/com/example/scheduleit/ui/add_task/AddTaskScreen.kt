package com.example.scheduleit.ui.add_task

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scheduleit.data.converter.DateTimeConverterUtil
import com.example.scheduleit.ui.navigation.BottomNavigationBar
import com.example.scheduleit.ui.tasks.Task
import com.example.scheduleit.ui.tasks.TaskViewModel
import org.threeten.bp.LocalDate
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

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

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
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("New Task", style = MaterialTheme.typography.headlineSmall)

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Enter task name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Enter task description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            Button(onClick = { datePickerDialog.show() }) {
                Icon(Icons.Default.DateRange, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Select date: ${DateTimeConverterUtil.formatDate(selectedDate)}")
            }

            Button(
                enabled = title.isNotBlank(),
                onClick = {
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
                Spacer(Modifier.width(8.dp))
                Text("Add Task")
            }
        }
    }
}
