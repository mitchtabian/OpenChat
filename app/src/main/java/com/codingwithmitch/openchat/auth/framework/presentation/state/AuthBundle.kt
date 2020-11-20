package com.codingwithmitch.openchat.auth.framework.presentation.state

import android.os.Parcelable
import com.codingwithmitch.openchat.auth.framework.presentation.navigation.AuthScreen
import com.codingwithmitch.openchat.auth.framework.presentation.navigation.toAuthScreen
import com.codingwithmitch.openchat.auth.framework.presentation.state.AuthViewState.*
import com.codingwithmitch.openchat.auth.framework.presentation.state.AuthViewState.CreatePasswordState.*
import kotlinx.android.parcel.Parcelize

/**
 * For saving and restoring state after process death.
 */

@Parcelize
class AuthBundle (
    val loginEmail: String = "",
    val loginPassword: String = "",
    val passwordResetEmail: String = "",
    val createEmail: String = "",
    val createUsername: String = "",
    val createPassword1: String = "",
    val createPassword2: String = "",
    val screenName: String = AuthScreen.Login.name()
) : Parcelable


fun AuthBundle.restoreViewState(): AuthViewState {
    return AuthViewState(
        loginEmailState = LoginEmailState(loginEmail),
        loginPasswordState = LoginPasswordState(loginPassword),
        passwordResetEmailState = PasswordResetEmailState(passwordResetEmail),
        createEmailState = CreateEmailState(createEmail),
        createUsernameState = CreateUsernameState(createUsername),
        createPasswordState = CreatePasswordState(
            Password1State(createPassword1),
            Password2State(createPassword2),
        ),
        screen = toAuthScreen(screenName)
    )
}

fun AuthViewState.toAuthBundle(): AuthBundle {
    return AuthBundle(
        loginEmail = loginEmailState.text,
        loginPassword = loginPasswordState.text,
        passwordResetEmail = passwordResetEmailState.text,
        createEmail = createEmailState.text,
        createUsername = createUsernameState.text,
        createPassword1 = createPasswordState.password1.text,
        createPassword2 = createPasswordState.password2.text,
        screenName = screen.name(),
    )
}













