package com.junechee.fish.data.di

import com.junechee.fish.data.usecase.ClearTokenUseCaseImpl
import com.junechee.fish.data.usecase.GetTokenUseCaseImpl
import com.junechee.fish.data.usecase.LoginUseCaseImpl
import com.junechee.fish.data.usecase.SetTokenUseCaseImpl
import com.junechee.fish.data.usecase.SignUpUseCaseImpl
import com.junechee.fish.data.usecase.main.setting.GetMyUserUseCaseImpl
import com.junechee.fish.domain.usecase.login.ClearTokenUseCase
import com.junechee.fish.domain.usecase.login.GetTokenUseCase
import com.junechee.fish.domain.usecase.login.LoginUseCase
import com.junechee.fish.domain.usecase.login.SetTokenUseCase
import com.junechee.fish.domain.usecase.login.SignUpUseCase
import com.junechee.fish.domain.usecase.main.setting.GetMyUserUseCase
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

    @Binds
    abstract fun bindGetMyUserUseCase(uc: GetMyUserUseCaseImpl): GetMyUserUseCase
}