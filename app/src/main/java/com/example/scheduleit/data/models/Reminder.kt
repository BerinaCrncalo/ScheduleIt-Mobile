package com.example.scheduleit.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time

@Entity(tableName = "reminder")
data class Reminder(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String,  // "Spending", "Collaboration", etc.
    val message: String,
    val reminderTime: Time
)
