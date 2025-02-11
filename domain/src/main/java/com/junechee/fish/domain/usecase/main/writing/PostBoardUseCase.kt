package com.junechee.fish.domain.usecase.main.writing

import com.junechee.fish.domain.model.Image

interface PostBoardUseCase {
    suspend operator fun invoke(
        title: String,
        content: String,
        images: List<Image>
    )
}