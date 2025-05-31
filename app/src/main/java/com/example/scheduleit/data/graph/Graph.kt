package com.example.scheduleit.data.graph

import android.content.Context
import com.example.scheduleit.data.database.ScheduleItDatabase
import com.example.scheduleit.data.repository.OfflineRepository

/**
 * Singleton object responsible for providing database and repository instances.
 */
object Graph {

    // Database instance (nullable)
    private var db: ScheduleItDatabase? = null

    /**
     * The repository instance. Safely initializes after db is provided.
     */
    val repository: OfflineRepository by lazy {
        val database = db ?: throw IllegalStateException("Database not initialized. Call provide() first.")
        OfflineRepository(
            database.taskDao(),
            database.reminderDao(),
            database.expenseDao(),
            database.commentDao(),
            database.aiChatBotDao(),
            database.userProfileDao()
        )
    }

    fun provide(context: Context) {
        if (db == null) {
            db = ScheduleItDatabase.getDatabase()
        }
    }
}
