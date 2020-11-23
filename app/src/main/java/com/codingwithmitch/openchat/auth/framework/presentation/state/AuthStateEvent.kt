package com.codingwithmitch.openchat.auth.framework.presentation.state

import com.codingwithmitch.openchat.common.business.domain.state.StateEvent

sealed class AuthStateEvent: StateEvent {

    data class LoginEvent(
        val email: String,
        val password: String
    ): AuthStateEvent() {

        override fun errorInfo(): String {
            return "Error trying to login."
        }

        override fun eventName(): String {
            return "LoginEvent"
        }

        override fun shouldDisplayProgressBar(): Boolean {
            return true
        }
    }
}