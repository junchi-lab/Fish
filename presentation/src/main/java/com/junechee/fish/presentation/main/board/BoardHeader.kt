package com.junechee.fish.presentation.main.board

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.junechee.fish.presentation.component.ProfileImage
import com.junechee.fish.presentation.theme.FishTheme

@Composable
fun BoardHeader(
    modifier: Modifier = Modifier,
    isMine: Boolean,
    profileImageUrl: String? = null,
    username: String,
    onOptionClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileImage(
            modifier = Modifier
                .padding(start = 8.dp)
                .size(36.dp),
            profileImageUrl = profileImageUrl,
            borderWidth = 1.dp
        )

        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = username,
            style = MaterialTheme.typography.titleMedium
        )

        if (isMine){
            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = onOptionClick) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "옵션"
                )
            }
        }

    }

}

@Preview
@Composable
private fun BoardHeaderPreview() {
    FishTheme {
        Surface {
            BoardHeader(
                isMine = true,
                username = "Fish",
                onOptionClick = {}
            )
        }
    }

}