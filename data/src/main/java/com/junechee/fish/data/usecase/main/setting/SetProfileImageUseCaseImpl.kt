package com.junechee.fish.data.usecase.main.setting

import com.junechee.fish.data.di.FISH_HOST
import com.junechee.fish.domain.usecase.file.GetImageUseCase
import com.junechee.fish.domain.usecase.file.UploadImageUseCase
import com.junechee.fish.domain.usecase.main.setting.GetMyUserUseCase
import com.junechee.fish.domain.usecase.main.setting.SetMyUserUseCase
import com.junechee.fish.domain.usecase.main.setting.SetProfileImageUseCase
import javax.inject.Inject

class SetProfileImageUseCaseImpl @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase,
    private val getImageUseCase: GetImageUseCase,
    private val setMyUserUseCase: SetMyUserUseCase,
    private val getMyUserUseCase: GetMyUserUseCase
) : SetProfileImageUseCase {
    override suspend fun invoke(contentUri: String): Result<Unit> = kotlin.runCatching {
        // 0. 내 정보 가져오기
        val user = getMyUserUseCase().getOrThrow()
        // 1. 이미지 정보 가져오기
        val image = getImageUseCase(contentUri = contentUri)
            ?: throw NullPointerException("Image can't be find")

        // 2. 이미지 서버에 업로드 하기
        val imagePath = uploadImageUseCase(image).getOrThrow()

        // 3. 내 정보 업데이트 하기
        setMyUserUseCase(
            profileImageUrl = imagePath
        ).getOrThrow()
    }
}