package com.example.scheduleit.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.scheduleit.auth.*
import com.example.scheduleit.ui.add_task.AddTaskScreen
import com.example.scheduleit.ui.ai.AIScreen
import com.example.scheduleit.ui.calendar.CalendarScreen
import com.example.scheduleit.ui.customisation.CustomizeScreen
import com.example.scheduleit.ui.details.TaskDetailScreen
import com.example.scheduleit.ui.finance.FinanceScreen
import com.example.scheduleit.ui.home.HomeScreen
import com.example.scheduleit.ui.home.HomeScreenViewModel
import com.example.scheduleit.ui.info.InfoScreen
import com.example.scheduleit.ui.mindfulness.MindfulnessScreen
import com.example.scheduleit.ui.profile.ProfileScreen
import com.example.scheduleit.ui.reminders.RemindersScreen
import com.example.scheduleit.ui.collaboration.CollaborationTasksScreen
import com.example.scheduleit.ui.task_history.TaskHistoryScreen
import com.example.scheduleit.ui.tasks.TaskViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleItNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        // Home Screen
        composable("home") {
            val homeViewModel: HomeScreenViewModel = viewModel()
            HomeScreen(
                navController = navController,
                viewModel = homeViewModel,
                navigateToAddTask = { navController.navigate("add_task") },
                navigateToDetails = { taskId -> navController.navigate("task_detail/$taskId") },
                onCustomizeClick = { navController.navigate("customize") },
                onRemindersClick = { navController.navigate("reminders") },
                onLogoutClick = {
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }

        // Add Task Screen
        composable("add_task") {
            val taskViewModel: TaskViewModel = viewModel()
            AddTaskScreen(
                viewModel = taskViewModel,
                onSave = { navController.navigateUp() },
                onCustomizeClick = { navController.navigate("customize") },
                onRemindersClick = { navController.navigate("reminders") },
                onLogoutClick = {
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }

        // Register Screen
        composable("register") {
            RegisterScreen(
                navController = navController,
                onRegistered = {
                    navController.navigate("home") {
                        popUpTo("register") { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        // Login Screen
        composable("login") {
            LoginScreen(
                onLoggedIn = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                navigateToRegister = {
                    navController.navigate("register")
                },
                navigateToResetPwd = {
                    navController.navigate("reset_pwd")
                }
            )
        }

        // Reset Password Screen
        composable("reset_pwd") {
            ResetPwdScreen(
                onEmailSent = {
                    navController.popBackStack()
                }
            )
        }

        // Task Detail Screen
        composable("task_detail/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
            if (taskId != null) {
                TaskDetailScreen(taskId = taskId, navController = navController)
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No task found with that ID")
                }
            }
        }

        // Task History Screen
        composable("history") {
            TaskHistoryScreen(
                onCustomizeClick = { navController.navigate("customize") },
                onRemindersClick = { navController.navigate("reminders") },
                onLogoutClick = { navController.navigate("login") },
                onRequestDetails = { taskId ->
                    navController.navigate("task_detail/$taskId")
                }
            )
        }

        // Info Screen
        composable("info") {
            InfoScreen()
        }

        // Customize Screen
        composable("customize") {
            CustomizeScreen(
                onNavigateToAI = { navController.navigate("ai_screen") },
                onNavigateToDate = { navController.navigate("calendar") },
                onNavigateToTasks = { navController.navigate("home") },
                onNavigateToReminders = { navController.navigate("reminders") },
                onLogout = { navController.navigate("login") },
                onNavigateToResetPassword = { navController.navigate("reset_pwd") },
                onNavigateToDeleteAccount = { navController.navigate("delete_account") }
            )
        }

        // Reminders Screen
        composable("reminders") {
            RemindersScreen(navController=navController)
        }

        // Profile Screen
        composable("profile") {
            ProfileScreen(navController = navController)
        }

        // Collaboration Screen
        composable("collaboration") {
            CollaborationTasksScreen()
        }

        // Finance Screen
        composable("finance") {
            FinanceScreen(navController = navController)
        }

        // Calendar Screen
        composable("calendar") {
            CalendarScreen(navController = navController)
        }

        // Mindfulness Screen
        composable("mindfulness") {
            MindfulnessScreen(navController = navController)
        }

        // AI Screen
        composable("ai_screen") {
            AIScreen(
                onViewDateClick = { navController.navigate("calendar") },
                onCollaborationClick = { navController.navigate("collaboration") },
                onCloseClick = { navController.navigateUp() },
                onViewHistoryClick = { navController.navigate("history") },
                onAddAiTaskClick = { navController.navigate("add_task") },
                onCustomizeClick = { navController.navigate("customize") },
                onRemindersClick = { navController.navigate("reminders") },
                onLogoutClick = {
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }

        // Delete Account Screen (optional if implemented)
        composable("delete_account") {
            // Add DeleteAccountScreen() here if you've implemented it
        }
    }
}