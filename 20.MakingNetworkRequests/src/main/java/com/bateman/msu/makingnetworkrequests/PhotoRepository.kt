package com.bateman.msu.makingnetworkrequests

import com.bateman.msu.makingnetworkrequests.api.FlickrApi
import com.bateman.msu.makingnetworkrequests.api.GalleryItem
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
//import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

class PhotoRepository {

    private val flickrApi: FlickrApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            //.addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        flickrApi = retrofit.create()
    }
    //suspend fun fetchContents() = flickrApi.fetchContents()
    //suspend fun fetchPhotos() = flickrApi.fetchPhotos()
    suspend fun fetchPhotos(): List<GalleryItem> =
        flickrApi.fetchPhotos().photos.galleryItems

 }