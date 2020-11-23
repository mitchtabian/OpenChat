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
}











