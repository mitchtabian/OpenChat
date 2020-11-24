package com.codingwithmitch.openchat.common.framework.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codingwithmitch.openchat.account.framework.datasource.cache.model.AccountCacheEntity
import com.codingwithmitch.openchat.account.framework.datasource.cache.database.AccountDao
import com.codingwithmitch.openchat.session.framework.datasource.cache.database.AuthTokenDao
import com.codingwithmitch.openchat.session.framework.datasource.cache.model.AuthTokenCacheEntity

@Database(
    entities = [
        AuthTokenCacheEntity::class,
        AccountCacheEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun authDao(): AuthTokenDao

    abstract fun accountDao(): AccountDao

    companion object{
        val DATABASE_NAME: String = "app_db"
    }


}