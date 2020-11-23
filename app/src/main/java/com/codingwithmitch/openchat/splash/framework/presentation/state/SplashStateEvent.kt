package com.codingwithmitch.openchat.splash.framework.presentation.state

import com.codingwithmitch.openchat.common.business.domain.state.StateEvent

sealed class SplashStateEvent: StateEvent {

    data class CheckAuthTokenEvent(
        var accountPk: Int
    ): SplashStateEvent() {

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