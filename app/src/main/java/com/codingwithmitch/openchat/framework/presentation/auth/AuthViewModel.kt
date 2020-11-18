package com.codingwithmitch.openchat.framework.presentation.auth

import androidx.compose.ui.focus.ExperimentalFocus
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.codingwithmitch.openchat.framework.presentation.auth.state.AuthViewState
import com.codingwithmitch.openchat.framework.presentation.auth.state.EmailState
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
        val new = buildNewViewState(username = username)
        setViewState(new)
    }

    fun setEmail(email: String){
        val new = buildNewViewState(emailState = EmailState(email))
        setViewState(new)
    }

    fun setPassword(password: String){
        val new = buildNewViewState(password = password)
        setViewState(new)
    }

    fun setShowPassword(showPassword: Boolean){
        val new = buildNewViewState(showPassword = showPassword)
        setViewState(new)
    }

    private fun buildNewViewState(
            emailState: EmailState? = null,
            username: String? = null,
            password: String? = null,
            showPassword: Boolean? = null,
    ): AuthViewState{
        val current = getCurrentViewState()
        return AuthViewState(
                emailState = emailState?: current.emailState,
                username = username?: current.username,
                password = password?: current.password,
                showPassword = showPassword?: current.showPassword,
        )
    }
}
















