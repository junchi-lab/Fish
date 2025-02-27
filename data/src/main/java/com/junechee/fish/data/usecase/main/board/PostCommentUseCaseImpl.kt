package com.junechee.fish.data.usecase.main.board

import com.junechee.fish.data.model.comment.CommentParam
import com.junechee.fish.data.retrofit.BoardService
import com.junechee.fish.domain.usecase.main.board.PostCommentUseCase
import javax.inject.Inject

class PostCommentUseCaseImpl @Inject constructor(
    private val boardService: BoardService
) : PostCommentUseCase {
    override suspend fun invoke(boardId: Long, text: String): Result<Long> = kotlin.runCatching {
        val requestBody = CommentParam(text).toRequestBody()
        boardService.postComment(
            boardId = boardId,
            requestBody = requestBody
        ).data
    }
}