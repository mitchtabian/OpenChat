package com.codingwithmitch.openchat.auth.framework.presentation.navigation

import com.codingwithmitch.openchat.auth.framework.presentation.navigation.AuthScreenName.*
import com.codingwithmitch.openchat.common.framework.presentation.navigation.Screen

enum class AuthScreenName{
    LOGIN,
    CREATE_ACCOUNT,
    PASSWORD_RESET,
}

fun toAuthScreen(name: String): AuthScreen {
    return when(name){
        LOGIN.name ->{
            AuthScreen.Login
        }
        CREATE_ACCOUNT.name ->{
            AuthScreen.CreateAccount
        }
        PASSWORD_RESET.name ->{
            AuthScreen.PasswordReset
        }
        else -> AuthScreen.Login
    }
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


































