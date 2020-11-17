package com.codingwithmitch.openchat.framework.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun PasswordInputField(
        password: String,
        onPasswordChange: (String) -> Unit,
        modifier: Modifier
) {
    TextField(
            value = password,
            onValueChange = {
                onPasswordChange(it)
            },
            label = {
                Text(text = "Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = modifier,
            keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
            ),
            leadingIcon = { Icon(Icons.Filled.Lock) }
    )

}















