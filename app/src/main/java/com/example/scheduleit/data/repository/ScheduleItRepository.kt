package com.example.scheduleit.data.repository

import com.example.scheduleit.data.models.*
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining operations for various data entities.
 *
 * This interface provides methods to interact with tasks, reminders, expenses, comments, and user profiles.
 */
interface ScheduleItRepository {
    // Task-related operations
    fun getAllTasks(): Flow<List<Task>>
    fun getTaskById(id: Int): Flow<Task>
    suspend fun insertTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)

    // Reminder-related operations
    suspend fun insertReminder(reminder: Reminder)
    fun getRemindersByType(type: String): Flow<List<Reminder>>

    // Expense-related operations
    suspend fun insertExpense(expense: Expense)
    fun getExpensesByCategory(category: String): Flow<List<Expense>>

    // Comment-related operations
    suspend fun insertComment(comment: Comment)
    fun getCommentsByTaskId(taskId: Int): Flow<List<Comment>>

    // AI ChatBot-related operations
    suspend fun insertAIChatBot(aiChatBot: AIChatBot)
    fun getAIChatHistory(taskId: Int): Flow<List<AIChatBot>>

    // User Profile-related operations
    suspend fun insertUserProfile(userProfile: UserProfile)
    fun getUserProfile(id: Int): Flow<UserProfile>
}
