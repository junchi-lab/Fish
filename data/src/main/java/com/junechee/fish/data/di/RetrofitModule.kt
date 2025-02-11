package com.junechee.fish.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.junechee.fish.data.retrofit.BoardService
import com.junechee.fish.data.retrofit.FileService
import com.junechee.fish.data.retrofit.RetrofitIntercept
import com.junechee.fish.data.retrofit.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

val FISH_HOST = "http://172.30.1.48:8080"

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun provideOkHttpClient(intercept: RetrofitIntercept): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(intercept)
            .build()
    }

    @Suppress("JSON_FORMAT_REDUNDANT")
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val converterFactory = Json {
            ignoreUnknownKeys = true
        }.asConverterFactory("application/json; charset=UTF8".toMediaType())

        return Retrofit.Builder()
            .baseUrl("${FISH_HOST}/api/")
            .addConverterFactory(converterFactory)
            .client(client)
            .build()
    }

    @Provides
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    fun provideFileService(retrofit: Retrofit): FileService {
        return retrofit.create(FileService::class.java)
    }

    @Provides
    fun provideBoardService(retrofit: Retrofit): BoardService {
        return retrofit.create(BoardService::class.java)
    }
}