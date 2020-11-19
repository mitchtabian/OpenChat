package com.codingwithmitch.openchat.framework.presentation.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.codingwithmitch.openchat.R
import com.codingwithmitch.openchat.framework.presentation.BaseApplication
import com.codingwithmitch.openchat.framework.presentation.TAG
import com.codingwithmitch.openchat.framework.presentation.auth.screens.AuthScreen
import com.codingwithmitch.openchat.framework.presentation.auth.screens.CreateAccountScreen
import com.codingwithmitch.openchat.framework.presentation.auth.screens.LoginScreen
import com.codingwithmitch.openchat.framework.presentation.auth.screens.PasswordResetScreen
import com.codingwithmitch.openchat.framework.presentation.session.AuthState
import com.codingwithmitch.openchat.framework.presentation.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalFocus
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class AuthFragment: Fragment() {

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.compose_view, container, false
        ).apply {
            findViewById<ComposeView>(R.id.compose_view).setContent {
                val authState by viewModel.authState.collectAsState()
                ObserveAuthState(
                        authState = authState,
                        onAuthSuccess = {
                            findNavController().navigate(R.id.action_authFragment_to_mainFragment)
                        }
                )
                AppTheme(
                        darkTheme = !(activity?.application as BaseApplication).isLight,
                ) {
                    val viewState by viewModel.viewState.collectAsState()
                    val screen = viewState.screen

                    Surface(
                            color = MaterialTheme.colors.background
                    ) {
                        when(screen){
                            is AuthScreen.Login -> {
                                LoginScreen(viewModel = viewModel)
                            }
                            is AuthScreen.PasswordReset -> {
                                PasswordResetScreen(viewModel = viewModel)
                            }
                            is AuthScreen.CreateAccount -> {
                                CreateAccountScreen(viewModel = viewModel)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBackPressDispatcher()
    }

    private fun initBackPressDispatcher(){
        activity?.let { activity ->
            activity.onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if(!viewModel.onBack()){
                        isEnabled = false

                        /**
                         * Work-around for know memory leak issue:
                         * https://issuetracker.google.com/issues/139738913
                         */
                        if (activity.isTaskRoot) {
                            activity.finishAfterTransition()
                        }
                        else {
                            activity.onBackPressed()
                        }
                    }
                }
            })
        }

    }

}


@ExperimentalCoroutinesApi
@Composable
fun ObserveAuthState(
        authState: AuthState,
        onAuthSuccess: () -> Unit,
){
    if(authState.isAuthenticated){
        onAuthSuccess()
    }
}



















