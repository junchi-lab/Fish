package com.junechee.fish.data.usecase.file

import android.content.Context
import android.net.Uri
import com.junechee.fish.domain.usecase.file.GetInputStreamUseCase
import java.io.InputStream
import javax.inject.Inject

class GetInputStreamUseCaseImpl @Inject constructor(
    private val context: Context
) : GetInputStreamUseCase {

    override fun invoke(contentUri: String): Result<InputStream> = kotlin.runCatching {
        val uri = Uri.parse(contentUri)
        context.contentResolver.openInputStream(uri) ?: throw IllegalStateException("The InputStream can't be read")
    }
}