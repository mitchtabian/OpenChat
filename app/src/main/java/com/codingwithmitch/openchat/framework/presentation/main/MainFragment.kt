package com.codingwithmitch.openchat.framework.presentation.main

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.findNavController
import com.codingwithmitch.openchat.R
import com.codingwithmitch.openchat.framework.presentation.session.SessionManager
import com.codingwithmitch.openchat.framework.presentation.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/**
 * Placeholder until I figure how the backend stuff is going to work
 */

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment : Fragment() {

    @Inject
    lateinit var sessionManager: SessionManager

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
                                    sessionManager.onLogout()
                                    findNavController().navigate(R.id.action_mainFragment_to_authFragment)
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

}



















