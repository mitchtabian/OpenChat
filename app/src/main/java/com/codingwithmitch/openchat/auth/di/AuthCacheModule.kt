package com.codingwithmitch.openchat.auth.di

import android.content.Context
import androidx.room.Room
import com.codingwithmitch.openchat.auth.business.data.cache.AuthCacheDataSource
import com.codingwithmitch.openchat.auth.business.data.cache.AuthCacheDataSourceImpl
import com.codingwithmitch.openchat.auth.framework.datasource.cache.AuthDaoService
import com.codingwithmitch.openchat.auth.framework.datasource.cache.AuthDaoServiceImpl
import com.codingwithmitch.openchat.auth.framework.datasource.cache.database.AuthDao
import com.codingwithmitch.openchat.auth.framework.datasource.cache.database.AuthDatabase
import com.codingwithmitch.openchat.auth.framework.datasource.cache.mappers.AuthCacheMapper
import com.codingwithmitch.openchat.common.business.domain.util.DateUtil
import com.codingwithmitch.openchat.common.framework.presentation.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.FragmentScoped
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
    fun provideAuthDb(@ApplicationContext app: Context): AuthDatabase {
        return Room
            .databaseBuilder(app, AuthDatabase::class.java, AuthDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthDao(authDatabase: AuthDatabase): AuthDao {
        return authDatabase.authDao()
    }

    @Singleton
    @Provides
    fun provideAuthDaoService(
        authDao: AuthDao,
        mapper: AuthCacheMapper,
        dateUtil: DateUtil,
    ): AuthDaoService{
        return AuthDaoServiceImpl(
            authDao = authDao,
            authCacheMapper = mapper,
            dateUtil = dateUtil
        )
    }

    @Singleton
    @Provides
    fun provideAuthCacheDataSource(
        authDaoService: AuthDaoService,
    ): AuthCacheDataSource{
        return AuthCacheDataSourceImpl(authDaoService)
    }

}





















