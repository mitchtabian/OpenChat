package com.codingwithmitch.openchat.session

import com.codingwithmitch.openchat.auth.business.data.cache.AuthCacheDataSource
import com.codingwithmitch.openchat.auth.business.domain.model.AuthToken
import com.codingwithmitch.openchat.common.business.data.cache.CacheResponseHandler
import com.codingwithmitch.openchat.common.business.domain.state.*
import com.codingwithmitch.openchat.common.business.domain.util.safeCacheCall
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

const val NO_AUTH_TOKEN_FOUND = "No AuthToken found."

class CheckAuthToken (
    private val cacheDataSource: AuthCacheDataSource
){

    suspend fun execute(stateEvent: StateEvent, accountPk: Int): Flow<DataState<SessionState>?> = flow{

        val cacheResult = safeCacheCall(IO){
            cacheDataSource.getTokens()
        }

        val response = object: CacheResponseHandler<SessionState, List<AuthToken>?>(
            response = cacheResult,
            stateEvent = stateEvent,
        ){
            override suspend fun handleSuccess(resultObj: List<AuthToken>?): DataState<SessionState>? {
                // should only be a single token. But find the one that matches the account pk
                if(resultObj != null){
                    var token: AuthToken? = null
                    for (authToken in resultObj){
                        if(authToken.accountId == accountPk){
                            token = authToken
                        }
                    }
                    if(token != null){
                        return DataState.data(
                            response = null,
                            data = SessionState(
                                authToken = token
                            ),
                            stateEvent = stateEvent
                        )
                    }

                }
                return DataState.data(
                    response = Response(
                        message = NO_AUTH_TOKEN_FOUND,
                        uiComponentType = UIComponentType.None(),
                        messageType = MessageType.Info()
                    ),
                    data = null,
                    stateEvent = stateEvent
                )
            }
        }.getResult()

        emit(response)
    }
}














