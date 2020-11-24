package com.codingwithmitch.openchat.session.di

import android.content.Context
import androidx.room.Room
import com.codingwithmitch.openchat.account.framework.datasource.cache.mappers.AccountCacheMapper
import com.codingwithmitch.openchat.session.business.data.cache.AuthCacheDataSource
import com.codingwithmitch.openchat.session.business.data.cache.AuthCacheDataSourceImpl
import com.codingwithmitch.openchat.session.framework.datasource.cache.AuthTokenDaoService
import com.codingwithmitch.openchat.session.framework.datasource.cache.AuthTokenDaoServiceImpl
import com.codingwithmitch.openchat.account.framework.datasource.cache.database.AccountDao
import com.codingwithmitch.openchat.session.framework.datasource.cache.database.AuthTokenDao
import com.codingwithmitch.openchat.common.framework.datasource.cache.database.AppDatabase
import com.codingwithmitch.openchat.session.framework.datasource.cache.mappers.AuthCacheMapper
import com.codingwithmitch.openchat.common.business.domain.util.DateUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AuthCacheModule {

    @Singleton
    @Provides
    fun provideCacheMapper(dateUtil: DateUtil): AuthCacheMapper{
        return AuthCacheMapper(dateUtil)
    }

    @Singleton
    @Provides
    fun provideAuthDao(authDatabase: AppDatabase): AuthTokenDao {
        return authDatabase.authDao()
    }

    @Singleton
    @Provides
    fun provideAuthDaoService(
        authTokenDao: AuthTokenDao,
        accountDao: AccountDao,
        mapper: AuthCacheMapper,
        accountMapper: AccountCacheMapper,
        dateUtil: DateUtil,
    ): AuthTokenDaoService {
        return AuthTokenDaoServiceImpl(
                authTokenDao = authTokenDao,
                accountDao = accountDao,
                authCacheMapper = mapper,
                accountCacheMapper = accountMapper,
                dateUtil = dateUtil
        )
    }

    @Singleton
    @Provides
    fun provideAuthCacheDataSource(
        authTokenDaoService: AuthTokenDaoService,
    ): AuthCacheDataSource{
        return AuthCacheDataSourceImpl(authTokenDaoService)
    }

}





















