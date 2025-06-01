package com.example.scheduleit

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.scheduleit.ui.theme.ScheduleItTheme
import com.example.scheduleit.ui.loader.Loader
import com.example.scheduleit.data.repository.OfflineRepository
import com.example.scheduleit.data.repository.ScheduleItRepository
import com.example.scheduleit.data.database.ScheduleItDatabase
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    private lateinit var repository: ScheduleItRepository

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = ScheduleItDatabase.getDatabase(this)

        repository = OfflineRepository(
            taskDao = database.taskDao(),
            reminderDao = database.reminderDao(),
            expenseDao = database.expenseDao(),
            commentDao = database.commentDao(),
            aiChatBotDao = database.aiChatBotDao(),
            userProfileDao = database.userProfileDao()
        )

        enableEdgeToEdge()

        setContent {
            ScheduleItTheme {
                var showLoader by remember { mutableStateOf(true) }

                LaunchedEffect(Unit) {
                    delay(3000)
                    showLoader = false
                }

                if (showLoader) {
                    Loader()
                } else {
                    Scaffold(
                        modifier = Modifier.fillMaxSize()
                    ) { innerPadding ->
                        ScheduleItApp(
                            repository = repository,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}
