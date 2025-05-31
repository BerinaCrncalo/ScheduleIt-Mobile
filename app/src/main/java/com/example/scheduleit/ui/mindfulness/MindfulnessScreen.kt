package com.example.scheduleit.ui.mindfulness

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Group
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.scheduleit.ui.navigation.BottomNavigationBar

@Composable
fun MindfulnessScreen(
    viewModel: MindfulnessViewModel = viewModel(),
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text("Mindfulness", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {}) { Text("View date") }
            Button(onClick = {}) { Text("Tasks") }
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Default.AccountCircle, contentDescription = null)
            Icon(Icons.Default.Group, contentDescription = null)
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn {
            items(viewModel.mindfulnessItems.size) { index ->
                val item = viewModel.mindfulnessItems[index]
                Card(
                    backgroundColor = Color(0xFFF1F1F1),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { viewModel.toggleExpanded(index) }
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(item.title, fontWeight = FontWeight.SemiBold)
                        if (item.expanded) {
                            Text("Details for ${item.title}", color = Color.Gray)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {}, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Time spent on meditating")
        }

        Icon(
            Icons.AutoMirrored.Filled.ShowChart,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(64.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {}, modifier = Modifier.align(Alignment.CenterHorizontally)) {
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
