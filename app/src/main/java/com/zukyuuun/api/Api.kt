package com.zukyuuun.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.net.HttpURLConnection

abstract class Api {

    companion object {
        /**
         * ベースURL
         */
        const val BASE_URL = "https://randomuser.me/"
    }

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val client: OkHttpClient = OkHttpClient.Builder().build()

    val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    /**
     * エラーの場合のResponse
     */
    data class ErrorResponse(
        val statusCode: Int,
        val message: String?
    ) {
        companion object {
            /**
             * エラーレスポンスを作成
             */
            fun newErrorResponse(throwable: Throwable): ErrorResponse {
                val statusCode = when (throwable) {
                    is IOException -> 111
                    is HttpException -> {
                        when (throwable.code()) {
                            HttpURLConnection.HTTP_BAD_REQUEST -> 222
                            HttpURLConnection.HTTP_INTERNAL_ERROR -> 333
                            else -> -1
                        }
                    }
                    else -> -1
                }
                return ErrorResponse(statusCode, throwable.message)
            }
        }
    }
}
