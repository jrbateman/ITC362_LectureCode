package com.bateman.msu.makingnetworkrequests.api

import retrofit2.http.GET

private const val API_KEY = "38b292a5b6956815cb6fbd066900db3f"

interface FlickrApi {

   // @GET("/")

    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
                "&api_key=$API_KEY" +
                "&format=json" +
                "&nojsoncallback=1" +
                "&extras=url_s"
    )

    // suspend fun fetchContents(): String
    suspend fun fetchPhotos(): FlickrResponse



}