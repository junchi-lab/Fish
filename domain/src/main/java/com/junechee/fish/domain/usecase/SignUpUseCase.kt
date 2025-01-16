package com.junechee.fish.domain.usecase

interface SignUpUseCase {
    suspend operator fun invoke(id: String, username: String, password: String): Result<Boolean>
}