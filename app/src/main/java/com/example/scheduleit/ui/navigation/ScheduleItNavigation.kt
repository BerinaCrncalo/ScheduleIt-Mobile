package com.example.scheduleit.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
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
import com.example.scheduleit.ui.details.ScheduleItDetailsDestination
import com.example.scheduleit.ui.details.TaskDetailScreen
import com.example.scheduleit.ui.home.HomeDestination
import com.example.scheduleit.ui.home.HomeScreen
import com.example.scheduleit.ui.home.HomeScreenViewModel
import com.example.scheduleit.ui.info.InfoDestination
import com.example.scheduleit.ui.info.InfoScreen
import com.example.scheduleit.ui.customisation.CustomizeScreen
import com.example.scheduleit.ui.finance.FinanceScreen
import com.example.scheduleit.ui.mindfulness.MindfulnessScreen
import com.example.scheduleit.ui.profile.ProfileScreen
import com.example.scheduleit.ui.collaboration.CollaborationTasksScreen
import com.example.scheduleit.ui.reminders.RemindersScreen
import com.example.scheduleit.ui.task_history.ScheduleItHistoryDestination
import com.example.scheduleit.ui.task_history.TaskHistoryScreen
import com.example.scheduleit.ui.tasks.TaskViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleItNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        // Home Screen
        composable("home") {
            val homeViewModel: HomeScreenViewModel = viewModel()
            HomeScreen(
                navController = navController,
                viewModel = homeViewModel,
                navigateToAddTask = { navController.navigate("add_task") },
                navigateToDetails = { taskId -> navController.navigate("details/$taskId") },
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
            val viewModel: TaskViewModel = viewModel()
            AddTaskScreen(
                viewModel = viewModel,
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
        composable(route = RegisterDestination.route) {
            RegisterScreen(
                navController = navController,
                onRegistered = {
                    navController.navigate(HomeDestination.route) {
                        popUpTo(RegisterDestination.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        // Login Screen
        composable(route = LoginDestination.route) {
            LoginScreen(
                onLoggedIn = {
                    navController.navigate(HomeDestination.route) {
                        popUpTo(LoginDestination.route) { inclusive = true }
                    }
                },
                navigateToRegister = {
                    navController.navigate(RegisterDestination.route)
                },
                navigateToResetPwd = {
                    navController.navigate(ResetPwdDestination.route)
                }
            )
        }

        // Reset Password Screen
        composable(route = ResetPwdDestination.route) {
            ResetPwdScreen(
                onEmailSent = {
                    navController.popBackStack()
                }
            )
        }

        // Task Detail screen
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


        // Task Finished Screen
        composable(route = ScheduleItHistoryDestination.route) {
            TaskHistoryScreen(
                onCustomizeClick = { navController.navigate("customize") },
                onRemindersClick = { navController.navigate("reminders") },
                onLogoutClick = { navController.navigate("login") },
                onRequestDetails = { taskId ->
                    navController.navigate("${ScheduleItDetailsDestination.route}/$taskId")
                }
            )
        }


        // Info Screen
        composable(route = InfoDestination.route) {
            InfoScreen()
        }

        // Customize Screen
        composable(route = "customize") {
            CustomizeScreen(
                onNavigateToAI = { navController.navigate("ai") },
                onNavigateToDate = { navController.navigate("date") },
                onNavigateToTasks = { navController.navigate("tasks") },
                onNavigateToReminders = { navController.navigate("reminders") },
                onLogout = { navController.navigate("login") },
                onNavigateToResetPassword = { navController.navigate("reset_pwd") },
                onNavigateToDeleteAccount = { navController.navigate("delete_account") }
            )
        }

        // Reminders Screen
        composable(route = "reminders") {
            RemindersScreen()
        }

        // Profile Screen
        composable("profile") {
            ProfileScreen(navController = navController)
        }

        // Collaboration Screen
        composable(route = "collaboration") {
            CollaborationTasksScreen()
        }

        // Finance screen
        composable(route = "finance") {
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

        // Ai Screen
        composable("ai_screen") {
            AIScreen(
                onViewDateClick = {navController.navigate("calendar")},
                onCollaborationClick = { navController.navigate("collaboration")},
                onCloseClick = { navController.navigateUp() },
                onViewHistoryClick = { /* your logic */ },
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
    }
}
