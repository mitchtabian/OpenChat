package com.codingwithmitch.openchat.auth.business.interactors

import com.codingwithmitch.openchat.auth.business.data.cache.AuthCacheDataSource
import com.codingwithmitch.openchat.common.business.domain.state.DataState
import com.codingwithmitch.openchat.common.business.domain.state.StateEvent
import com.codingwithmitch.openchat.common.business.domain.util.safeCacheCall
import com.codingwithmitch.openchat.session.SessionManager
import com.codingwithmitch.openchat.session.SessionState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@ExperimentalCoroutinesApi
class Logout (
        private val cacheDataSource: AuthCacheDataSource,
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
    }
}


















