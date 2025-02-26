package com.junechee.fish.data.di

import com.junechee.fish.data.usecase.main.board.DeleteBoardUseCaseImpl
import com.junechee.fish.data.usecase.main.board.GetBoardUseCaseImpl
import com.junechee.fish.domain.usecase.main.board.DeleteBoardUseCase
import com.junechee.fish.domain.usecase.main.board.GetBoardUseCase
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
}