package com.jsn.quickgrab.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jsn.quickgrab.navigation.QuickGrabNavigation
import com.jsn.quickgrab.ui.theme.QuickGrabTheme

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