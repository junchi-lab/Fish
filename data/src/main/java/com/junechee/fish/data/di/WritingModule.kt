package com.junechee.fish.data.di

import com.junechee.fish.data.usecase.main.writing.GetImageListUseCaseImpl
import com.junechee.fish.data.usecase.main.writing.PostBoardUseCaseImpl
import com.junechee.fish.domain.usecase.main.writing.GetImageListUseCase
import com.junechee.fish.domain.usecase.main.writing.PostBoardUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class WritingModule {

    @Binds
    abstract fun bindGetImageListUseCase(us: GetImageListUseCaseImpl): GetImageListUseCase

    @Binds
    abstract fun bindPostBoardUseCase(us: PostBoardUseCaseImpl): PostBoardUseCase
}