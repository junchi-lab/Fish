package com.junechee.fish.domain.usecase.login

interface LoginUseCase {

    suspend operator fun invoke(id: String, password: String): Result<String>
}