package com.junechee.fish.data.di

import com.junechee.fish.data.usecase.main.board.DeleteBoardUseCaseImpl
import com.junechee.fish.data.usecase.main.board.DeleteCommentUseCaseImpl
import com.junechee.fish.data.usecase.main.board.GetBoardUseCaseImpl
import com.junechee.fish.data.usecase.main.board.PostCommentUseCaseImpl
import com.junechee.fish.domain.usecase.main.board.DeleteBoardUseCase
import com.junechee.fish.domain.usecase.main.board.DeleteCommentUseCase
import com.junechee.fish.domain.usecase.main.board.GetBoardUseCase
import com.junechee.fish.domain.usecase.main.board.PostCommentUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class BoardModule {
    @Binds
    abstract fun bindGetBoardUseCase(uc: GetBoardUseCaseImpl): GetBoardUseCase

    @Binds
    abstract fun bindDeleteBoardUseCase(uc: DeleteBoardUseCaseImpl): DeleteBoardUseCase

    @Binds
    abstract fun bindPostCommentUseCase(uc: PostCommentUseCaseImpl): PostCommentUseCase

    @Binds
    abstract fun bindDeleteCommentUseCase(uc: DeleteCommentUseCaseImpl): DeleteCommentUseCase

}