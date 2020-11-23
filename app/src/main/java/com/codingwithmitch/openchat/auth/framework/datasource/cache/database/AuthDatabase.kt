package com.codingwithmitch.openchat.auth.framework.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codingwithmitch.openchat.account.framework.datasource.cache.model.AccountCacheEntity
import com.codingwithmitch.openchat.auth.framework.datasource.cache.model.AuthTokenCacheEntity

@Database(entities = [AuthTokenCacheEntity::class, AccountCacheEntity::class ], version = 1, exportSchema = false)
abstract class AuthDatabase: RoomDatabase() {

    abstract fun authDao(): AuthDao

    abstract fun accountDao(): AccountDao

    companion object{
        val DATABASE_NAME: String = "auth_db"
    }


}