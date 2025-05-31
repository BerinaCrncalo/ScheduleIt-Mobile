package com.example.scheduleit.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.scheduleit.ui.navigation.BottomNavigationBar
import org.threeten.bp.LocalDate

@Composable
fun CalendarScreen(
    viewModel: CalendarViewModel = viewModel(),
    navController: NavController
) {
    val selectedDate = viewModel.selectedDate.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(title = { Text("Calendar") })

        CalendarView(
            selectedDate = selectedDate.value,
            onDateSelected = { viewModel.onDateSelected(it) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* TODO: Show add task UI */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6C4AB6))
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Task", tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Add task", color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { /* TODO: AI logic */ }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
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
fun CalendarView(selectedDate: LocalDate, onDateSelected: (LocalDate) -> Unit) {
    val dates = (1..31).map { LocalDate.of(2024, 12, it) }

    Text("December 2024", fontWeight = FontWeight.Bold)

    LazyVerticalGrid(columns = GridCells.Fixed(7)) {
        items(dates) { date ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(40.dp)
                    .background(
                        if (date == selectedDate) Color(0xFF6C4AB6) else Color.LightGray,
                        shape = CircleShape
                    )
                    .clickable { onDateSelected(date) },
                contentAlignment = Alignment.Center
            ) {
                Text("${date.dayOfMonth}", color = Color.White)
            }
        }
    }
}
