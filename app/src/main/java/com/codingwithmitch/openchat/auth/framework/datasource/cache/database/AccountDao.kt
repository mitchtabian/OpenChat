package com.codingwithmitch.openchat.auth.framework.datasource.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codingwithmitch.openchat.account.framework.datasource.cache.model.AccountCacheEntity

@Dao
interface AccountDao {

    // Insert a new token into the cache
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: AccountCacheEntity): Long

    // There should only be one token at any given time.
    @Query("SELECT * FROM account WHERE id = :id")
    suspend fun getAccount(id: Int): AccountCacheEntity

}



















