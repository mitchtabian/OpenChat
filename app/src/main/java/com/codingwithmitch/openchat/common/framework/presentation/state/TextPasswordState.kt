package com.codingwithmitch.openchat.common.framework.presentation.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.codingwithmitch.openchat.common.business.domain.util.ProjectRegex.PASSWORD_VALIDATION_INFO
import com.codingwithmitch.openchat.common.business.domain.util.ProjectRegex.PASSWORD_VALIDATION_REGEX
import java.util.regex.Pattern

open class TextPasswordState(
    value: String,
    isShowing: Boolean = false,
): TextFieldState(){

    var showPassword: Boolean by mutableStateOf(false)

    init {
        text = value
        showPassword = isShowing
    }

    override fun getLabel(): String {
        return "Password"
    }

    override fun isValid(): Boolean {
        return Pattern.matches(PASSWORD_VALIDATION_REGEX, text)
    }

    override fun getErrorMessage(): String {
        return PASSWORD_VALIDATION_INFO
    }
}









