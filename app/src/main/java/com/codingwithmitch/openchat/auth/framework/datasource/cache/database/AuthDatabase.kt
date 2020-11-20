package com.codingwithmitch.openchat.auth.framework.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codingwithmitch.openchat.account.framework.datasource.cache.AccountEntity
import com.codingwithmitch.openchat.auth.framework.datasource.cache.model.AuthTokenCacheEntity

@Database(entities = [AuthTokenCacheEntity::class, AccountEntity::class ], version = 1, exportSchema = false)
abstract class AuthDatabase: RoomDatabase() {

    abstract fun authDao(): AuthDao

    companion object{
        val DATABASE_NAME: String = "auth_db"
    }


}