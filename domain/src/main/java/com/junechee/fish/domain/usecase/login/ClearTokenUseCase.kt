package com.junechee.fish.domain.usecase.login

interface ClearTokenUseCase {
    suspend operator fun invoke() : Result<Unit>
}