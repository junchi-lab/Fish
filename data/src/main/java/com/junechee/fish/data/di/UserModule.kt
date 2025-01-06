package com.junechee.fish.data.di

import com.junechee.fish.data.usecase.LoginUseCaseImpl
import com.junechee.fish.domain.usecase.LoginUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserModule {
    @Binds
    abstract fun bindLoginUseCase(uc: LoginUseCaseImpl): LoginUseCase
}