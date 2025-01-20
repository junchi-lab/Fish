package com.junechee.fish.data.usecase.main.setting

import com.junechee.fish.data.model.toDomainModel
import com.junechee.fish.data.retrofit.UserService
import com.junechee.fish.domain.model.User
import com.junechee.fish.domain.usecase.main.setting.GetMyUserUseCase
import javax.inject.Inject

class GetMyUserUseCaseImpl @Inject constructor(
    private val userService: UserService
) : GetMyUserUseCase {

    override suspend fun invoke(): Result<User> = kotlin.runCatching{
        userService.myPage().data.toDomainModel()
    }

}