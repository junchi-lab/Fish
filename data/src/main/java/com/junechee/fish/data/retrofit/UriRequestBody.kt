package com.junechee.fish.data.retrofit

import android.util.Log
import com.junechee.fish.domain.usecase.file.GetInputStreamUseCase
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.BufferedSink
import okio.FileNotFoundException
import okio.source
import okio.use

class UriRequestBody constructor(
    val contentUri: String,
    val getInputStreamUseCase: GetInputStreamUseCase,
    val contentType: MediaType? = null,
    val contentLength: Long
) : RequestBody() {

    override fun contentType(): MediaType? {
        return contentType
    }

    override fun contentLength(): Long {
        return contentLength
    }

    override fun writeTo(sink: BufferedSink) {
        try {
            getInputStreamUseCase(
                contentUri = contentUri
            ).getOrThrow()
                .use { inputStream ->
                    sink.writeAll(inputStream.source())
                }
        } catch (e: FileNotFoundException) {
            Log.e("UriRequestBody", e.message.orEmpty())
        }

    }
}