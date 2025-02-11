package com.junechee.fish.data.model

import android.os.Parcelable
import com.junechee.fish.domain.model.Image
import kotlinx.parcelize.Parcelize

@Parcelize
class BoardParcel (
    val title: String,
    val content: String,
    val images: List<Image>
): Parcelable {
}