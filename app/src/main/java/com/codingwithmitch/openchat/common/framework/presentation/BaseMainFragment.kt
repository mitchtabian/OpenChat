package com.codingwithmitch.openchat.common.framework.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.codingwithmitch.openchat.R
import com.codingwithmitch.openchat.session.SessionManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/**
 * All fragments except [AuthFragment] will extend this class.
 *
 * This makes it simple to observe the Auth state. If the token becomes null then navigate
 * back to login screen.
 */
@ExperimentalCoroutinesApi
abstract class BaseMainFragment : Fragment(){

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sessionManager.sessionState.observe(viewLifecycleOwner, {sessionState ->
            if(sessionState?.authToken == null){

                /**
                 * Currently I can't think of a better way to do this. It's mostly great, other
                 * than the SplashFragment shows for a second. But this is not a big deal.
                 * Pros and cons...
                 */
                findNavController()
                        .createDeepLink()
                        .setGraph(R.navigation.auth_graph)
                        .setDestination(R.id.authFragment)
                        .createPendingIntent()
                        .send()
            }
        })

    }
}




















