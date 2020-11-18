package com.codingwithmitch.openchat.framework.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codingwithmitch.openchat.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), UIController {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

}










