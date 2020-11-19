package com.codingwithmitch.openchat.framework.presentation.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * I got the idea for this from:
 * https://github.com/android/compose-samples/blob/e1ab50d935/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/TextFieldState.kt
 *
 * Function:
 * 1. Hold the field value
 * 2. Encapsulate the field validation
 * 3. Encapsulate the field errors
 **/

abstract class TextFieldState(){

    var text: String by mutableStateOf("")

    // Was the text field ever focused
    var wasFocused: Boolean by mutableStateOf(false)

    // Is it currently focused
    var isFocused: Boolean by mutableStateOf(false)

    // Helper needed for determining if errors should be shown
    // (if currently focused -> disable. if not focused -> enable.
    private var displayErrors: Boolean by mutableStateOf(false)

    fun isErrors() = !isValid() && displayErrors

    open fun onFocusChange(focused: Boolean) {
        isFocused = focused
        if (focused) {
            wasFocused = true
        }
    }

    fun checkEnableShowErrors() {
        // only show errors if the text was at least once focused
        if (wasFocused) {
            displayErrors = true
        }
    }

    /**
     * Was this field ever focused?
     * if not, show error msg.
     */
    fun validate() {
        if(!wasFocused){
            wasFocused = true
            displayErrors = true
        }
    }

    abstract fun getLabel(): String

    abstract fun isValid(): Boolean

    abstract fun getErrorMessage(): String

}















