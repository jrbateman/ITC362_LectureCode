package com.bateman.makingnetworkrequestspexel

import com.bateman.makingnetworkrequestspexel.api.AuthInterceptor
import com.bateman.makingnetworkrequestspexel.api.GalleryItem
import com.bateman.makingnetworkrequestspexel.api.PexelApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create


class PhotoRepository {

    private val pexelApi: PexelApi

    init{
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.pexels.com/v1/")
            .client(client) // Attach the custom client with authentication
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        pexelApi = retrofit.create<PexelApi>()

    }

    suspend fun fetchPhotos(page: Int):List<GalleryItem> = pexelApi.fetchPhotos(page = page).galleryItems //   photos.galleryItems



}






