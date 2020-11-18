package com.codingwithmitch.openchat.framework.presentation.auth

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.codingwithmitch.openchat.framework.presentation.auth.state.AuthViewState
import com.codingwithmitch.openchat.framework.presentation.auth.state.AuthViewState.*
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

    fun setLoginEmailChanged(email: String){
        val new = buildNewViewState(loginEmailState = LoginEmailState(email))
        setViewState(new)
    }

    fun onLoginPasswordChanged(password: String){
        val new = buildNewViewState(loginPasswordState = LoginPasswordState(password))
        setViewState(new)
    }

    fun setShowLoginPassword(showPassword: Boolean){
        val new = buildNewViewState(showLoginPassword = showPassword)
        setViewState(new)
    }

    private fun buildNewViewState(
            loginEmailState: LoginEmailState? = null,
            loginPasswordState: LoginPasswordState? = null,
            showLoginPassword: Boolean? = null,
    ): AuthViewState{
        val current = getCurrentViewState()
        return AuthViewState(
                loginEmailState = loginEmailState?: current.loginEmailState,
                loginPasswordState = loginPasswordState?: current.loginPasswordState,
                showLoginPassword = showLoginPassword?: current.showLoginPassword,
        )
    }
}
















