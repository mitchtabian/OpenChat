package com.codingwithmitch.openchat.auth.business.interactors

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import com.codingwithmitch.openchat.auth.business.data.cache.AuthCacheDataSource
import com.codingwithmitch.openchat.common.business.domain.state.DataState
import com.codingwithmitch.openchat.common.business.domain.state.StateEvent
import com.codingwithmitch.openchat.common.business.domain.util.safeCacheCall
import com.codingwithmitch.openchat.session.SessionManager
import com.codingwithmitch.openchat.session.SessionState
import com.codingwithmitch.openchat.splash.framework.datasource.preferences.SplashPreference
import com.codingwithmitch.openchat.splash.framework.datasource.preferences.SplashPreference.KEY_ACCOUNT_PK
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Named

@ExperimentalCoroutinesApi
class Logout (
    private val cacheDataSource: AuthCacheDataSource,
    @Named private val authPreferences: DataStore<Preferences>,
){

    fun execute(stateEvent: StateEvent): Flow<DataState<SessionState>?> = flow {

        // Delete token from cache
        safeCacheCall(IO){
            cacheDataSource.deleteTokens()
        }

        emit(
                DataState.data(
                        response = null,
                        data = SessionState(),
                        stateEvent = stateEvent
                )
        )

        // Remove [Account.pk] from Datastore
        clearAccountPkFromPreferences()
    }

    private suspend fun clearAccountPkFromPreferences(){
        authPreferences.edit { preferences ->
            preferences[KEY_ACCOUNT_PK] = -1
        }
    }
}


















