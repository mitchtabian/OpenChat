package com.codingwithmitch.openchat.session.di

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import com.codingwithmitch.openchat.session.business.data.cache.AuthCacheDataSource
import com.codingwithmitch.openchat.session.business.data.network.AuthNetworkDataSource
import com.codingwithmitch.openchat.session.business.interactors.Login
import com.codingwithmitch.openchat.session.business.interactors.Logout
import com.codingwithmitch.openchat.session.framework.presentation.SessionManager
import com.codingwithmitch.openchat.session.business.interactors.CheckAuthToken
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
    fun provideLoginUseCase(
        cacheDataSource: AuthCacheDataSource,
        networkDataSource: AuthNetworkDataSource,
        @Named("auth_preferences") dataStore: DataStore<Preferences>,
    ): Login {
        return Login(cacheDataSource, networkDataSource, dataStore)
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
    fun provideCheckAuthTokenUseCase(
        cacheDataSource: AuthCacheDataSource,
    ): CheckAuthToken {
        return CheckAuthToken(cacheDataSource)
    }

    @Singleton
    @Provides
    fun provideSessionManager(
        logout: Logout,
        login: Login,
        checkAuthToken: CheckAuthToken,
        @Named("auth_preferences") dataStore: DataStore<Preferences>,
    ): SessionManager {
        return SessionManager(
            logout, login, checkAuthToken, dataStore
        )
    }


}













