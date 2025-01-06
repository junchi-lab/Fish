package com.junechee.fish.domain.usecase

interface SingUpUseCase {
    suspend operator fun invoke(id: String, username: String, password: String): Result<String>
}