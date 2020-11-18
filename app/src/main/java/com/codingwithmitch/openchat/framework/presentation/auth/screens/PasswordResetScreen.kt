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
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalFocus
@ExperimentalCoroutinesApi
@Composable
fun PasswordResetScreen(
    viewModel: AuthViewModel,
){
    val viewState by viewModel.viewState.collectAsState()

    val emailState = viewState.passwordResetEmailState

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
                PasswordResetFields(
                    viewModel = viewModel,
                    emailState = emailState,
                    smallPadding = smallPadding,
                    mediumPadding = mediumPadding,
                )
            }
        }
    }
}

@ExperimentalCoroutinesApi
@ExperimentalFocus
@Composable
fun PasswordResetFields(
    viewModel: AuthViewModel,
    emailState: PasswordResetEmailState,
    smallPadding: Dp,
    mediumPadding: Dp,
){
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .padding(
                top = mediumPadding,
                bottom = mediumPadding,
                start = smallPadding,
                end = smallPadding
            ),
    ){
        PasswordResetEmailField(
            emailState = emailState,
            onEmailChanged = viewModel::onPasswordResetEmailChanged,
            focusRequester = focusRequester
        )
        Spacer(modifier = Modifier.preferredHeight(smallPadding))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .focus(), // Make this button "focusable"
            onClick = {
                // TODO("Execute password reset")
            },

            ) {
            Text(
                text = "Reset",
                style = TextStyle(color = Color.White)
            )
        }
    }
}


@ExperimentalFocus
@Composable
fun PasswordResetEmailField(
    emailState: PasswordResetEmailState,
    onEmailChanged: (String) -> Unit,
    focusRequester: FocusRequester,
){
    EmailInputField(
        emailState = emailState,
        onEmailChanged = onEmailChanged,
        modifier = Modifier
            .fillMaxWidth(),
        imeAction = ImeAction.Next,
        onImeAction = {
            focusRequester.requestFocus()
        },
    )
}












