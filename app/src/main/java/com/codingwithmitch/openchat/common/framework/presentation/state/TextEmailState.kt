package com.codingwithmitch.openchat.common.framework.presentation.state

import com.codingwithmitch.openchat.common.business.domain.util.ProjectRegex
import java.util.regex.Pattern

open class TextEmailState(
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