package com.junechee.fish.data.usecase

import com.junechee.fish.data.UserDataStore
import com.junechee.fish.domain.usecase.login.ClearTokenUseCase
import javax.inject.Inject

class ClearTokenUseCaseImpl @Inject constructor(
    private val userDataStore: UserDataStore
) : ClearTokenUseCase {

    override suspend fun invoke(): Result<Unit> = kotlin.runCatching {
        userDataStore.clear()
    }
}