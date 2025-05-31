package com.example.scheduleit.ui.reminders

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RemindersScreen(viewModel: RemindersViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text("Reminders", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {}) { Text("View date") }
            Button(onClick = {}) { Text("Tasks") }
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Default.Person, contentDescription = null)
            Icon(Icons.Default.Group, contentDescription = null)
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn {
            items(viewModel.reminders.size) { index ->
                val item = viewModel.reminders[index]
                Card(
                    backgroundColor = Color(0xFF6A4BB7),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { viewModel.toggleExpanded(index) }
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(item.title, color = Color.White)
                        if (item.expanded) {
                            Text("Details for ${item.title}", color = Color.White.copy(alpha = 0.7f))
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {}, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("AI")
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {}) { Text("Customize") }
            Button(onClick = {}) { Text("Reminders") }
            Button(onClick = {}) { Text("Logout") }
        }
    }
}
