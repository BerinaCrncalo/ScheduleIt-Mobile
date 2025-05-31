package com.example.scheduleit.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import java.util.Date

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val durationMinutes: Int,
    val dueDate: Date,
    val status: String,
    val category: String,
    val priority: Int,
    @ColumnInfo(index = true) val userId: Int
)
