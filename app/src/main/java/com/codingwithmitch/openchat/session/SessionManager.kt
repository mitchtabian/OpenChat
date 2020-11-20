package com.codingwithmitch.openchat.session

import android.util.Log
import com.codingwithmitch.openchat.auth.business.domain.model.AuthToken
import com.codingwithmitch.openchat.auth.business.interactors.Login
import com.codingwithmitch.openchat.auth.framework.presentation.state.AuthStateEvent
import com.codingwithmitch.openchat.common.business.data.util.GenericErrors
import com.codingwithmitch.openchat.common.business.domain.state.*
import com.codingwithmitch.openchat.common.framework.presentation.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class SessionManager
@Inject
constructor(
        private val loginUseCase: Login
) {

    private val _sessionState: MutableStateFlow<SessionState?> = MutableStateFlow(null)

    val sessionState: StateFlow<SessionState?> get() =  _sessionState

    suspend fun setStateEvent(stateEvent: SessionStateEvent){
        val job: Flow<DataState<SessionState>?> = when(stateEvent){

            is SessionStateEvent.LoginEvent -> {
                loginUseCase.execute(
                        stateEvent = stateEvent,
                        email = stateEvent.email,
                        password = stateEvent.password
                )
            }
        }
        job.onEach { dataState ->
            dataState?.let { dState ->
                withContext(Dispatchers.Main){
                    dataState.data?.let { data ->
                        data.authToken?.let { authToken ->
                            onLoginSuccess(authToken)
                        }
                    }
                    dataState.stateMessage?.let { stateMessage ->
                        Log.d(TAG, "setStateEvent: GOT STATE MSG: ${stateMessage.response.message}")
                    }
                    dataState.stateEvent?.let { stateEvent ->
                    }
                }
            }
        }.launchIn(CoroutineScope(IO))
    }

    fun onLoginSuccess(authToken: AuthToken){
        _sessionState.value = SessionState(authToken)
    }

    fun onLogout(){
        _sessionState.value = SessionState(authToken = null)
    }
}














