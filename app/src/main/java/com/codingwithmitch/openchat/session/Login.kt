package com.codingwithmitch.openchat.session

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import com.codingwithmitch.openchat.account.business.domain.model.Account
import com.codingwithmitch.openchat.auth.business.data.cache.AuthCacheDataSource
import com.codingwithmitch.openchat.auth.business.data.network.AuthNetworkDataSource
import com.codingwithmitch.openchat.auth.business.domain.model.AuthToken
import com.codingwithmitch.openchat.common.business.data.network.ApiResponseHandler
import com.codingwithmitch.openchat.common.business.domain.state.DataState
import com.codingwithmitch.openchat.common.business.domain.state.StateEvent
import com.codingwithmitch.openchat.common.business.domain.util.printLogD
import com.codingwithmitch.openchat.common.business.domain.util.safeApiCall
import com.codingwithmitch.openchat.common.business.domain.util.safeCacheCall
import com.codingwithmitch.openchat.session.SessionPreferences.KEY_ACCOUNT_PK
import com.codingwithmitch.openchat.util.DEBUG
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Named

class Login (
        private val cacheDataSource: AuthCacheDataSource,
        private val networkDataSource: AuthNetworkDataSource,
        @Named private val authPreferences: DataStore<Preferences>,
){

    suspend fun execute(
        stateEvent: StateEvent,
        email: String,
        password: String
    ): Flow<DataState<SessionState>?> = flow {

        val networkResult = safeApiCall(IO){
            networkDataSource.login(email, password)
        }

        if(DEBUG) delay(2000)

        val response = object: ApiResponseHandler<SessionState, AuthToken>(
            response = networkResult,
            stateEvent = stateEvent
        ){
            override suspend fun handleSuccess(resultObj: AuthToken): DataState<SessionState>? {
                printLogD("Login", "handleSUCESS: ${resultObj}")
                return DataState.data(
                    response = null,
                    data = SessionState(authToken = resultObj),
                    stateEvent = stateEvent
                )
            }
        }.getResult()

        emit(response)

        // Cache the Account information and token.
        response?.data?.authToken?.let { authToken ->
            authToken.accountId?.let { pk ->

                // cache the [Account]
                saveAccountToCache(pk = pk, email = email)

                // Cache the [AuthToken]
                saveAuthTokenToCache(authToken = authToken)

                // Save [Account.pk] to Datastore for auto-login.
                saveAccountPkToPreferences(pk)
            }
        }
    }

    /**
     * Save the account to the cache. Email and PK must match server.
     */
    private suspend fun saveAccountToCache(pk: Int, email: String){
        safeCacheCall(IO){
            cacheDataSource.insertAccount(Account(id = pk, email = email))
        }
    }

    /**
     * Save the auth token to cache
     * 1. Enables auto-login next time app launches.
     * 2. Token can be easily accessed for using in request headers to server.
     *
     * WARNING:
     * AccountEntity must exist for this user to save the token since there is a
     * Foreign key relationship in [AuthTokenCacheEntity]
     */
    private suspend fun saveAuthTokenToCache(authToken: AuthToken){
        // delete old token. (There should only be one)
        safeCacheCall(IO){
            cacheDataSource.deleteTokens()
        }

        // save the new token to the cache
        safeCacheCall(IO){
            cacheDataSource.insertToken(authToken)
        }
    }

    private suspend fun saveAccountPkToPreferences(accountPk: Int){
        authPreferences.edit { preferences ->
            preferences[KEY_ACCOUNT_PK] = accountPk
        }
    }
}


















