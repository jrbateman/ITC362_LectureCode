package com.bateman.msu.makingnetworkrequests

import com.bateman.msu.makingnetworkrequests.api.FlickrApi
import com.bateman.msu.makingnetworkrequests.api.GalleryItem
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
//import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.random.Random

class PhotoRepository() {

    private val flickrApi: FlickrApi
    val startDate = LocalDate.of(2000, 1, 1)
    val endDate = LocalDate.of(2024, 12, 31)
    val randdate = randomDate(startDate,endDate).toString()


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
    suspend fun fetchPhotos(date: String): List<GalleryItem> =
        flickrApi.fetchPhotos(date = date).photos.galleryItems
       // flickrApi.fetchPhotos(date).photos.galleryItems

    fun randomDate(start: LocalDate, end: LocalDate): LocalDate {
        val daysBetween = ChronoUnit.DAYS.between(start, end)
        val randomDays = Random.nextLong(daysBetween + 1)
        return start.plusDays(randomDays)
    }


 }