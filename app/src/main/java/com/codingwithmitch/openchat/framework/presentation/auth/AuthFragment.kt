package com.codingwithmitch.openchat.framework.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.codingwithmitch.openchat.R
import com.codingwithmitch.openchat.framework.presentation.auth.screens.AuthScreen
import com.codingwithmitch.openchat.framework.presentation.auth.screens.CreateAccountScreen
import com.codingwithmitch.openchat.framework.presentation.auth.screens.LoginScreen
import com.codingwithmitch.openchat.framework.presentation.auth.screens.PasswordResetScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

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
                MaterialTheme {

                    val viewState by viewModel.viewState.collectAsState()
                    val screen = viewState.screen

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
                        activity.onBackPressed()
                    }
                }
            })
        }

    }

}




















