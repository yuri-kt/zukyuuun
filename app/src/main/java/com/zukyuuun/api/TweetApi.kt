package com.zukyuuun.api

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import retrofit2.http.GET
import com.squareup.moshi.Json

/**
 *
 */
class TweetApi : Api() {

    /**
     *
     */
    interface Service {
        @GET("api")
        fun getTweetAsync(): Deferred<Response>
    }

    /**
     *
     */
    fun get(callback: (response: Pair<Response?, ErrorResponse?>) -> Unit) {
        val service = retrofit.create(Service::class.java)
        GlobalScope.launch {
            try {
                val response = withContext(Dispatchers.Default) { service.getTweetAsync().await() }
                callback(Pair(response, null))
            } catch (t: Throwable) {
                callback(Pair(null, ErrorResponse.newErrorResponse(t)))
            }
        }
    }

    data class Response(
        @Json(name = "results")
        val result: Any
    )
}
