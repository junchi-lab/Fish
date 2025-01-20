package com.junechee.fish.domain.usecase.login

interface SetTokenUseCase {
    suspend operator fun invoke(token: String)
}