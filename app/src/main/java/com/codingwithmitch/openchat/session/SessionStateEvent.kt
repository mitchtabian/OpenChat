package com.codingwithmitch.openchat.session

import com.codingwithmitch.openchat.common.business.domain.state.StateEvent

sealed class SessionStateEvent: StateEvent{

    data class LoginEvent(
        val email: String,
        val password: String
    ): SessionStateEvent() {

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

    class LogoutEvent: SessionStateEvent() {

        override fun errorInfo(): String {
            return "Error logging out."
        }

        override fun eventName(): String {
            return "LogoutEvent"
        }

        override fun shouldDisplayProgressBar(): Boolean {
            return true
        }
    }
}











