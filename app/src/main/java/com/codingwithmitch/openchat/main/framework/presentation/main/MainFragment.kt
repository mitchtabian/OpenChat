package com.codingwithmitch.openchat.main.framework.presentation.main

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.lifecycleScope
import com.codingwithmitch.openchat.R
import com.codingwithmitch.openchat.common.framework.presentation.BaseMainFragment
import com.codingwithmitch.openchat.session.SessionManager
import com.codingwithmitch.openchat.common.framework.presentation.theme.AppTheme
import com.codingwithmitch.openchat.session.SessionStateEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Placeholder until I figure how the backend stuff is going to work
 */

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment : BaseMainFragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
                R.layout.compose_view, container, false
        ).apply {
            findViewById<ComposeView>(R.id.compose_view).setContent {
                AppTheme(
                        darkTheme = false,
                ){
                    Column() {
                        Text(
                                text = "MAIN",
                                style = MaterialTheme.typography.subtitle2
                        )
                        Button(
                                onClick = {
                                    lifecycleScope.launch {
                                        executeLogout()
                                    }
                                },

                                ) {
                            Text(
                                    text = "Logout",
                                    style = MaterialTheme.typography.button
                            )
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

    private suspend fun executeLogout(){
        sessionManager.setStateEvent(SessionStateEvent.LogoutEvent())
    }

    private fun initBackPressDispatcher(){
        activity?.let { activity ->
            activity.onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    /**
                     * Work-around for known memory leak issue:
                     * https://issuetracker.google.com/issues/139738913
                     */
                    if (activity.isTaskRoot) {
                        activity.finishAfterTransition()
                    }
                    else {
                        activity.onBackPressed()
                    }
                }
            })
        }
    }
}



















