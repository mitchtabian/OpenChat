package com.codingwithmitch.openchat.auth.business.interactors

import com.codingwithmitch.openchat.account.business.domain.model.Account
import com.codingwithmitch.openchat.auth.business.data.cache.AuthCacheDataSource
import com.codingwithmitch.openchat.auth.business.data.network.AuthNetworkDataSource
import com.codingwithmitch.openchat.auth.business.domain.model.AuthToken
import com.codingwithmitch.openchat.common.business.data.cache.CacheResponseHandler
import com.codingwithmitch.openchat.common.business.data.network.ApiResponseHandler
import com.codingwithmitch.openchat.common.business.domain.state.DataState
import com.codingwithmitch.openchat.common.business.domain.state.StateEvent
import com.codingwithmitch.openchat.common.business.domain.util.printLogD
import com.codingwithmitch.openchat.common.business.domain.util.safeApiCall
import com.codingwithmitch.openchat.common.business.domain.util.safeCacheCall
import com.codingwithmitch.openchat.session.SessionState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Login (
    private val cacheDataSource: AuthCacheDataSource,
    private val networkDataSource: AuthNetworkDataSource
){

    suspend fun execute(
        stateEvent: StateEvent,
        email: String,
        password: String
    ): Flow<DataState<SessionState>?> = flow {

        val networkResult = safeApiCall(IO){
            networkDataSource.login(email, password)
        }

        val response = object: ApiResponseHandler<SessionState, AuthToken>(
            response = networkResult,
            stateEvent = stateEvent
        ){
            override suspend fun handleSuccess(resultObj: AuthToken): DataState<SessionState>? {
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
                saveAccountToCache(pk = pk, email = email)

                saveAuthTokenToCache(authToken = authToken)
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
        val cacheResult = safeCacheCall(IO){
            cacheDataSource.insertToken(authToken)
        }

        val response = object: CacheResponseHandler<Long, Long>(
                response = cacheResult,
                stateEvent = null,
        ){
            override suspend fun handleSuccess(resultObj: Long): DataState<Long>? {
                    return DataState.data(
                            response = null,
                            data = resultObj,
                            stateEvent = null,
                    )
            }
        }.getResult()
    }
}


















