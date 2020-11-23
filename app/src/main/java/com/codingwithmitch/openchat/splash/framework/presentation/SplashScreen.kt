package com.codingwithmitch.openchat.splash.framework.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.codingwithmitch.openchat.R

@Composable
fun SplashScreen(){
    ConstraintLayout(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primary)
    ){
        val (icon) = createRefs()
        Surface(
                modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                        .constrainAs(icon) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                        },
                shape = RoundedCornerShape(ContextAmbient.current.resources.getDimension(R.dimen.default_corner_radius)),
                color = Color.White,
                elevation = ContextAmbient.current.resources.getDimension(R.dimen.default_elevation).dp,
        ) {
            Image(
                    asset = imageResource(id = R.drawable.logo_250_250_transparent),
                    modifier = Modifier
                            .padding(4.dp),

            )
        }
    }
}