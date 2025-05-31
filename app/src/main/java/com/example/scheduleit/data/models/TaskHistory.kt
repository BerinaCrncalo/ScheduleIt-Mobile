package com.example.scheduleit.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "task_history")
data class TaskHistory(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val taskId: Int,  // Foreign key to Task
    val status: String,  // "Completed", "Upcoming"
    val timestamp: Timestamp
)
