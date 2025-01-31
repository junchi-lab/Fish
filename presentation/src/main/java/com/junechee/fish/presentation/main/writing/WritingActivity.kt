package com.junechee.fish.presentation.main.writing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.junechee.fish.presentation.theme.FishTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WritingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FishTheme {
                WritingNavHost()
            }
        }
    }
}