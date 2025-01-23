package com.junechee.fish.presentation.main.setting

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.junechee.fish.domain.usecase.login.ClearTokenUseCase
import com.junechee.fish.domain.usecase.main.setting.GetMyUserUseCase
import com.junechee.fish.domain.usecase.main.setting.SetMyUserUseCase
import com.junechee.fish.domain.usecase.main.setting.SetProfileImageUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val clearTokenUseCase: ClearTokenUseCase,
    private val getMyUserUseCase: GetMyUserUseCase,
    private val setMyUserUseCase: SetMyUserUseCase,
    private val setProfileImageUseCase: SetProfileImageUseCase

) : ViewModel(), ContainerHost<SettingState, SettingSideEffect> {


    override val container: Container<SettingState, SettingSideEffect> =
        container(
            initialState = SettingState(),
            buildSettings = {
                this.exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                    intent {
                        postSideEffect(SettingSideEffect.Toast(throwable.message.orEmpty()))
                    }

                }
            }
        )

    init {
        load()
    }

    private fun load() = intent {
        val user = getMyUserUseCase().getOrThrow()

        reduce {
            state.copy(
                profileImageUrl = user.profileImageUrl,
                username = user.username
            )
        }

        Log.i("SettingViewModel State","fileUrl : ${user.profileImageUrl}")

    }


    fun onLogoutClick() = intent {
        clearTokenUseCase().getOrThrow()
        postSideEffect(SettingSideEffect.NavigateToLoginActivity)
    }

    fun onUsernameChange(username: String) = intent {
        setMyUserUseCase(
            userName = username
        ).getOrThrow()
        load()

    }

    fun onImageViewChange(contentUri: Uri?) = intent {
        setProfileImageUseCase(
            contentUri = contentUri.toString()
        ).getOrThrow()
        load()
    }

}

@Immutable
data class SettingState(
    val profileImageUrl: String? = null,
    val username: String = ""
)

sealed interface SettingSideEffect {
    class Toast(val message: String) : SettingSideEffect
    object NavigateToLoginActivity : SettingSideEffect
}
