package com.example.scheduleit.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "ai_chat_bot")
data class AIChatBot(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val taskId: Int?,
    val question: String,
    val response: String,
    val timestamp: Date
)
