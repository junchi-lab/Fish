package com.junechee.fish.presentation.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.junechee.fish.presentation.theme.FishTheme

@Composable
fun FishButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit

) {
    Button(
        modifier = modifier.height(48.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        onClick = onClick
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )

    }
}

@Preview
@Composable
private fun FishButtonPreview() {
    FishTheme {
        FishButton(
            modifier = Modifier,
            text = "Login"
        ) { }
    }
    
}