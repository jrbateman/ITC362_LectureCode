package com.bateman.makingnetworkrequestspexel.api

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PexelResponse(
    val photos: PhotoResponse
)