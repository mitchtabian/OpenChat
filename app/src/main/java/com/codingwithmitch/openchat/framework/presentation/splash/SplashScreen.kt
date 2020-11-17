package com.codingwithmitch.openchat.framework.presentation.splash

import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import com.codingwithmitch.openchat.R

@Composable
fun SplashScreen(){
    ConstraintLayout {
        val (icon) = createRefs()
        Icon(
                asset = imageResource(id = R.drawable.logo_250_250_transparent),
                modifier = Modifier.constrainAs(icon) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
        )

    }
}