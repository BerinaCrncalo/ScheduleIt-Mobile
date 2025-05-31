package com.example.scheduleit.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.scheduleit.data.models.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insertExpense(expense: Expense)

    @Update
    suspend fun updateExpense(expense: Expense)

    @Query("SELECT * FROM expense WHERE id = :id LIMIT 1")
    fun getExpenseById(id: Int): Flow<Expense>

    @Query("SELECT * FROM expense WHERE category = :category")
    fun getExpensesByCategory(category: String): Flow<List<Expense>>
}
