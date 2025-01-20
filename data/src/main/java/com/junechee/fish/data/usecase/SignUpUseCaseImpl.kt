package com.junechee.fish.data.usecase

import com.junechee.fish.data.model.SignUpParam
import com.junechee.fish.data.retrofit.UserService
import com.junechee.fish.domain.usecase.login.SignUpUseCase
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(
    private val userService: UserService
) : SignUpUseCase {

    override suspend fun invoke(id: String, username: String, password: String): Result<Boolean> =
        runCatching {
            val requestBody = SignUpParam(
                loginId = id,
                name = username,
                password = password,
                extraUserInfo = "",
                profileFilePath = ""
            ).toRequestBody()

            userService.signUp(requestBody = requestBody).result == "SUCCESS"
        }
}