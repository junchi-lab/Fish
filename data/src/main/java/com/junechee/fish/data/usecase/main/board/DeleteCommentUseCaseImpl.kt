package com.junechee.fish.data.usecase.main.board

import com.junechee.fish.data.retrofit.BoardService
import com.junechee.fish.domain.usecase.main.board.DeleteBoardUseCase
import com.junechee.fish.domain.usecase.main.board.DeleteCommentUseCase
import javax.inject.Inject

class DeleteCommentUseCaseImpl @Inject constructor(
    private val boardService: BoardService
) : DeleteCommentUseCase {
    override suspend fun invoke(boardId: Long, commentId: Long): Result<Long> = kotlin.runCatching {
        boardService.deleteComment(
            boardId = boardId,
            commentId = commentId
        ).data
    }


}