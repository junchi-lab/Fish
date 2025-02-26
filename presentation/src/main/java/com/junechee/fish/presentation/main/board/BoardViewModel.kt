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
import com.junechee.fish.domain.usecase.main.board.GetBoardUseCase
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
    private val getBoardUseCase: GetBoardUseCase,
    private val deleteBoardUseCase: DeleteBoardUseCase

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
        val boardFlow = getBoardUseCase().getOrThrow()
        val boardCardModelFlow = boardFlow.map { pagingData ->
            pagingData.map { board ->
                board.toUiModel()
            }
        }

        reduce {
            state.copy(
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

    fun onDeleteComment(comment: Comment) = intent{

    }
}

data class BoardState(
    val boardCardModelFlow: Flow<PagingData<BoardCardModel>> = emptyFlow(),
    val deleteBoardIds: Set<Long> = emptySet()

)

sealed interface BoardSideEffect {
    class Toast(val message: String) : BoardSideEffect

}
