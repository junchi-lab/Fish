package com.junechee.fish.presentation.main.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.junechee.fish.presentation.main.writing.FishImagePager
import com.junechee.fish.presentation.theme.FishTheme

@Composable
fun BoardCard(
    modifier: Modifier = Modifier,
    profileImageUrl: String? = null,
    username: String,
    images: List<String>,
    text: String,
    onOptionClick: () -> Unit,
    onReplyClick: () -> Unit

) {
    Column(
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(16.dp)
            ),

        ) {
        // 헤더
        BoardHeader(
            modifier = Modifier.fillMaxWidth(),
            profileImageUrl = profileImageUrl,
            username = username,
            onOptionClick = onOptionClick
        )

        // 이미지 페이저
        if (images.isNotEmpty()) {
            FishImagePager(
                modifier = Modifier.fillMaxWidth(),
                images = images
            )
        }
        var maxLines by remember(text) { mutableIntStateOf(1) }
        var showMore by remember { mutableStateOf(false) }

        // 내용(text)
        Text(
            modifier = Modifier
                .padding(top = 4.dp)
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            text = text,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { textLayoutResult ->
                showMore = textLayoutResult.didOverflowHeight
            }
        )

        if (showMore) {
            TextButton(onClick = {
                maxLines = Integer.MAX_VALUE
            }) {
                Text(
                    text = "더보기",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }

        // 댓글 버튼
        IconButton(
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(horizontal = 8.dp)
                .align(Alignment.End),
            onClick = onReplyClick
        ) {
            Icon(
                imageVector = Icons.Filled.AddCircle,
                contentDescription = "reply"
            )
        }
    }


}

@Preview
@Composable
private fun BoardCardPreview() {
    FishTheme {
        BoardCard(
            username = "Fish Preview",
            images = emptyList(),
            text = "Preview\nPreview\nPreview\nPreview\nPreview\n",
            onOptionClick = {},
            onReplyClick = {}

        )
    }

}