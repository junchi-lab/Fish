package com.junechee.fish.domain.usecase.main.setting

import com.junechee.fish.domain.model.User

interface GetMyUserUseCase {
    suspend operator fun invoke() : Result<User>
}