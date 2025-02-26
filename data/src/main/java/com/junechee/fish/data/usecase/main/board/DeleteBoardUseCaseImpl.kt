package com.junechee.fish.data.usecase.main.board

import com.junechee.fish.data.retrofit.BoardService
import com.junechee.fish.domain.usecase.main.board.DeleteBoardUseCase
import javax.inject.Inject

class DeleteBoardUseCaseImpl @Inject constructor(
    private val boardService: BoardService

) : DeleteBoardUseCase {

    override suspend fun invoke(boardId: Long): Result<Long> = kotlin.runCatching{
        boardService.deleteBoard(boardId).data
    }
}