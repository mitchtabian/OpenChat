package com.codingwithmitch.openchat.framework.presentation.common

import com.codingwithmitch.openchat.business.domain.util.ProjectRegex
import com.codingwithmitch.openchat.business.domain.util.ProjectRegex.USERNAME_VALIDATION_INFO
import java.util.regex.Pattern

open class TextUsernameState(
    value: String
): TextFieldState(){

    init {
        text = value
    }

    override fun getLabel(): String {
        return "Username"
    }

    override fun isValid(): Boolean {
        return Pattern.matches(ProjectRegex.USERNAME_VALIDATION_REGEX, text)
    }

    override fun getErrorMessage(): String {
        return USERNAME_VALIDATION_INFO
    }
}








