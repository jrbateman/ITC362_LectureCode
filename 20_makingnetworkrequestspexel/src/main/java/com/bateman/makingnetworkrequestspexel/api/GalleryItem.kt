package com.bateman.makingnetworkrequestspexel.api


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GalleryItem(
    @Json(name = "id") val id: Int,
    @Json(name = "width") val width: Int,
    @Json(name = "height") val height: Int,
    @Json(name = "url") val url: String, // Link to the Pexels page for the photo
    @Json(name = "photographer") val photographer: String,
    @Json(name = "photographer_url") val photographerUrl: String,
    @Json(name = "avg_color") val avgColor: String,
    @Json(name = "src") val src: PhotoSource, // Nested object for image sources
    @Json(name = "liked") val liked: Boolean,
    @Json(name = "alt") val alt: String
)

@JsonClass(generateAdapter = true)
data class PhotoSource(
    @Json(name = "original") val original: String,
    @Json(name = "large2x") val large2x: String,
    @Json(name = "large") val large: String,
    @Json(name = "medium") val medium: String,
    @Json(name = "small") val small: String,
    @Json(name = "portrait") val portrait: String,
    @Json(name = "landscape") val landscape: String,
    @Json(name = "tiny") val tiny: String
)