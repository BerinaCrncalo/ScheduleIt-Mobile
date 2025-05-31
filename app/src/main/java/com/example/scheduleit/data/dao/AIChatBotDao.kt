package com.example.scheduleit.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.scheduleit.data.models.AIChatBot
import kotlinx.coroutines.flow.Flow

@Dao
interface AIChatBotDao {
    @Insert
    suspend fun insertAIChatBot(aiChatBot: AIChatBot): Long

    @Query("SELECT * FROM ai_chat_bot WHERE taskId = :taskId")
    fun getAIChatHistory(taskId: Int): Flow<List<AIChatBot>>
}
