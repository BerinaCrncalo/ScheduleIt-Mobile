package com.example.scheduleit.ui.ai

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scheduleit.ui.navigation.BottomNavigationBar
import com.example.scheduleit.ui.navigation.ScheduleItTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
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
        topBar = {
            ScheduleItTopAppBar(
                title = "AI ChatBot",
                canNavigateBack = false,
                onNavigateHome = {}, // no back button behavior needed here, or implement if needed
                onViewDate = onViewDateClick,
                onProfileClick = onCloseClick,  // using close icon as profile button here
                onCollabClick = onCollaborationClick
            )
        },
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
            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                tonalElevation = 4.dp,
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFF5E4AE3),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
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
                        label = { Text("Enter question for AIChatBot", color = Color.White) },
                        placeholder = { Text("Enter question for AIChatBot", color = Color.White.copy(alpha = 0.7f)) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White,
                            cursorColor = Color.White,
                            focusedLabelColor = Color.White,
                            unfocusedLabelColor = Color.White
                        ),
                        textStyle = LocalTextStyle.current.copy(color = Color.White),
                        singleLine = true
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
