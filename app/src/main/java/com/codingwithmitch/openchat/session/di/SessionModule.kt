package com.codingwithmitch.openchat.session.di

import android.app.Application
import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import com.codingwithmitch.openchat.auth.business.data.cache.AuthCacheDataSource
import com.codingwithmitch.openchat.auth.business.data.network.AuthNetworkDataSource
import com.codingwithmitch.openchat.auth.business.interactors.Login
import com.codingwithmitch.openchat.auth.business.interactors.Logout
import com.codingwithmitch.openchat.session.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Named
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(ApplicationComponent::class)
object SessionModule {

    @Singleton
    @Provides
    @Named("auth_preferences")
    fun provideAuthDatastore(@ApplicationContext app: Context): DataStore<Preferences>{
        return app.createDataStore(name = "auth_preferences")
    }

    @Singleton
    @Provides
    fun provideLogoutUseCase(
            cacheDataSource: AuthCacheDataSource,
            @Named("auth_preferences") dataStore: DataStore<Preferences>,
    ): Logout {
        return Logout(cacheDataSource, dataStore)
    }

    @Singleton
    @Provides
    fun provideSessionManager(logout: Logout): SessionManager {
        return SessionManager(logout)
    }


}













