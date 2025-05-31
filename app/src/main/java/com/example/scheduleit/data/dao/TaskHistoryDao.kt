package com.example.scheduleit.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.scheduleit.data.models.TaskHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskHistoryDao {
    @Insert
    suspend fun insertTaskHistory(taskHistory: TaskHistory)

    @Query("SELECT * FROM task_history WHERE taskId = :taskId")
    fun getTaskHistoryByTaskId(taskId: Int): Flow<List<TaskHistory>>
}
