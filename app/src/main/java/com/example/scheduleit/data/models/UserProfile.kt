package com.example.scheduleit.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val email: String,
    val phoneNumber: String,
    val tasksCompleted: Int = 0,
    val tasksUpcoming: Int = 0,
)
