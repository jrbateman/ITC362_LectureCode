package com.bateman.msu.makingnetworkrequests.api

import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.random.Random

//https://api.flickr.com/services/rest/?method=flickr.interestingness.getList&api_key=38b292a5b6956815cb6fbd066900db3f&format=json&nojsoncallback=1&extras=url_s


private const val API_KEY = "38b292a5b6956815cb6fbd066900db3f"

interface FlickrApi {

    // @GET("/")

   @GET("services/rest/")
    suspend fun fetchPhotos(
        @Query("method") method: String = "flickr.interestingness.getList",
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1,
        @Query("extras") extras: String = "url_s",
        @Query("date") date: String
    ): FlickrResponse

    // suspend fun fetchPhotos(date: String): FlickrResponse


   /* @GET(
        "services/rest/?method=flickr.interestingness.getList" +
                "&api_key=$API_KEY" +
                "&format=json" +
                "&nojsoncallback=1" +
                "&extras=url_s" +
                "&date=date"
    )

    suspend fun fetchPhotos(date: String): FlickrResponse*/
   // suspend fun fetchContents(): String

}
