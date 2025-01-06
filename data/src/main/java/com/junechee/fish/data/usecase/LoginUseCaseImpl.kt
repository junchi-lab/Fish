package com.junechee.fish.data.usecase

import com.junechee.fish.domain.usecase.LoginUseCase
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor() : LoginUseCase {
    override suspend fun invoke(id: String, password: String): Result<String> = runCatching {
        "token"
    }
}