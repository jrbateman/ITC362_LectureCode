package com.bateman.makingnetworkrequestspexel.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "EeTHpjNruPG29aBRE8YT8X3J4iMZisBbz0zbGZXv9W9PRYfswzmo6a9Z")
            .build()
        return chain.proceed(request)
    }
}