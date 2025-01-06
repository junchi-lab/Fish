package com.junechee.fish.presentation.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.junechee.fish.presentation.component.FishButton
import com.junechee.fish.presentation.theme.FishTheme

@Composable
fun WelcomeScreen() {
    Surface {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.padding(top = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Connected",
                    style = MaterialTheme.typography.displayLarge
                )
                Text(
                    text = "your favorite social network",
                    style = MaterialTheme.typography.labelLarge
                )
            }

            FishButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                    .align(alignment = Alignment.BottomCenter),
                text = "Login",
                onClick = {}
            )
        }
    }

}

@Preview
@Composable
private fun WelcomeScreenPreview() {
    FishTheme {
        WelcomeScreen()
    }

}