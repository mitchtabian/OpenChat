package com.codingwithmitch.openchat.splash.framework.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.codingwithmitch.openchat.R
import com.codingwithmitch.openchat.common.business.domain.util.printLogD
import com.codingwithmitch.openchat.common.framework.presentation.theme.AppTheme
import com.codingwithmitch.openchat.session.SessionManager
import com.codingwithmitch.openchat.splash.business.interactors.NO_AUTH_TOKEN_FOUND
import com.codingwithmitch.openchat.splash.framework.presentation.state.SplashStateEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SplashFragment : Fragment() {

    private val viewModel: SplashViewModel by viewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.compose_view, container, false
        ).apply {
            findViewById<ComposeView>(R.id.compose_view).setContent {

                val progressBarState by viewModel.shouldDisplayProgressBar.collectAsState()

                val stateMessageState by viewModel.stateMessage.collectAsState()

                // There is no token found, redirect to login screen.
                if(stateMessageState?.response?.message?.equals(NO_AUTH_TOKEN_FOUND) == true){
                    findNavController().navigate(R.id.action_splashFragment_to_authFragment)
                }
                AppTheme(
                    darkTheme = false,
                    progressBarIsDisplayed = progressBarState,
                ){
                    SplashScreen()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Limit the time SplashFragment is visible. If jobs take longer than 2 seconds
         * redirect to LoginScreen.
         */
        lifecycleScope.launch {
            delay(2000)
            findNavController().navigate(R.id.action_splashFragment_to_authFragment)
        }
        viewModel.sessionState.observe(viewLifecycleOwner, {sessionState ->
            if(sessionState?.authToken != null){
                findNavController().navigate(R.id.action_splashFragment_to_authFragment)
            }
        })

        viewModel.checkAuthToken()
    }
}
















