package com.codingwithmitch.openchat.session.di

import com.codingwithmitch.openchat.auth.business.data.cache.AuthCacheDataSource
import com.codingwithmitch.openchat.auth.business.data.network.AuthNetworkDataSource
import com.codingwithmitch.openchat.auth.business.interactors.Login
import com.codingwithmitch.openchat.session.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(ApplicationComponent::class)
object SessionModule {

    @Singleton
    @Provides
    fun provideLoginUseCase(
        cacheDataSource: AuthCacheDataSource,
        networkDataSource: AuthNetworkDataSource,
    ): Login {
        return Login(cacheDataSource, networkDataSource)
    }

    @Singleton
    @Provides
    fun provideSessionManager(login: Login): SessionManager {
        return SessionManager(login)
    }
}













