package com.codingwithmitch.openchat.framework.presentation.auth

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.codingwithmitch.openchat.framework.presentation.auth.state.AuthViewState
import com.codingwithmitch.openchat.framework.presentation.common.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@ExperimentalCoroutinesApi
class AuthViewModel
@ViewModelInject
constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
): BaseViewModel<AuthViewState>(){

    val email: MutableStateFlow<String> = MutableStateFlow("")

    fun setUsername(username: String){
        val current = getCurrentViewStateOrNew()
        current.username = username
        setViewState(current)
    }

    fun setEmail(email: String){
        val current = getCurrentViewStateOrNew()
        current.email = email
        setViewState(current)
    }

    override fun initNewViewState(): AuthViewState {
        return AuthViewState()
    }
}
















