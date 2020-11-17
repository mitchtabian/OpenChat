package com.codingwithmitch.openchat.framework.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.codingwithmitch.openchat.R
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.foundation.text.BasicText

@AndroidEntryPoint
class SplashFragment : Fragment() {
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.compose_view, container, false
        ).apply {
            findViewById<ComposeView>(R.id.compose_view).setContent {
                SplashScreen()
            }

        }
    }
}
















