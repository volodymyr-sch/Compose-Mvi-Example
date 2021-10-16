package com.vsch.mvi

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onResume() {
        super.onResume()
        setContent {
            MviApp()
        }
    }
}