package com.junechee.fish.data.di

import com.junechee.fish.data.usecase.ClearTokenUseCaseImpl
import com.junechee.fish.data.usecase.GetTokenUseCaseImpl
import com.junechee.fish.data.usecase.LoginUseCaseImpl
import com.junechee.fish.data.usecase.SetTokenUseCaseImpl
import com.junechee.fish.data.usecase.SignUpUseCaseImpl
import com.junechee.fish.domain.usecase.ClearTokenUseCase
import com.junechee.fish.domain.usecase.GetTokenUseCase
import com.junechee.fish.domain.usecase.LoginUseCase
import com.junechee.fish.domain.usecase.SetTokenUseCase
import com.junechee.fish.domain.usecase.SignUpUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserModule {
    @Binds
    abstract fun bindLoginUseCase(uc: LoginUseCaseImpl): LoginUseCase

    @Binds
    abstract fun bindSignUpUseCase(uc: SignUpUseCaseImpl): SignUpUseCase

    @Binds
    abstract fun bindSetTokenUseCase(uc: SetTokenUseCaseImpl): SetTokenUseCase

    @Binds
    abstract fun bindGetTokenUseCase(uc: GetTokenUseCaseImpl): GetTokenUseCase

    @Binds
    abstract fun bindClearTokenUseCase(uc: ClearTokenUseCaseImpl): ClearTokenUseCase
}