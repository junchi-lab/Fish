package com.junechee.fish.data.usecase

import com.junechee.fish.data.UserDataStore
import com.junechee.fish.domain.usecase.login.GetTokenUseCase
import javax.inject.Inject



class GetTokenUseCaseImpl @Inject constructor(
    private val userDataStore: UserDataStore
) : GetTokenUseCase {

    override suspend fun invoke(): String? {
        return userDataStore.getToken()
    }


}