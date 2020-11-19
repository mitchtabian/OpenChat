package com.codingwithmitch.openchat.framework.presentation.session

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class SessionManager {

    private val _authState: MutableStateFlow<AuthState> = MutableStateFlow(AuthState())

    val authState: StateFlow<AuthState> get() =  _authState

    fun onAuthenticate(){
        _authState.value = AuthState(true)
    }

    fun onLogout(){
        _authState.value = AuthState(false)
    }
}

class AuthState(
        var isAuthenticated: Boolean = false
)













