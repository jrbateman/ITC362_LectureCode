package com.bateman.pexelsapiview.api

import com.bateman.makingnetworkrequestspexel.api.GalleryItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoResponse(
    @Json(name = "photos") val galleryItems:List<GalleryItem>
    )