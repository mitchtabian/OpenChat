package com.codingwithmitch.openchat.framework.presentation.auth.screens

import com.codingwithmitch.openchat.framework.presentation.auth.screens.AuthScreenName.*
import com.codingwithmitch.openchat.framework.presentation.common.Screen

enum class AuthScreenName{
    LOGIN,
    CREATE_ACCOUNT,
    PASSWORD_RESET,
}

/**
 * Class defining the screens we have in the app: login, Create account and Password reset
 */
sealed class AuthScreen: Screen {

    object Login : AuthScreen() {
        override fun name(): String {
            return LOGIN.name
        }
    }

    object CreateAccount : AuthScreen() {
        override fun name(): String {
            return CREATE_ACCOUNT.name
        }
    }

    object PasswordReset : AuthScreen() {
        override fun name(): String {
            return PASSWORD_RESET.name
        }
    }
}


































