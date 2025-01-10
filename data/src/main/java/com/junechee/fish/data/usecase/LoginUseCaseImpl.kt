package com.junechee.fish.data.usecase

import com.junechee.fish.data.model.LoginParam
import com.junechee.fish.data.retrofit.UserService
import com.junechee.fish.domain.usecase.LoginUseCase
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val userService: UserService
) : LoginUseCase {
    override suspend fun invoke(id: String, password: String): Result<String> = runCatching {
        val requestBody = LoginParam(loginId = id, password = password).toRequestBody()
        userService.login(requestBody = requestBody).data
    }
}