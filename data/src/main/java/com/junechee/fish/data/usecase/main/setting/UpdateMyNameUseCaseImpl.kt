package com.junechee.fish.data.usecase.main.setting

import com.junechee.fish.data.model.UpdateMyInfoParam
import com.junechee.fish.data.retrofit.UserService
import com.junechee.fish.domain.usecase.main.setting.GetMyUserUseCase
import com.junechee.fish.domain.usecase.main.setting.UpdateMyNameUseCase
import javax.inject.Inject

class UpdateMyNameUseCaseImpl @Inject constructor(
    private val userService: UserService,
    private val getMyUserUseCase: GetMyUserUseCase
) : UpdateMyNameUseCase {
    override suspend fun invoke(userName: String): Result<Unit> = kotlin.runCatching {

        val user = getMyUserUseCase().getOrThrow()
        val requestBody = UpdateMyInfoParam(
            userName = userName,
            extraUserInfo = user.profileImageUrl.orEmpty(),
            profileFilePath = ""
        ).toRequestBody()

        userService.patchMyPage(requestBody)
    }
}