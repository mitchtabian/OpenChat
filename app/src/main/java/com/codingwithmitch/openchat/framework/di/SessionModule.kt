package com.codingwithmitch.openchat.framework.di

import com.codingwithmitch.openchat.framework.presentation.session.SessionManager
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
    fun provideSessionManager(): SessionManager{
        return SessionManager()
    }
}













