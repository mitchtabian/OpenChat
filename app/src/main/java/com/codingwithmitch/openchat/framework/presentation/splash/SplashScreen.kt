package com.codingwithmitch.openchat.framework.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.codingwithmitch.openchat.R

@Composable
fun SplashScreen(){
    ConstraintLayout(
            modifier = Modifier.background(color = MaterialTheme.colors.primary)
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
                shape = RoundedCornerShape(10.dp),
                color = Color.White,
                elevation = 22.dp,
        ) {
            Image(
                    asset = imageResource(id = R.drawable.logo_250_250_transparent),
                    modifier = Modifier
                            .padding(4.dp),

            )
        }
    }
}