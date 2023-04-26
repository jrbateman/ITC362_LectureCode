package com.bateman.msu.makingnetworkrequests.api

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class FlickrResponse(
    val photos: PhotoResponse
)