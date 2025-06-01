package com.example.scheduleit.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index
import androidx.room.TypeConverters
import com.example.scheduleit.data.converter.DateTimeConverters
import java.util.Date

@Entity(
    tableName = "task",
    indices = [Index(value = ["userId"])]
)
@TypeConverters(DateTimeConverters::class)
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val durationMinutes: Int,
    val dueDate: Date,
    val status: String,
    val category: String,
    val priority: Int,
    val userId: Int
)
