package com.junechee.fish.presentation.main.board

import android.widget.Toast
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.junechee.fish.domain.model.Comment
import com.junechee.fish.presentation.theme.FishTheme
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun BoardScreen(
    viewModel: BoardViewModel
) {
    val state = viewModel.collectAsState().value
    val items: LazyPagingItems<BoardCardModel> = state.boardCardModelFlow.collectAsLazyPagingItems()
    var modelForDialog: BoardCardModel? by remember { mutableStateOf(null) }
    val context = LocalContext.current

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is BoardSideEffect.Toast -> Toast.makeText(
                context,
                sideEffect.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    BoardScreen(
        boardCardModels = items,
        deleteBoardIds = state.deleteBoardIds,
        onOptionClick = { modelForDialog = it },
        onDeleteComment = viewModel::onDeleteComment
    )

    BoardOptionDialog(
        model = modelForDialog,
        onDismissRequest = { modelForDialog = null },
        onBoardDelete = viewModel::onBoardDelete
    )

}

@Composable
fun BoardScreen(
    modifier: Modifier = Modifier.padding(WindowInsets.navigationBars.asPaddingValues()),
    boardCardModels: LazyPagingItems<BoardCardModel>,
    deleteBoardIds: Set<Long> = emptySet(),
    onOptionClick: (BoardCardModel) -> Unit,
    onDeleteComment: (Comment) -> Unit
) {
    Surface {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(
                count = boardCardModels.itemCount,
                key = { index -> boardCardModels[index]?.boardId ?: index }
            ) { index ->
                boardCardModels[index]?.run {
                    if (!deleteBoardIds.contains(this.boardId)) {
                        BoardCard(
                            username = this.username,
                            images = this.images,
                            text = this.text,
                            comments = comments,
                            onOptionClick = { onOptionClick(this) },
                            onDeleteComment = {onDeleteComment(comments[index])},
                            onCommentSend = {}
                        )
                    }
                }
            }
        }

    }

}

@Preview
@Composable
private fun BoardScreenPreview() {
    FishTheme {
//        BoardScreen(
//            boardCardModels = listOf(
//                BoardCardModel(
//                    boardId = 4855,
//                    username = "aaaaaa",
//                    images = listOf(),
//                    text = "abceddfadfasf"
//                ),
//                BoardCardModel(
//                    boardId = 4856,
//                    username = "bbbbbbb",
//                    images = listOf(),
//                    text = "bbabdasfadfadsfasdfsdaf\nfadfadsfasdfsdaf\nfadfadsfasdfsdaf\nfadfadsfasdfsdaf\nfadfadsfasdfsdaf\nfadfadsfasdfsdaf"
//                ),
//                BoardCardModel(
//                    boardId = 4857,
//                    username = "cccccc",
//                    images = listOf(),
//                    text = "ccccccadfdsfasdf\nccccadfdsfasdf\nccccadfdsfasdf\nccccadfdsfasdf\nccccadfdsfasdf\nccccadfdsfasdf"
//                ),
//            ),
//            onOptionClick = {},
//            onReplyClick = {}
//        )
    }

}