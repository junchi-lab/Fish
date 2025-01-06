package com.junechee.fish.domain.usecase

interface LoginUseCase {

    suspend operator fun invoke(id: String, password: String): Result<String>
}