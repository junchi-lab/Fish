package com.junechee.fish.presentation.main.board.comment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.junechee.fish.presentation.component.ProfileImage
import com.junechee.fish.presentation.theme.FishTheme
import org.w3c.dom.Comment

@Composable
fun CommentCard(
    modifier: Modifier = Modifier,
    profileImageUrl: String? = null,
    username: String = "",
    text: String = "",
    onDeleteComment: () -> Unit
) {
    Surface {
        Row(
            modifier = modifier
                .padding(vertical = 8.dp)
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileImage(
                modifier = Modifier.size(28.dp),
                profileImageUrl = profileImageUrl,
                borderWidth = 0.7.dp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = username)
                Text(text = text)
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = onDeleteComment) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Delete"
                )
            }
        }

    }


}

@Preview
@Composable
private fun CommentCardPreview() {
    FishTheme {
        CommentCard(
            profileImageUrl = null,
            username = "JJJJJJJ",
            text = "HI",
            onDeleteComment = {}
        )
    }
}