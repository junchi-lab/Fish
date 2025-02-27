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
import com.junechee.fish.domain.usecase.main.setting.GetMyUserUseCase
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
        myUserId = state.myUserId,
        boardCardModels = items,
        deleteBoardIds = state.deleteBoardIds,
        addedComments = state.addedComments,
        deletedComments = state.deletedComments,
        onOptionClick = { modelForDialog = it },
        onDeleteComment = viewModel::onDeleteComment,
        onCommentSend = viewModel::onCommentSend
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
    myUserId: Long,
    boardCardModels: LazyPagingItems<BoardCardModel>,
    deleteBoardIds: Set<Long> = emptySet(),
    addedComments: Map<Long, List<Comment>>,
    deletedComments: Map<Long, List<Comment>>,
    onOptionClick: (BoardCardModel) -> Unit,
    onDeleteComment: (Long, Comment) -> Unit,
    onCommentSend: (Long, String) -> Unit
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
                    val model:BoardCardModel = this
                    if (!deleteBoardIds.contains(this.boardId)) {
                        BoardCard(
                            isMine = model.userId == myUserId,
                            boardId = model.boardId,
                            username = model.username,
                            images = model.images,
                            text = model.text,
                            comments = model.comments + addedComments[model.boardId].orEmpty() - deletedComments[model.boardId].orEmpty()
                                .toSet(),
                            onOptionClick = { onOptionClick(model) },
                            onDeleteComment = onDeleteComment,
                            onCommentSend = onCommentSend
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