package com.codingwithmitch.openchat.common.framework.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.material.AmbientTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Got from:
 * https://github.com/android/compose-samples/blob/e1ab50d935be6d2062d4defed3d610026dde4e2d/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/SignInSignUp.kt#L215
 *
 * WARNING:
 * This will be removed later when TextFields support errors.
  */
@Composable
fun TextFieldError(
        textError: String
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.preferredWidth(16.dp))
        Text(
                text = textError,
                modifier = Modifier.fillMaxWidth(),
                style = AmbientTextStyle.current.copy(color = MaterialTheme.colors.error)
        )
    }
}










