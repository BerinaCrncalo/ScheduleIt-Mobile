package com.example.scheduleit.ui.task_history

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scheduleit.ui.components.CompletedTaskCard
import com.example.scheduleit.ui.components.DeleteAlert
import com.example.scheduleit.ui.navigation.BottomNavigationBar

object ScheduleItHistoryDestination {
    const val route = "task_history"
}

@Composable
fun TaskHistoryScreen(
    onCustomizeClick: () -> Unit,
    onRemindersClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onRequestDetails: (Int) -> Unit,
    viewModel: TaskHistoryViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    // Show toast when task deleted (only once per deletion)
    LaunchedEffect(state.confirmDelete) {
        if (state.confirmDelete) {
            Toast.makeText(context, "Task deleted successfully!", Toast.LENGTH_SHORT).show()
            viewModel.denyDeletion()
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                onCustomizeClick = onCustomizeClick,
                onRemindersClick = onRemindersClick,
                onLogoutClick = onLogoutClick
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp)
        ) {
            listOf(
                "Finished" to state.finishedTasks,
                "Work" to state.workTasks,
                "Personal" to state.personalTasks,
                "Health" to state.healthTasks,
                "Wellness" to state.wellnessTasks,
                "Appointments" to state.appointmentTasks,
                "Errands" to state.errandTasks,
                "Learning" to state.learningTasks,
                "Others" to state.otherTasks
            ).forEach { (title, tasks) ->
                item { SectionHeader(title) }
                item {
                    if (tasks.isEmpty()) {
                        EmptyTasksText()
                    } else {
                        Column {
                            tasks.forEach { task ->
                                CompletedTaskCard(
                                    task = task,
                                    isChecked = task.status == "Completed",
                                    onCheckedChange = { t, checked ->
                                        viewModel.updateTaskStatus(t, checked)
                                    },
                                    onDelete = { t ->
                                        viewModel.assignTaskForDeletion(t)
                                    },
                                    onRequestDetails = onRequestDetails,
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        if (state.openDeleteDialog) {
            DeleteAlert(
                onDelete = {
                    viewModel.confirmDeletion()
                    viewModel.closeDeleteDialog()
                },
                onDismissRequest = {
                    viewModel.closeDeleteDialog()
                }
            )
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun EmptyTasksText() {
    Text(
        text = "No tasks available",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        textAlign = TextAlign.Center
    )
}
