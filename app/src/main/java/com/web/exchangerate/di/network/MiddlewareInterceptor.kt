package com.web.exchangerate.di.network

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException

/**
 *Created by Yehor Kudimov on 06.01.2021.
 */
class MiddlewareInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Proceed
        val response = chain.proceed(originalRequest)
        val bodyString = response.body?.string()

        if (response.code == 200) {
            try {

                // Handle middleware errors here

               /* val errorResponse = try {
                    Gson().fromJson(bodyString, ErrorResponse::class.java)
                } catch (throwable: Throwable) {
                    null
                }
                if (errorResponse?.error != null) {
                    // send broadcast
                }*/
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }

        return response.newBuilder()
            .body(bodyString?.toResponseBody(response.body?.contentType()))
            .build()
    }
}