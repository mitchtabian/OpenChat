package com.codingwithmitch.openchat.auth.di

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import com.codingwithmitch.openchat.auth.business.data.cache.AuthCacheDataSource
import com.codingwithmitch.openchat.auth.business.data.network.AuthNetworkDataSource
import com.codingwithmitch.openchat.auth.business.interactors.Login
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Named
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(ApplicationComponent::class)
object AuthModule {

    @Singleton
    @Provides
    fun provideLoginUseCase(
            cacheDataSource: AuthCacheDataSource,
            networkDataSource: AuthNetworkDataSource,
            @Named("auth_preferences") dataStore: DataStore<Preferences>,
    ): Login {
        return Login(cacheDataSource, networkDataSource, dataStore)
    }
}













