package com.junechee.fish.domain.usecase.file

import com.junechee.fish.domain.model.Image

interface GetImageUseCase {
    operator fun invoke(contentUri: String): Image?
}