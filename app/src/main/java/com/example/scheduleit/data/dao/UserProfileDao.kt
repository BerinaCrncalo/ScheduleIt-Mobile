package com.example.scheduleit.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.scheduleit.data.models.UserProfile
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfileDao {
    // Insert a new user profile
    @Insert
    suspend fun insertUserProfile(userProfile: UserProfile)

    // Update an existing user profile
    @Update
    suspend fun update(userProfile: UserProfile)

    // Get a user profile by ID
    @Query("SELECT * FROM user_profile WHERE id = :id LIMIT 1")
    fun getUserProfile(id: Int): Flow<UserProfile>
}
