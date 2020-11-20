package com.codingwithmitch.openchat.auth.business.interactors

import com.codingwithmitch.openchat.auth.business.data.cache.AuthCacheDataSource
import com.codingwithmitch.openchat.auth.business.data.network.AuthNetworkDataSource
import com.codingwithmitch.openchat.auth.business.domain.model.AuthToken
import com.codingwithmitch.openchat.auth.framework.datasource.network.model.AuthTokenNetworkEntity
import com.codingwithmitch.openchat.auth.framework.presentation.state.AuthViewState
import com.codingwithmitch.openchat.common.business.data.network.ApiResponseHandler
import com.codingwithmitch.openchat.common.business.data.network.ApiResult
import com.codingwithmitch.openchat.common.business.domain.state.DataState
import com.codingwithmitch.openchat.common.business.domain.state.Response
import com.codingwithmitch.openchat.common.business.domain.state.StateEvent
import com.codingwithmitch.openchat.common.business.domain.util.printLogD
import com.codingwithmitch.openchat.common.business.domain.util.safeApiCall
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
                printLogD("Login", "handleSuccess: ${resultObj}")
                return DataState.data(
                    response = null,
                    data = SessionState(authToken = resultObj),
                    stateEvent = stateEvent
                )
            }
        }.getResult()

        emit(response)

        // TODO("UPDATE THE CACHE")
    }
}
















