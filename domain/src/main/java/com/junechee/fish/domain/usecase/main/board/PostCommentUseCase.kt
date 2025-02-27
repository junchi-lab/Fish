package com.junechee.fish.domain.usecase.main.board

interface PostCommentUseCase {

    suspend operator fun invoke(
        boardId: Long,
        text: String
    ): Result<Long>
}