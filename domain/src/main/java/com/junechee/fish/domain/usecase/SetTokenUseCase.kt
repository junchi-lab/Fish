package com.junechee.fish.domain.usecase

interface SetTokenUseCase {
    suspend operator fun invoke(token: String)
}