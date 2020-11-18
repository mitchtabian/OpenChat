package com.codingwithmitch.openchat.framework.presentation.auth.state

import com.codingwithmitch.openchat.framework.presentation.common.TextFieldState
import java.util.regex.Pattern


class AuthViewState(
    var emailState: EmailState = EmailState(""),
    var username: String = "",
    var password: String = "",
    var showPassword: Boolean = false,

)

class EmailState(
        value: String
): TextFieldState(){

    private val EMAIL_VALIDATION_REGEX = "^(.+)@(.+)\$"

    init {
        text = value
    }

    override fun isValid(): Boolean {
        return Pattern.matches(EMAIL_VALIDATION_REGEX, text)
    }

    override fun getErrorMessage(): String {
        return "Invalid email: $text"
    }
}
















