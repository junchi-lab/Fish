package com.junechee.fish.domain.usecase.file

import com.junechee.fish.domain.model.Image

interface UploadImageUseCase {

    suspend operator fun invoke(
        image: Image
    ) : Result<String>
}