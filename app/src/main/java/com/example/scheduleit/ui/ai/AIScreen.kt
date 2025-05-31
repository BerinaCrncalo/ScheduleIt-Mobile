package com.example.scheduleit.ui.ai

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scheduleit.ui.navigation.BottomNavigationBar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AIScreen(
    onViewDateClick: () -> Unit = {},
    onCollaborationClick: () -> Unit = {},
    onCloseClick: () -> Unit = {},
    onViewHistoryClick: () -> Unit = {},
    onAddAiTaskClick: () -> Unit = {},
    onCustomizeClick: () -> Unit = {},
    onRemindersClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    val viewModel: AIScreenViewModel = viewModel()
    var userQuestion by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                onCustomizeClick = onCustomizeClick,
                onRemindersClick = onRemindersClick,
                onLogoutClick = onLogoutClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
        ) {
            // Top navigation row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = onViewDateClick) {
                    Text("View date")
                }
                Button(onClick = onCollaborationClick) {
                    Text("👥")
                }
                Text("📋", fontSize = 24.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Purple AI Chatbot Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF5E4AE3), RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "AIChatBot",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            "❌",
                            color = Color.White,
                            modifier = Modifier.clickable { onCloseClick() }
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "💡 Finish tasks with high priority!",
                        color = Color.White,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = userQuestion,
                        onValueChange = { userQuestion = it },
                        label = { Text("Enter question for AIChatBot") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(onClick = {
                        viewModel.generateSuggestions(userQuestion)
                        userQuestion = ""
                    }) {
                        Text("Ask")
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        viewModel.aiSuggestion.value,
                        color = Color.White,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = onViewHistoryClick, modifier = Modifier.fillMaxWidth()) {
                        Text("View question history")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(onClick = onAddAiTaskClick, modifier = Modifier.fillMaxWidth()) {
                        Text("Add AI Task")
                    }
                }
            }
        }
    }
}
