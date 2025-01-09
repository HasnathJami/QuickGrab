package com.jsn.quickgrab.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jsn.quickgrab.navigation.QuickGrabNavigation
import com.jsn.quickgrab.ui.theme.QuickGrabTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            QuickGrabTheme {
               QuickGrabNavigation()
            }
        }
    }
}