package com.codingwithmitch.openchat.auth.framework.presentation.state

import com.codingwithmitch.openchat.auth.business.domain.model.AuthToken
import com.codingwithmitch.openchat.auth.framework.presentation.navigation.AuthScreen
import com.codingwithmitch.openchat.common.framework.presentation.state.TextEmailState
import com.codingwithmitch.openchat.common.framework.presentation.state.TextFieldState
import com.codingwithmitch.openchat.common.framework.presentation.state.TextPasswordState
import com.codingwithmitch.openchat.common.framework.presentation.state.TextUsernameState

const val BUNDLE_KEY_AUTH_VIEWSTATE = "com.codingwithmitch.openchat.auth.framework.presentation.state.AuthViewState"

class AuthViewState(

    // Will be set on successful login
    var authToken: AuthToken? = null,

    // LoginScreen
    var loginEmailState: LoginEmailState = LoginEmailState(""),
    var loginPasswordState: LoginPasswordState = LoginPasswordState(""),

    // PasswordResetScreen
    var passwordResetEmailState: PasswordResetEmailState = PasswordResetEmailState(""),

    // CreateAccountScreen
    var createEmailState: CreateEmailState = CreateEmailState(""),
    var createUsernameState: CreateUsernameState = CreateUsernameState(""),
    var createPasswordState: CreatePasswordState = CreatePasswordState(
            CreatePasswordState.Password1State(""),
            CreatePasswordState.Password2State("")
    ),

    // Manage navigation
    var screen: AuthScreen = AuthScreen.Login
){

    /**
     * LoginScreen variables
     */
    class LoginEmailState(
            value: String
    ): TextEmailState(value)

    class LoginPasswordState(
            value: String,
            showPassword: Boolean = false,
    ): TextPasswordState(value, showPassword)


    /**
     * PasswordResetScreen variables
     */
    class PasswordResetEmailState(
            value: String
    ): TextEmailState(value)


    /**
     * CreateAccountScreen variables
     */
    class CreateEmailState(
            value: String
    ): TextEmailState(value)

    class CreateUsernameState(
            value: String
    ): TextUsernameState(value)

    class CreatePasswordState(
            val password1: Password1State,
            val password2: Password2State
    ): TextFieldState(){

        init {
            // force errors to be enabled if `isValid` fails
            wasFocused = true
            checkEnableShowErrors()
        }

        class Password1State(
                value: String,
                showPassword: Boolean = false,
        ): TextPasswordState(value, showPassword)


        class Password2State(
                value: String,
                showPassword: Boolean = false,
        ): TextPasswordState(value, showPassword){

            override fun getLabel(): String {
                return "Confirm password"
            }
        }

        override fun getLabel(): String {
            return "NONE" // Not used
        }

        override fun isValid(): Boolean {
            return password1.text == password2.text
        }

        override fun getErrorMessage(): String {
            return "Passwords must match."
        }


    }

}

































