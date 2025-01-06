package com.junechee.fish.presentation.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FishTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(8.dp)
    )

}