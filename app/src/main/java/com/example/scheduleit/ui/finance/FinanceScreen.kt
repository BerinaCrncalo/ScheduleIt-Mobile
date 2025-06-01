package com.example.scheduleit.ui.finance

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.scheduleit.ui.navigation.BottomNavigationBar
import com.example.scheduleit.ui.components.PieChart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinanceScreen(
    viewModel: FinanceViewModel = viewModel(),
    navController: NavController
) {
    val spendingData = viewModel.spendingData.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Finance Track") }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                onCustomizeClick = { navController.navigate("customize") },
                onRemindersClick = { navController.navigate("reminders") },
                onLogoutClick = {
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { navController.navigate("calendar") }) {
                    Icon(Icons.Filled.DateRange, contentDescription = "View date")
                    Spacer(Modifier.width(8.dp))
                    Text("View date")
                }
                Button(onClick = { navController.navigate("tasks") }) {
                    Icon(Icons.Filled.List, contentDescription = "Tasks")
                    Spacer(Modifier.width(8.dp))
                    Text("Tasks")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            val options = listOf(
                "Add an expense",
                "Delete an expense",
                "Spending reminder",
                "Planned requests",
                "Saving recommendations",
                "Budget graph"
            )

            options.forEach {
                Text(text = it, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(vertical = 4.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            PieChart(spendingData.value)

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("aiscreen") },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("AI")
            }
        }
    }
}
