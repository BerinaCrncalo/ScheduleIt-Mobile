package com.example.scheduleit.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.scheduleit.data.models.Comment
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {
    @Insert
    suspend fun insertComment(comment: Comment)

    @Query("SELECT * FROM comment WHERE taskId = :taskId")
    fun getCommentsByTaskId(taskId: Int): Flow<List<Comment>>
}
