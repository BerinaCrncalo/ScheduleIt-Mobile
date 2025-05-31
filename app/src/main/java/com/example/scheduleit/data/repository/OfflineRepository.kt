package com.example.scheduleit.data.repository

import com.example.scheduleit.data.dao.*
import com.example.scheduleit.data.models.*
import kotlinx.coroutines.flow.Flow

/**
 * Repository implementation for offline tasks management.
 *
 * This repository provides methods to interact with tasks, reminders, expenses, comments, and user profiles.
 */
class OfflineRepository(
    private val taskDao: TaskDao,
    private val reminderDao: ReminderDao,
    private val expenseDao: ExpenseDao,
    private val commentDao: CommentDao,
    private val aiChatBotDao: AIChatBotDao,
    private val userProfileDao: UserProfileDao
) : ScheduleItRepository {

    // Task-related operations
    override fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()
    override fun getTaskById(id: Int): Flow<Task> = taskDao.getTaskById(id)
    override suspend fun insertTask(task: Task) = taskDao.insertTask(task)
    override suspend fun updateTask(task: Task) = taskDao.updateTask(task)
    override suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)

    // Reminder-related operations
    override suspend fun insertReminder(reminder: Reminder) = reminderDao.insertReminder(reminder)
    override fun getRemindersByType(type: String): Flow<List<Reminder>> = reminderDao.getRemindersByType(type)

    // Expense-related operations
    override suspend fun insertExpense(expense: Expense) = expenseDao.insertExpense(expense)
    override fun getExpensesByCategory(category: String): Flow<List<Expense>> = expenseDao.getExpensesByCategory(category)

    // Comment-related operations
    override suspend fun insertComment(comment: Comment) = commentDao.insertComment(comment)
    override fun getCommentsByTaskId(taskId: Int): Flow<List<Comment>> = commentDao.getCommentsByTaskId(taskId)

    // AI ChatBot-related operations
    override suspend fun insertAIChatBot(aiChatBot: AIChatBot) = aiChatBotDao.insertAIChatBot(aiChatBot)
    override fun getAIChatHistory(taskId: Int): Flow<List<AIChatBot>> = aiChatBotDao.getAIChatHistory(taskId)

    // User Profile-related operations
    override suspend fun insertUserProfile(userProfile: UserProfile) = userProfileDao.insertUserProfile(userProfile)
    override fun getUserProfile(id: Int): Flow<UserProfile> = userProfileDao.getUserProfile(id)
}
