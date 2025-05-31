package com.example.scheduleit.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "comment")
data class Comment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val taskId: Int,  // Foreign key to Task
    val userId: Int,  // Foreign key to UserProfile
    val content: String,
    val timestamp: Timestamp
)
