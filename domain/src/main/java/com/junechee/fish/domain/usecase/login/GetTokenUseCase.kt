package com.junechee.fish.domain.usecase.login

interface GetTokenUseCase {
    suspend operator fun invoke() : String?
}