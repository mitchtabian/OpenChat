package com.codingwithmitch.openchat.auth.framework.presentation

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.codingwithmitch.openchat.auth.framework.presentation.navigation.AuthScreen
import com.codingwithmitch.openchat.auth.framework.presentation.state.*
import com.codingwithmitch.openchat.auth.framework.presentation.state.AuthViewState.*
import com.codingwithmitch.openchat.auth.framework.presentation.state.AuthViewState.CreatePasswordState.*
import com.codingwithmitch.openchat.common.business.domain.util.printLogD
import com.codingwithmitch.openchat.session.framework.presentation.SessionManager
import com.codingwithmitch.openchat.session.framework.presentation.SessionStateEvent.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@ExperimentalCoroutinesApi
class AuthViewModel
@ViewModelInject
constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val sessionManager: SessionManager,
): ViewModel(){

    private val _viewState: MutableStateFlow<AuthViewState> = MutableStateFlow(AuthViewState())

    val viewState: StateFlow<AuthViewState> get() =  _viewState

    init {
        // Restore the ViewState after process death
        savedStateHandle.get<AuthBundle>(BUNDLE_KEY_AUTH_VIEWSTATE)?.let { bundle ->
            val viewState = bundle.restoreViewState()
            setViewState(viewState)
            navigateTo(viewState.screen)
        }
    }

    fun navigateTo(screen: AuthScreen) {
        val new = buildNewViewState(screen = screen)
        setViewState(new)
    }

    fun onBack(): Boolean {
        val wasHandled = _viewState.value.screen != AuthScreen.Login
        navigateTo(AuthScreen.Login)
        return wasHandled
    }

    fun setViewState(viewState: AuthViewState){
        _viewState.value = viewState
    }

    private fun getCurrentViewState(): AuthViewState {
        return _viewState.value
    }

    fun onLoginEmailChanged(email: String){
        val new = buildNewViewState(loginEmailState = LoginEmailState(email))
        setViewState(new)
    }

    fun onLoginPasswordChanged(password: String){
        val showPasswordValue = _viewState.value.loginPasswordState.showPassword
        val new = buildNewViewState(loginPasswordState = LoginPasswordState(password, showPasswordValue))
        setViewState(new)
    }

    fun setShowLoginPassword(showPassword: Boolean){
        val password = _viewState.value.loginPasswordState.text
        val new = buildNewViewState(loginPasswordState = LoginPasswordState(password, showPassword))
        setViewState(new)
    }

    fun onCreateEmailChanged(email: String){
        val new = buildNewViewState(createEmailState = CreateEmailState(email))
        setViewState(new)
    }

    fun onCreateUsernameChanged(username: String){
        val new = buildNewViewState(createUsernameState = CreateUsernameState(username))
        setViewState(new)
    }

    fun onPassword1Changed(password: String){
        val showPassword1Value = _viewState.value.createPasswordState.password1.showPassword
        val password2State = _viewState.value.createPasswordState.password2
        val new = buildNewViewState(
                createPasswordState = CreatePasswordState(
                        password1 = Password1State(password, showPassword1Value),
                        password2 = password2State
                )
        )
        setViewState(new)
    }

    fun onPassword2Changed(password: String){
        val showPassword2Value = _viewState.value.createPasswordState.password2.showPassword
        val password1State = _viewState.value.createPasswordState.password1
        val new = buildNewViewState(
                createPasswordState = CreatePasswordState(
                        password1 = password1State,
                        password2 = Password2State(password, showPassword2Value)
                )
        )
        setViewState(new)
    }

    fun setShowPassword1(showPassword: Boolean){
        val password1Value = _viewState.value.createPasswordState.password1.text
        val password2State = _viewState.value.createPasswordState.password2
        val new = buildNewViewState(
                createPasswordState = CreatePasswordState(
                        password1 = Password1State(password1Value, showPassword),
                        password2 = password2State
                )
        )
        setViewState(new)
    }

    fun setShowPassword2(showPassword: Boolean){
        val password2Value = _viewState.value.createPasswordState.password2.text
        val password1State = _viewState.value.createPasswordState.password1
        val new = buildNewViewState(
                createPasswordState = CreatePasswordState(
                        password1 = password1State,
                        password2 = Password2State(password2Value, showPassword)
                )
        )
        setViewState(new)
    }

    fun onPasswordResetEmailChanged(email: String){
        val new = buildNewViewState(passwordResetEmailState = PasswordResetEmailState(email))
        setViewState(new)
    }
    
    fun onSendPasswordResetEmail(){
        val emailState = _viewState.value.passwordResetEmailState
        emailState.validate()
        if (!emailState.isErrors()) {
            // TODO("Fire reset password StateEvent")
            onPasswordResetEmailChanged("")
        }
    }

    fun onAttemptLogin(email: String, password: String){
        val emailState = _viewState.value.loginEmailState
        val passwordState = _viewState.value.loginPasswordState
        emailState.validate()
        passwordState.validate()
        printLogD("AuthViewmodel", "ATTEMPTING LOGIN")
        if(!emailState.isErrors() && !passwordState.isErrors()){
            sessionManager.setStateEvent(LoginEvent(email, password))
        }
    }

    fun onAttemptCreateAccount(){
        val emailState = _viewState.value.createEmailState
        val usernameState = _viewState.value.createUsernameState
        val password1State = _viewState.value.createPasswordState.password1
        val password2State = _viewState.value.createPasswordState.password2
        emailState.validate()
        usernameState.validate()
        password1State.validate()
        password2State.validate()
        if(!emailState.isErrors()
                && !usernameState.isErrors()
                && !password1State.isErrors()
                && !password2State.isErrors()
        ){
            // TODO("Attempt Create new account")
        }
    }

    private fun buildNewViewState(
        loginEmailState: LoginEmailState? = null,
        loginPasswordState: LoginPasswordState? = null,
        passwordResetEmailState: PasswordResetEmailState? = null,
        createEmailState: CreateEmailState? = null,
        createUsernameState: CreateUsernameState? = null,
        createPasswordState: CreatePasswordState? = null,
        screen: AuthScreen? = null,
    ): AuthViewState{
        val current = getCurrentViewState()
        val new = AuthViewState(
                loginEmailState = loginEmailState?: current.loginEmailState,
                loginPasswordState = loginPasswordState?: current.loginPasswordState,
                passwordResetEmailState = passwordResetEmailState?: current.passwordResetEmailState,
                createEmailState = createEmailState?: current.createEmailState,
                createUsernameState = createUsernameState?: current.createUsernameState,
                createPasswordState = createPasswordState?: current.createPasswordState,
                screen = screen?: current.screen,
        )
        savedStateHandle.set(BUNDLE_KEY_AUTH_VIEWSTATE, new.toAuthBundle())
        return new
    }
}
















