package com.junechee.fish.presentation.main.board

import androidx.compose.runtime.Immutable
import com.junechee.fish.domain.model.Board
import com.junechee.fish.domain.model.Comment

@Immutable
data class BoardCardModel(
    val boardId: Long,
    val username: String,
    val images: List<String>,
    val text: String,
    val comments: List<Comment>
)

fun Board.toUiModel(): BoardCardModel {
    return BoardCardModel(
        boardId = this.id,
        username = this.username,
        images = this.images,
        text = this.content,
        comments = this.comments
    )
}
