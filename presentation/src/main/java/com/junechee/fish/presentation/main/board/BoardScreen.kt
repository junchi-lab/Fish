package com.junechee.fish.presentation.main.board

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.junechee.fish.presentation.theme.FishTheme

@Composable
fun BoardScreen(
    modifier: Modifier = Modifier.padding(WindowInsets.navigationBars.asPaddingValues()),
    boardCardModels: List<BoardCardModel>,
    onOptionClick: (BoardCardModel) -> Unit,
    onReplyClick: (BoardCardModel) -> Unit

) {
    Surface {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(
                count = boardCardModels.size,
                key = { index ->
                    boardCardModels[index].boardId
                }
            ) { index ->
                val boardCardModel = boardCardModels[index]

                BoardCard(
                    username = boardCardModel.username,
                    images = boardCardModel.images,
                    text = boardCardModel.text,
                    onOptionClick = { onOptionClick(boardCardModel) },
                    onReplyClick = { onReplyClick(boardCardModel) }
                )
            }
        }

    }

}

@Preview
@Composable
private fun BoardScreenPreview() {
    FishTheme {
        BoardScreen(
            boardCardModels = listOf(
                BoardCardModel(
                    boardId = 4855,
                    username = "aaaaaa",
                    images = listOf(),
                    text = "abceddfadfasf"
                ),
                BoardCardModel(
                    boardId = 4856,
                    username = "bbbbbbb",
                    images = listOf(),
                    text = "bbabdasfadfadsfasdfsdaf\nfadfadsfasdfsdaf\nfadfadsfasdfsdaf\nfadfadsfasdfsdaf\nfadfadsfasdfsdaf\nfadfadsfasdfsdaf"
                ),
                BoardCardModel(
                    boardId = 4857,
                    username = "cccccc",
                    images = listOf(),
                    text = "ccccccadfdsfasdf\nccccadfdsfasdf\nccccadfdsfasdf\nccccadfdsfasdf\nccccadfdsfasdf\nccccadfdsfasdf"
                ),
            ),
            onOptionClick = {},
            onReplyClick = {}
        )
    }

}