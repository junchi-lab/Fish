package com.junechee.fish.domain.usecase.main.writing

import com.junechee.fish.domain.model.Image

interface GetImageListUseCase {
    suspend operator fun invoke(): List<Image>
}