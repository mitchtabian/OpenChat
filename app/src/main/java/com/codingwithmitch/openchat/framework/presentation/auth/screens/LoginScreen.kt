package com.codingwithmitch.openchat.framework.presentation.auth.screens

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import com.codingwithmitch.openchat.R
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.platform.ConfigurationAmbient
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.ui.tooling.preview.UiMode
import com.codingwithmitch.openchat.framework.presentation.BaseApplication
import com.codingwithmitch.openchat.framework.presentation.auth.AuthViewModel
import com.codingwithmitch.openchat.framework.presentation.auth.state.AuthViewState.*
import com.codingwithmitch.openchat.framework.presentation.components.EmailInputField
import com.codingwithmitch.openchat.framework.presentation.components.PasswordInputField
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalFocus
@ExperimentalCoroutinesApi
@Composable
fun LoginScreen(
        viewModel: AuthViewModel,
) {

    val viewState by viewModel.viewState.collectAsState()

    val loginEmailState = viewState.loginEmailState
    val loginPasswordState = viewState.loginPasswordState
    val showLoginPassword = loginPasswordState.showPassword

    val defaultPadding = ContextAmbient.current.resources.getDimension(R.dimen.default_padding).dp
    val defaultElevation = ContextAmbient.current.resources.getDimension(R.dimen.default_elevation).dp
    val smallPadding = ContextAmbient.current.resources.getDimension(R.dimen.small_padding).dp
    val mediumPadding = ContextAmbient.current.resources.getDimension(R.dimen.medium_padding).dp
    val smallCornerRadius = ContextAmbient.current.resources.getDimension(R.dimen.small_corner_radius)

    ConstraintLayout(
            modifier = Modifier
                    .fillMaxSize()
    ) {
        val (card) = createRefs()
        Card(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(mediumPadding)
                        .constrainAs(card) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                        }
                        .focus(),
                shape = RoundedCornerShape(smallCornerRadius),
                elevation = defaultElevation,
        ) {
            ScrollableColumn() {
                LoginFields(
                        viewModel = viewModel,
                        smallPadding = smallPadding,
                        mediumPadding = mediumPadding,
                        emailState = loginEmailState,
                        onEmailChanged = viewModel::onLoginEmailChanged,
                        loginPasswordState = loginPasswordState,
                        onPasswordChanged = viewModel::onLoginPasswordChanged,
                        onExecuteLogin = {
                            // TODO ("Execute Login use case")
                        },
                        showPassword = showLoginPassword,
                        onShowPasswordChanged = {
                            viewModel.setShowLoginPassword(it)
                        }
                )
            }
        }
    }
}


@ExperimentalCoroutinesApi
@ExperimentalFocus
@Composable
fun LoginFields(
        viewModel: AuthViewModel,
        smallPadding: Dp,
        mediumPadding: Dp,
        emailState: LoginEmailState,
        onEmailChanged: (String) -> Unit,
        loginPasswordState: LoginPasswordState,
        onPasswordChanged: (String) -> Unit,
        onExecuteLogin: () -> Unit,
        showPassword: Boolean,
        onShowPasswordChanged: (Boolean) -> Unit,
){
    val appContext = (ContextAmbient.current.applicationContext as BaseApplication)
    val passwordFocusRequester = remember { FocusRequester() }
    val loginBtnFocusRequester = remember { FocusRequester() }
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
                emailState = emailState,
                onEmailChanged = onEmailChanged,
                modifier = Modifier
                        .fillMaxWidth(),
                imeAction = ImeAction.Next,
                onImeAction = {
                    passwordFocusRequester.requestFocus()
                },
        )
        Spacer(modifier = Modifier.preferredHeight(smallPadding))
        PasswordInputField(
                passwordState = loginPasswordState,
                onPasswordChange = onPasswordChanged,
                modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(passwordFocusRequester)
                ,
                imeAction = ImeAction.Done,
                onImeAction = {
                    loginBtnFocusRequester.requestFocus()
                    //TODO("Execute Login use case")
                },
                showPassword = showPassword,
                onShowPasswordChange = onShowPasswordChanged
        )
        Spacer(modifier = Modifier.preferredHeight(smallPadding))
        Button(
                modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(loginBtnFocusRequester)
                        .focus(), // Make this button "focusable"
                onClick = {
                    onExecuteLogin()
                },

                ) {
            Text(
                    text = "Log in",
                    style = MaterialTheme.typography.button
            )
        }
        Spacer(modifier = Modifier.preferredHeight(mediumPadding))
        PasswordResetField(
                onSelectPasswordReset = {
                    viewModel.navigateTo(AuthScreen.PasswordReset)
                }
        )
        Spacer(modifier = Modifier.preferredHeight(mediumPadding))
        CreateAnAccountField(
                onSelectCreateAccount = {
                    viewModel.navigateTo(AuthScreen.CreateAccount)
                }
        )
        Spacer(modifier = Modifier.preferredHeight(mediumPadding))
        Button(
                modifier = Modifier
                        .fillMaxWidth()
                        .focus(),
                onClick = {
                    appContext.toggleLightTheme()
                },

                ) {
            Text(
                    text = "Toggle Theme",
                    style = MaterialTheme.typography.button
            )
        }
    }
}

@Composable
fun PasswordResetField(
        onSelectPasswordReset: () -> Unit,
){
    Column(
            modifier = Modifier.fillMaxWidth()
    ){
        WithConstraints(
                modifier = Modifier
                        .clickable(
                                onClick = {
                                    onSelectPasswordReset()
                                }
                        )
                        .align(Alignment.CenterHorizontally)
        ) {
            Text(
                    text = "Password Reset",
                    style = MaterialTheme.typography.h5,
            )
        }
    }
}



@Composable
fun CreateAnAccountField(
        onSelectCreateAccount: () -> Unit,
){
    Column(
            modifier = Modifier.fillMaxWidth()
    ){
        WithConstraints(
                modifier = Modifier
                        .clickable(
                                onClick = {
                                    onSelectCreateAccount()
                                }
                        )
                        .align(Alignment.CenterHorizontally)
        ) {
            Text(
                    text = "Create an Account",
                    style = MaterialTheme.typography.h5,
            )
        }
    }
}


















