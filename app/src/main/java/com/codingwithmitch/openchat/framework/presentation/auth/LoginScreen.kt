package com.codingwithmitch.openchat.framework.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import com.codingwithmitch.openchat.R
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.codingwithmitch.openchat.framework.presentation.components.EmailInputField
import com.codingwithmitch.openchat.framework.presentation.components.PasswordInputField
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
) {
    val viewState by viewModel.viewState.collectAsState()

    val email = viewState.email
    val password = viewState.password

    val defaultPadding = ContextAmbient.current.resources.getDimension(R.dimen.default_padding).dp
    val defaultElevation = ContextAmbient.current.resources.getDimension(R.dimen.default_elevation).dp
    val smallPadding = ContextAmbient.current.resources.getDimension(R.dimen.small_padding).dp
    val mediumPadding = ContextAmbient.current.resources.getDimension(R.dimen.medium_padding).dp
    val smallCornerRadius = ContextAmbient.current.resources.getDimension(R.dimen.small_corner_radius)

    ConstraintLayout(
            modifier = Modifier.background(color = MaterialTheme.colors.primary)
    ) {
        val (card) = createRefs()
        Card(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(defaultPadding)
                        .constrainAs(card) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                        },
                shape = RoundedCornerShape(smallCornerRadius),
                backgroundColor = White,
                elevation = defaultElevation,
        ) {
            LoginFields(
                    smallPadding = smallPadding,
                    mediumPadding = mediumPadding,
                    email = email,
                    onEmailChanged = viewModel::setEmail,
                    password = password,
                    onPasswordChanged = viewModel::setPassword,
                    onExecuteLogin = {
                        // TODO ("Execute Login use case")
                    }
            )
            PasswordResetField(
                    executePasswordReset = {
                        // TODO ("Execute Password Reset use case")
                    }
            )
        }
    }
}


@Composable
fun LoginFields(
        smallPadding: Dp,
        mediumPadding: Dp,
        email: String,
        onEmailChanged: (String) -> Unit,
        password: String,
        onPasswordChanged: (String) -> Unit,
        onExecuteLogin: () -> Unit,
){
    Column(
            modifier = Modifier
                    .padding(
                            top = mediumPadding,
                            bottom = mediumPadding,
                            start = smallPadding,
                            end = smallPadding
                    ),
    ) {
        EmailInputField(
                email = email,
                onEmailChanged = onEmailChanged,
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                                bottom = smallPadding,
                        )
        )
        PasswordInputField(
                password = password,
                onPasswordChange = onPasswordChanged,
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                                bottom = smallPadding,
                        )
        )
        Button(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                                bottom = mediumPadding,
                        ),
                onClick = {
                    onExecuteLogin()
                },

                ) {
            Text(
                    text = "Log in",
                    style = TextStyle(color = White)
            )
        }
    }
}

@Composable
fun PasswordResetField(
        executePasswordReset: () -> Unit
){
    Column{
        WithConstraints(
                modifier = Modifier
                        .clickable(
                                onClick = {
                                    executePasswordReset()
                                }
                        )
                        .align(Alignment.CenterHorizontally)
        ) {
            Text(
                    text = "Password Reset",
                    style = TextStyle(
                            color = MaterialTheme.colors.primaryVariant,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = TextUnit.Companion.Sp(16)
                    ),
            )
        }
    }
}
















