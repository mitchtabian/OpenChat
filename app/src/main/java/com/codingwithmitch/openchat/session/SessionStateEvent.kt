package com.codingwithmitch.openchat.session

import com.codingwithmitch.openchat.common.business.domain.state.StateEvent

sealed class SessionStateEvent: StateEvent{

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

    data class CheckAuthTokenEvent(
        var accountPk: Int
    ): SessionStateEvent() {

        override fun errorInfo(): String {
            return "Error checking for a cached AuthToken."
        }

        override fun eventName(): String {
            return "CheckAuthTokenEvent"
        }

        override fun shouldDisplayProgressBar(): Boolean {
            return true
        }
    }


}











