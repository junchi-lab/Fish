package com.junechee.fish.data.usecase

import com.junechee.fish.data.UserDataStore
import com.junechee.fish.domain.usecase.ClearTokenUseCase
import javax.inject.Inject

class ClearTokenUseCaseImpl @Inject constructor(
    private val userDataStore: UserDataStore
): ClearTokenUseCase {

    override suspend fun invoke() {
        userDataStore.clear()
    }
}