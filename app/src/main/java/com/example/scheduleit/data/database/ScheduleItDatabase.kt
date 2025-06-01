package com.example.scheduleit.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.scheduleit.data.dao.*
import com.example.scheduleit.data.models.*
import com.example.scheduleit.data.converter.DateTimeConverters
import com.example.scheduleit.data.models.Task
import com.example.scheduleit.data.models.Reminder
import com.example.scheduleit.data.models.Expense
import com.example.scheduleit.data.models.Comment
import com.example.scheduleit.data.models.AIChatBot
import com.example.scheduleit.data.models.UserProfile


@Database(
    entities = [
        Task::class,
        Reminder::class,
        Expense::class,
        Comment::class,
        AIChatBot::class,
        UserProfile::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateTimeConverters::class)
abstract class ScheduleItDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
    abstract fun reminderDao(): ReminderDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun commentDao(): CommentDao
    abstract fun aiChatBotDao(): AIChatBotDao
    abstract fun userProfileDao(): UserProfileDao

    companion object {
        @Volatile
        private var INSTANCE: ScheduleItDatabase? = null

        fun getDatabase(context: Context): ScheduleItDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ScheduleItDatabase::class.java,
                    "scheduleit_database"
                )

                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
