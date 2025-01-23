package com.junechee.fish.data.usecase.main.setting

import com.junechee.fish.data.model.UpdateMyInfoParam
import com.junechee.fish.data.retrofit.UserService
import com.junechee.fish.domain.usecase.main.setting.GetMyUserUseCase
import com.junechee.fish.domain.usecase.main.setting.SetMyUserUseCase
import javax.inject.Inject

class SetMyUserUseCaseImpl @Inject constructor(
    private val userService: UserService,
    private val getMyUserUseCase: GetMyUserUseCase
) : SetMyUserUseCase {

    override suspend fun invoke(userName: String?, profileImageUrl: String?): Result<Unit> = kotlin.runCatching{
        val user = getMyUserUseCase().getOrThrow()
        val requestBody = UpdateMyInfoParam(
            userName = userName ?: user.username,
            profileFilePath =  profileImageUrl ?: user.profileImageUrl.orEmpty(),
            extraUserInfo = ""
        ).toRequestBody()

        userService.patchMyPage(requestBody)
    }
}