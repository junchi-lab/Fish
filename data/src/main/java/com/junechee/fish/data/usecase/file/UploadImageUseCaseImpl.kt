package com.junechee.fish.data.usecase.file

import com.junechee.fish.data.di.FISH_HOST
import com.junechee.fish.data.retrofit.FileService
import com.junechee.fish.data.retrofit.UriRequestBody
import com.junechee.fish.domain.model.Image
import com.junechee.fish.domain.usecase.file.GetInputStreamUseCase
import com.junechee.fish.domain.usecase.file.UploadImageUseCase
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import javax.inject.Inject

class UploadImageUseCaseImpl @Inject constructor(
    private val fileService: FileService,
    private val getInputStreamUseCase: GetInputStreamUseCase
) : UploadImageUseCase {
    override suspend fun invoke(image: Image): Result<String> = kotlin.runCatching {

        val fileNamePart = MultipartBody.Part.createFormData("fileName", image.name)

        val requestBody = UriRequestBody(
            contentUri = image.uri,
            getInputStreamUseCase = getInputStreamUseCase,
            contentType = image.mimeType.toMediaType(),
            contentLength = image.size
        )
        val filePart = MultipartBody.Part.createFormData("file", image.name, requestBody)

        "$FISH_HOST/" +fileService.uploadFile(
            fileName = fileNamePart,
            file = filePart
        ).data.filePath
    }
}