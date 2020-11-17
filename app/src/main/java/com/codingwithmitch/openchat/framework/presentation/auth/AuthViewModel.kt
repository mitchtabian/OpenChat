package com.codingwithmitch.openchat.framework.presentation.auth

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.codingwithmitch.openchat.framework.presentation.auth.state.AuthViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@ExperimentalCoroutinesApi
class AuthViewModel
@ViewModelInject
constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _viewState: MutableStateFlow<AuthViewState> = MutableStateFlow(AuthViewState())

    val viewState: StateFlow<AuthViewState> get() =  _viewState

    fun setViewState(viewState: AuthViewState){
        _viewState.value = viewState
    }

    private fun getCurrentViewState(): AuthViewState {
        return _viewState.value
    }

    fun setUsername(username: String){
        val current = getCurrentViewState()
        val new = AuthViewState(
                email = current.email,
                username = username,
                password = current.password,
        )
        setViewState(new)
    }

    fun setEmail(email: String){
        val current = getCurrentViewState()
        val new = AuthViewState(
                email = email,
                username = current.username,
                password = current.password
        )
        setViewState(new)
    }

    fun setPassword(password: String){
        val current = getCurrentViewState()
        val new = AuthViewState(
                email = current.email,
                username = current.username,
                password = password
        )
        setViewState(new)
    }

}
















