package com.bateman.makingnetworkrequestspexel.api

import com.bateman.pexelsapiview.api.PhotoResponse
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PexelResponse(
    val photos: PhotoResponse
)