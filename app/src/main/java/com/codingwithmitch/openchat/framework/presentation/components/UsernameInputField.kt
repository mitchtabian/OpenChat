package com.codingwithmitch.openchat.framework.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focusObserver
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.codingwithmitch.openchat.framework.presentation.auth.state.AuthViewState.*
import com.codingwithmitch.openchat.framework.presentation.common.TextEmailState
import com.codingwithmitch.openchat.framework.presentation.common.TextUsernameState

@ExperimentalFocus
@Composable
fun UsernameInputField(
        usernameState: TextUsernameState,
        onUsernameChanged: (String) -> Unit,
        modifier: Modifier,
        imeAction: ImeAction = ImeAction.Next,
        onImeAction: () -> Unit,
){

    TextField(
            value = usernameState.text,
            onValueChange = {
                onUsernameChanged(it)
            },
            label = {
                Text(text = usernameState.getLabel())
            },
            modifier = modifier.focusObserver { focusState ->
                val focused = focusState == FocusState.Active
                usernameState.onFocusChange(focused)
                if (!focused) {
                    usernameState.checkEnableShowErrors()
                }
            },
            keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = imeAction,
            ),
            leadingIcon = {Icon(Icons.Filled.AccountCircle)},
            onImeActionPerformed = { action, softKeyboardController ->
                if (action == ImeAction.Next || action == ImeAction.Done) {
                    softKeyboardController?.hideSoftwareKeyboard()
                }
                onImeAction()
            },
            isErrorValue = usernameState.isErrors()
    )
    if(usernameState.isErrors()) TextFieldError(textError = usernameState.getErrorMessage())
}














