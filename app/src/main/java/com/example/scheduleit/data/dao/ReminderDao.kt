package com.example.scheduleit.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.scheduleit.data.models.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Insert
    suspend fun insertReminder(reminder: Reminder)

    @Query("SELECT * FROM reminder WHERE type = :type")
    fun getRemindersByType(type: String): Flow<List<Reminder>>
}
