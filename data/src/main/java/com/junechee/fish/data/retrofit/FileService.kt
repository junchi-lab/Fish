package com.junechee.fish.data.retrofit

import com.junechee.fish.data.model.CommonResponse
import com.junechee.fish.data.model.FileDTO
import okhttp3.MultipartBody
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FileService {

    @POST("files")
    @Multipart
    @Headers("ContentType: multipart/form-data;")
    suspend fun uploadFile(
        @Part fileName: MultipartBody.Part,
        @Part file: MultipartBody.Part
    ): CommonResponse<FileDTO>
}