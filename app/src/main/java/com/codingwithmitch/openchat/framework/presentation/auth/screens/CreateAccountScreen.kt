package com.codingwithmitch.openchat.framework.presentation.auth.screens

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.codingwithmitch.openchat.R
import com.codingwithmitch.openchat.framework.presentation.auth.AuthViewModel
import com.codingwithmitch.openchat.framework.presentation.auth.state.AuthViewState.*
import com.codingwithmitch.openchat.framework.presentation.components.EmailInputField
import com.codingwithmitch.openchat.framework.presentation.components.PasswordInputField
import com.codingwithmitch.openchat.framework.presentation.components.TextFieldError
import com.codingwithmitch.openchat.framework.presentation.components.UsernameInputField
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalFocus
@ExperimentalCoroutinesApi
@Composable
fun CreateAccountScreen(
    viewModel: AuthViewModel,
){
    val viewState by viewModel.viewState.collectAsState()

    val emailState = viewState.createEmailState
    val usernameState = viewState.createUsernameState
    val passwordState = viewState.createPasswordState

    val defaultPadding = ContextAmbient.current.resources.getDimension(R.dimen.default_padding).dp
    val defaultElevation = ContextAmbient.current.resources.getDimension(R.dimen.default_elevation).dp
    val smallPadding = ContextAmbient.current.resources.getDimension(R.dimen.small_padding).dp
    val mediumPadding = ContextAmbient.current.resources.getDimension(R.dimen.medium_padding).dp
    val smallCornerRadius = ContextAmbient.current.resources.getDimension(R.dimen.small_corner_radius)

    ConstraintLayout(
        modifier = Modifier
            .background(color = MaterialTheme.colors.primary)
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
            backgroundColor = Color.White,
            elevation = defaultElevation,
        ) {
            ScrollableColumn() {
                CreateAccountFields(
                    emailState = emailState,
                    onEmailChanged = viewModel::onCreateEmailChanged,
                    usernameState = usernameState,
                    onUsernameChanged = viewModel::onCreateUsernameChanged,
                    passwordState = passwordState,
                    onPassword1Changed = viewModel::onPassword1Changed,
                    onShowPassword1Changed = viewModel::setShowPassword1,
                    onPassword2Changed = viewModel::onPassword2Changed,
                    onShowPassword2Changed = viewModel::setShowPassword2,
                    smallPadding = smallPadding,
                    mediumPadding = mediumPadding,
                )
            }
        }
    }
}


@ExperimentalFocus
@ExperimentalCoroutinesApi
@Composable
fun CreateAccountFields(
    emailState: CreateEmailState,
    onEmailChanged: (String) -> Unit,
    usernameState: CreateUsernameState,
    onUsernameChanged: (String) -> Unit,
    passwordState: CreatePasswordState,
    onPassword1Changed: (String) -> Unit,
    onShowPassword1Changed: (Boolean) -> Unit,
    onPassword2Changed: (String) -> Unit,
    onShowPassword2Changed: (Boolean) -> Unit,
    smallPadding: Dp,
    mediumPadding: Dp,
){
    val usernameFocusRequester = remember { FocusRequester() }
    val password1FocusRequester = remember { FocusRequester() }
    val password2FocusRequester = remember { FocusRequester() }
    val createAccountFocusRequester = remember { FocusRequester() }

    val showPassword1 = passwordState.password1.showPassword
    val showPassword2 = passwordState.password2.showPassword

    Column(
        modifier = Modifier
            .padding(
                top = mediumPadding,
                bottom = mediumPadding,
                start = smallPadding,
                end = smallPadding
            ),
    ){
        EmailInputField(
            emailState = emailState,
            onEmailChanged = onEmailChanged,
            modifier = Modifier
                .fillMaxWidth(),
            imeAction = ImeAction.Next,
            onImeAction = {
                usernameFocusRequester.requestFocus()
            },
        )
        Spacer(modifier = Modifier.preferredHeight(smallPadding))
        UsernameInputField(
            usernameState = usernameState,
            onUsernameChanged = onUsernameChanged,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(usernameFocusRequester),
            imeAction = ImeAction.Next,
            onImeAction = {
                password1FocusRequester.requestFocus()
            },
        )
        Spacer(modifier = Modifier.preferredHeight(smallPadding))
        PasswordInputField(
            passwordState = passwordState.password1,
            onPasswordChange = onPassword1Changed,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(password1FocusRequester)
            ,
            imeAction = ImeAction.Next,
            onImeAction = {
                password2FocusRequester.requestFocus()
            },
            showPassword = showPassword1,
            onShowPasswordChange = onShowPassword1Changed
        )
        Spacer(modifier = Modifier.preferredHeight(smallPadding))
        PasswordInputField(
            passwordState = passwordState.password2,
            onPasswordChange = onPassword2Changed,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(password2FocusRequester)
            ,
            imeAction = ImeAction.Done,
            onImeAction = {
                createAccountFocusRequester.requestFocus()
            },
            showPassword = showPassword2,
            onShowPasswordChange = onShowPassword2Changed
        )
        if(passwordState.isErrors()) TextFieldError(textError = passwordState.getErrorMessage())
        Spacer(modifier = Modifier.preferredHeight(mediumPadding))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(createAccountFocusRequester)
                .focus(), // Make this button "focusable"
            onClick = {
                // TODO("Execute CreateAccount use-case")
            },

            ) {
            Text(
                text = "Create Account",
                style = TextStyle(color = Color.White)
            )
        }
    }
}
























