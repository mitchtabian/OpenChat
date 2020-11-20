package com.codingwithmitch.openchat.common.framework.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focusObserver
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.codingwithmitch.openchat.common.framework.presentation.state.TextEmailState

@ExperimentalFocus
@Composable
fun EmailInputField(
    emailState: TextEmailState,
    onEmailChanged: (String) -> Unit,
    modifier: Modifier,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit,
){

    TextField(
            value = emailState.text,
            onValueChange = {
                onEmailChanged(it)
            },
            label = {
                Text(text = emailState.getLabel())
            },
            modifier = modifier.focusObserver { focusState ->
                val focused = focusState == FocusState.Active
                emailState.onFocusChange(focused)
                if (!focused) {
                    emailState.checkEnableShowErrors()
                }
            },
            keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = imeAction,
            ),
            leadingIcon = {Icon(Icons.Filled.Email)},
            onImeActionPerformed = { action, softKeyboardController ->
                if (action == ImeAction.Next || action == ImeAction.Done) {
                    softKeyboardController?.hideSoftwareKeyboard()
                }
                onImeAction()
            },
            isErrorValue = emailState.isErrors()
    )
    if(emailState.isErrors()) TextFieldError(textError = emailState.getErrorMessage())
}














