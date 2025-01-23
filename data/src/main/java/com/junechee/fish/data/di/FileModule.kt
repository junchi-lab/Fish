package com.junechee.fish.data.di

import com.junechee.fish.data.usecase.file.GetImageUseCaseImpl
import com.junechee.fish.data.usecase.file.GetInputStreamUseCaseImpl
import com.junechee.fish.data.usecase.file.UploadImageUseCaseImpl
import com.junechee.fish.domain.usecase.file.GetImageUseCase
import com.junechee.fish.domain.usecase.file.GetInputStreamUseCase
import com.junechee.fish.domain.usecase.file.UploadImageUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FileModule {

    @Binds
    abstract fun bindGetInputStreamUseCase(uc: GetInputStreamUseCaseImpl): GetInputStreamUseCase

    @Binds
    abstract fun bindGetImageUseCase(uc: GetImageUseCaseImpl): GetImageUseCase

    @Binds
    abstract fun bindUploadImageUseCase(uc: UploadImageUseCaseImpl): UploadImageUseCase
}