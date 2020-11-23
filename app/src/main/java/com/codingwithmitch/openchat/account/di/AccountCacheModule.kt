package com.codingwithmitch.openchat.account.di

import android.content.Context
import androidx.room.Room
import com.codingwithmitch.openchat.account.framework.datasource.cache.mappers.AccountCacheMapper
import com.codingwithmitch.openchat.auth.business.data.cache.AuthCacheDataSource
import com.codingwithmitch.openchat.auth.business.data.cache.AuthCacheDataSourceImpl
import com.codingwithmitch.openchat.auth.framework.datasource.cache.AuthDaoService
import com.codingwithmitch.openchat.auth.framework.datasource.cache.AuthDaoServiceImpl
import com.codingwithmitch.openchat.auth.framework.datasource.cache.database.AccountDao
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
object AccountCacheModule {

    @Singleton
    @Provides
    fun provideAccountCacheMapper(): AccountCacheMapper {
        return AccountCacheMapper()
    }

    @Singleton
    @Provides
    fun provideAccountDao(authDatabase: AuthDatabase): AccountDao {
        return authDatabase.accountDao()
    }


}





















