package com.example.scheduleit

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.scheduleit.data.repository.ScheduleItRepository
import com.example.scheduleit.ui.navigation.BottomNavigationBar
import com.example.scheduleit.ui.navigation.ScheduleItNavHost
import com.example.scheduleit.ui.navigation.ScheduleItTopAppBar

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScheduleItApp(
    repository: ScheduleItRepository,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        modifier = modifier,
        topBar = {
            ScheduleItTopAppBar(
                canNavigateBack = false,
                onNavigateHome = { navController.navigate("home") },
                onViewDate = { navController.navigate("calendar") },
                onProfileClick = { navController.navigate("profile") },
                onCollabClick = { navController.navigate("collaboration") }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                onCustomizeClick = { navController.navigate("customize") },
                onRemindersClick = { navController.navigate("reminders") },
                onLogoutClick = {
                    navController.navigate("login") {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    }
                }
            )
        }
    ) { innerPadding ->
        ScheduleItNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
