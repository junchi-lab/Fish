package com.junechee.fish.domain.usecase

interface GetTokenUseCase {
    suspend operator fun invoke() : String?
}