package com.codingwithmitch.openchat.splash.di

import com.codingwithmitch.openchat.auth.business.data.cache.AuthCacheDataSource
import com.codingwithmitch.openchat.splash.business.interactors.CheckAuthToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(ApplicationComponent::class)
object SplashModule {

    @Singleton
    @Provides
    fun provideCheckAuthTokenUseCase(
        cacheDataSource: AuthCacheDataSource,
    ): CheckAuthToken{
        return CheckAuthToken(cacheDataSource)
    }

}