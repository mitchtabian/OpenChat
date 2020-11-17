package com.codingwithmitch.openchat.framework.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.codingwithmitch.openchat.R
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.codingwithmitch.openchat.framework.presentation.auth.state.AuthViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
){
    val viewState: AuthViewState by viewModel.viewState.observeAsState(AuthViewState())

    ConstraintLayout(
        modifier = Modifier.background(color = MaterialTheme.colors.primary)
    ){
        val (card) = createRefs()
        val defaultPadding = ContextAmbient.current.resources.getDimension(R.dimen.default_padding).dp
        val smallPadding = ContextAmbient.current.resources.getDimension(R.dimen.small_padding).dp
        val mediumPadding = ContextAmbient.current.resources.getDimension(R.dimen.medium_padding).dp
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
            shape = RoundedCornerShape(ContextAmbient.current.resources.getDimension(R.dimen.small_corner_radius)),
            backgroundColor = White,
            elevation = ContextAmbient.current.resources.getDimension(R.dimen.default_elevation).dp,
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        top = mediumPadding,
                        bottom = mediumPadding,
                        start = smallPadding,
                        end = smallPadding
                    ),
            ) {
                TextField(
                    value = viewState.email,
                    onValueChange = {
                        viewModel.setEmail(it)
                    },
                    label = {
                        Text(text = "Email")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = smallPadding,
                        ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                )
                TextField(
                    value = viewState.password,
                    onValueChange = {
                        viewModel.setPassword(it)
                    },
                    label = {
                        Text(text = "Password")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = mediumPadding,
                        ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = mediumPadding,
                        ),
                    onClick = {
                        // TODO ("Execute Login use case")
                    },

                ) {
                    Text(
                        text = "Log in",
                        style = TextStyle(color = White)
                    )
                }
                WithConstraints(
                    modifier = Modifier
                        .clickable(
                            onClick = {
                                // TODO("Navigate to ResetPasswordScreen")
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
    }
}















