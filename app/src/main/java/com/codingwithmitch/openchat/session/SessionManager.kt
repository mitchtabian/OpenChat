package com.codingwithmitch.openchat.session

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codingwithmitch.openchat.auth.business.domain.model.AuthToken
import com.codingwithmitch.openchat.auth.business.interactors.Login
import com.codingwithmitch.openchat.auth.business.interactors.Logout
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
        private val logoutUseCase: Logout,
) {

    private val _sessionState: MutableLiveData<SessionState?> = MutableLiveData()

    val sessionState: LiveData<SessionState?> get() =  _sessionState

    suspend fun setStateEvent(stateEvent: SessionStateEvent){
        val job: Flow<DataState<SessionState>?> = when(stateEvent){

            is SessionStateEvent.LogoutEvent -> {
                logoutUseCase.execute(stateEvent = stateEvent)
            }
        }
        job.onEach { dataState ->
            dataState?.let { dState ->
                withContext(Dispatchers.Main){
                    dataState.data?.let { data ->
                        if(data.authToken == null){
                            onLogout()
                        }
                    }
                    dataState.stateMessage?.let { stateMessage ->
                        // TODO("Update UI")
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

    private fun onLogout(){
        _sessionState.value = SessionState(authToken = null)
    }
}














