package com.codingwithmitch.openchat.session

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import com.codingwithmitch.openchat.auth.business.data.cache.AuthCacheDataSource
import com.codingwithmitch.openchat.common.business.domain.state.*
import com.codingwithmitch.openchat.common.business.domain.util.safeCacheCall
import com.codingwithmitch.openchat.session.SessionPreferences.KEY_ACCOUNT_PK
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Named

const val LOGOUT_SUCCESS = "com.codingwithmitch.openchat.session.LOGOUT_SUCCESS"

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
                        response = Response(
                            message = LOGOUT_SUCCESS,
                            uiComponentType = UIComponentType.None(),
                            messageType = MessageType.Info()
                        ),
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


















