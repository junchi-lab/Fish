package com.junechee.fish.data.retrofit

import com.junechee.fish.data.model.CommonResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface BoardService {

    @POST("boards")
    suspend fun postBoard(
        @Body requestBody: RequestBody
    ): CommonResponse<Long>
}