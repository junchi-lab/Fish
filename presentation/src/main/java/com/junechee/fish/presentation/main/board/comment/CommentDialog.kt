package com.junechee.fish.presentation.main.board.comment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.junechee.fish.domain.model.Comment
import com.junechee.fish.presentation.component.FishTextField
import com.junechee.fish.presentation.theme.FishTheme

@Composable
fun CommentDialog(
    modifier: Modifier = Modifier,
    isMine: Boolean,
    comments: List<Comment>,
    visible: Boolean,
    onDismissRequest: () -> Unit,
    onCloseClick: () -> Unit = {},
    onDeleteComment: (Comment) -> Unit,
    onCommentSend: (String) -> Unit
) {
    if (visible) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            var text by remember { mutableStateOf("") }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(0.5f)
                            .imePadding()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                text = "${comments.size} reply"
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(onClick = onCloseClick) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "Close"
                                )
                            }
                        }
                        LazyColumn(
                            modifier = Modifier.weight(1f)
                        ) {
                            items(count = comments.size) { index ->
                                val comment = comments[index]

                                CommentCard(
                                    modifier = Modifier,
                                    isMine = isMine,
                                    profileImageUrl = comment.profileImageUrl,
                                    username = comment.username,
                                    text = comment.text,
                                    onDeleteComment = { onDeleteComment(comment) }
                                )
                            }
                        }
                        HorizontalDivider()
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            FishTextField(
                                modifier = Modifier.weight(1f),
                                value = text,
                                onValueChange = { text = it }
                            )
                            IconButton(onClick = {
                                onCommentSend(text)
                                text = ""
                            }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.Send,
                                    contentDescription = "Send"
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}

@Preview
@Composable
private fun CommentDialogPreview() {
    FishTheme {
        CommentDialog(
            isMine = true,
            visible = true,
            comments = emptyList(),
            onDismissRequest = {},
            onDeleteComment = {},
            onCloseClick = {},
            onCommentSend = {}
        )
    }

}