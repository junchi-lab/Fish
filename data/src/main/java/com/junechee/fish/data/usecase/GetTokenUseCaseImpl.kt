package com.junechee.fish.data.usecase

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.junechee.fish.data.UserDataStore
import com.junechee.fish.domain.usecase.GetTokenUseCase
import javax.inject.Inject



class GetTokenUseCaseImpl @Inject constructor(
    private val userDataStore: UserDataStore
) : GetTokenUseCase {

    override suspend fun invoke(): String? {
        return userDataStore.getToken()
    }


}