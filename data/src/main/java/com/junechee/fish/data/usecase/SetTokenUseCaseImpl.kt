package com.junechee.fish.data.usecase

import com.junechee.fish.data.UserDataStore
import com.junechee.fish.domain.usecase.SetTokenUseCase
import javax.inject.Inject

class SetTokenUseCaseImpl @Inject constructor(
    private val userDataStore: UserDataStore
): SetTokenUseCase {

    override suspend fun invoke(token: String) {
        userDataStore.setToken(token)
    }
}