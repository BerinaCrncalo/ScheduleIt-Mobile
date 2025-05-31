package com.example.scheduleit.ui.finance

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.scheduleit.ui.navigation.BottomNavigationBar

@Composable
fun FinanceScreen(
    viewModel: FinanceViewModel = viewModel(),
    navController: NavController
) {
    val spendingData = viewModel.spendingData.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(title = { Text("Finance Track") })

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { /* View date logic */ }) {
                Icon(Icons.Default.DateRange, contentDescription = null)
                Text("View date")
            }
            Button(onClick = { /* Tasks logic */ }) {
                Icon(Icons.Default.List, contentDescription = null)
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
            Text(text = it)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { /* Income and expenses logic */ }) {
            Text("Income and expenses")
        }

        Spacer(modifier = Modifier.height(16.dp))

        PieChart(spendingData.value)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* AI logic */ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("AI")
        }

        Spacer(modifier = Modifier.height(16.dp))

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
}

@Composable
fun PieChart(data: List<Float>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("Pie chart here (placeholder)")
    }
}
