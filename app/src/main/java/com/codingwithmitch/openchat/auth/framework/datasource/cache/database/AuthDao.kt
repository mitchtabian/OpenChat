package com.codingwithmitch.openchat.auth.framework.datasource.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codingwithmitch.openchat.auth.framework.datasource.cache.model.AuthTokenCacheEntity

@Dao
interface AuthDao {

    // Insert a new token into the cache
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToken(authToken: AuthTokenCacheEntity): Long

    // There should only be one token at any given time.
    @Query("SELECT * FROM auth_token")
    suspend fun getAuthToken(): List<AuthTokenCacheEntity>

    // Delete all tokens from cache (Should only be one)
    @Query("DELETE FROM auth_token")
    suspend fun deleteTokens()


//    // Insert a new token into the cache
//    @Insert
//    suspend fun insertToken(authToken: AuthTokenCacheEntity)
//
//    // There should only be one token at any given time.
//    @Query("SELECT * FROM auth_token")
//    suspend fun getAuthToken()
//
//    // Delete all tokens from cache (Should only be one)
//    @Query("DELETE FROM auth_token")
//    suspend fun deleteTokens()
}



















