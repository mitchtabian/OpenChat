package com.codingwithmitch.openchat.framework.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun EmailInputField(
        email: String,
        onEmailChanged: (String) -> Unit,
        modifier: Modifier
){

    TextField(
            value = email,
            onValueChange = {
                onEmailChanged(it)
            },
            label = {
                Text(text = "Email")
            },
            modifier = modifier,
            keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
            ),
            leadingIcon = {Icon(Icons.Filled.Email)},
    )

}














