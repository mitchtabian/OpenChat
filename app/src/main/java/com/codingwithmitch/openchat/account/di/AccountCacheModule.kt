package com.codingwithmitch.openchat.account.di

import com.codingwithmitch.openchat.account.framework.datasource.cache.mappers.AccountCacheMapper
import com.codingwithmitch.openchat.account.framework.datasource.cache.database.AccountDao
import com.codingwithmitch.openchat.common.framework.datasource.cache.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AccountCacheModule {

    @Singleton
    @Provides
    fun provideAccountCacheMapper(): AccountCacheMapper {
        return AccountCacheMapper()
    }

    @Singleton
    @Provides
    fun provideAccountDao(db: AppDatabase): AccountDao {
        return db.accountDao()
    }


}





















