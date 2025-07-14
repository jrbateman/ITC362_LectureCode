package com.bateman.makingnetworkrequestspexel.api



import retrofit2.http.GET
import retrofit2.http.Query

 interface PexelApi {
        @GET("curated")
        suspend fun fetchPhotos(@Query("per_page") perPage: Int = 100,
                                @Query("page") page: Int = 3): PhotoResponse
 }
