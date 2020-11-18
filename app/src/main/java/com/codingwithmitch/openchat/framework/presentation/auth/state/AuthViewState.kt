package com.codingwithmitch.openchat.framework.presentation.auth.state

import com.codingwithmitch.openchat.business.domain.util.ProjectRegex
import com.codingwithmitch.openchat.business.domain.util.ProjectRegex.PASSWORD_VALIDATION_INFO
import com.codingwithmitch.openchat.framework.presentation.common.TextFieldState
import java.util.regex.Pattern


class AuthViewState(
        var loginEmailState: LoginEmailState = LoginEmailState(""),
        var loginPasswordState: LoginPasswordState = LoginPasswordState(""),
        var showLoginPassword: Boolean = false,
){

    class LoginEmailState(
            value: String
    ): TextFieldState(){

        init {
            text = value
        }

        override fun getLabel(): String {
            return "Email"
        }

        override fun isValid(): Boolean {
            return Pattern.matches(ProjectRegex.EMAIL_VALIDATION_REGEX, text)
        }

        override fun getErrorMessage(): String {
            return "Invalid email: $text"
        }
    }


    class LoginPasswordState(
            value: String
    ): TextFieldState(){

        init {
            text = value
        }

        override fun getLabel(): String {
            return "Password"
        }

        override fun isValid(): Boolean {
            return Pattern.matches(ProjectRegex.PASSWORD_VALIDATION_REGEX, text)
        }

        override fun getErrorMessage(): String {
            return PASSWORD_VALIDATION_INFO
        }


    }

}





















