package com.junechee.fish.presentation.main.board

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.map
import com.junechee.fish.domain.model.ACTION_POSTED
import com.junechee.fish.domain.model.Comment
import com.junechee.fish.domain.usecase.main.board.DeleteBoardUseCase
import com.junechee.fish.domain.usecase.main.board.DeleteCommentUseCase
import com.junechee.fish.domain.usecase.main.board.GetBoardUseCase
import com.junechee.fish.domain.usecase.main.board.PostCommentUseCase
import com.junechee.fish.domain.usecase.main.setting.GetMyUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class BoardViewModel @Inject constructor(
    private val getMyUserUseCase: GetMyUserUseCase,
    private val getBoardUseCase: GetBoardUseCase,
    private val deleteBoardUseCase: DeleteBoardUseCase,
    private val postCommentUseCase: PostCommentUseCase,
    private val deleteCommentUseCase: DeleteCommentUseCase

) : ViewModel(), ContainerHost<BoardState, BoardSideEffect> {
    override val container: Container<BoardState, BoardSideEffect> = container(
        initialState = BoardState(),
        buildSettings = {
            this.exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                intent { postSideEffect(BoardSideEffect.Toast(throwable.message.orEmpty())) }
            }

        }
    )


    init {
        load()
    }

    fun load() = intent {
        val myUser = getMyUserUseCase().getOrThrow()
        val boardFlow = getBoardUseCase().getOrThrow()
        val boardCardModelFlow = boardFlow.map { pagingData ->
            pagingData.map { board ->
                board.toUiModel()
            }
        }

        reduce {
            state.copy(
                myUserId = myUser.id,
                boardCardModelFlow = boardCardModelFlow
            )
        }
    }

    fun onBoardDelete(model: BoardCardModel) = intent {
        deleteBoardUseCase(model.boardId).getOrThrow()
        reduce {
            state.copy(
                deleteBoardIds = state.deleteBoardIds + model.boardId
            )
        }
    }

    fun onCommentSend(boardId: Long, text: String) = intent {
        val user = getMyUserUseCase().getOrThrow()
        val commentId = postCommentUseCase(boardId, text).getOrThrow()
        reduce {
            val comment = Comment(
                id = commentId,
                text = text,
                username = user.username,
                profileImageUrl = user.profileImageUrl
            )
            val newAddedComments = state.addedComments + Pair(
                boardId,
                state.addedComments[boardId].orEmpty() + comment
            )

            state.copy(
                addedComments = newAddedComments
            )
        }


        postCommentUseCase(boardId, text)
    }

    fun onDeleteComment(boardId: Long, comment: Comment) = intent {
        val deleteCommentId = deleteCommentUseCase(
            boardId = boardId,
            commentId = comment.id
        ).getOrThrow()

        val newDeletedComments = state.deletedComments + Pair(
            boardId,
            state.deletedComments[boardId].orEmpty() + comment
        )

        reduce {
            state.copy(
                deletedComments = newDeletedComments
            )
        }
    }
}

data class BoardState(
    val myUserId: Long = -1L,
    val boardCardModelFlow: Flow<PagingData<BoardCardModel>> = emptyFlow(),
    val deleteBoardIds: Set<Long> = emptySet(),
    val addedComments: Map<Long, List<Comment>> = emptyMap(),
    val deletedComments: Map<Long, List<Comment>> = emptyMap()

)

sealed interface BoardSideEffect {
    class Toast(val message: String) : BoardSideEffect

}
