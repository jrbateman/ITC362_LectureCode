package com.bateman.msu.makingnetworkrequests

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bateman.msu.makingnetworkrequests.api.GalleryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.http.Body
import retrofit2.http.Field
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.random.Random

private const val TAG = "PhotoGalleryViewModel"

class PhotoGalleryViewModel : ViewModel() {

    val startDate = LocalDate.of(2000, 1, 1)
    val endDate = LocalDate.of(2024, 12, 31)
        val randate = randomDate(startDate,endDate).toString()

    private val photoRepository = PhotoRepository()

    private val _galleryItems: MutableStateFlow<List<GalleryItem>> =
        MutableStateFlow(emptyList())
    val galleryItems: StateFlow<List<GalleryItem>>
        get() = _galleryItems.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val items = photoRepository.fetchPhotos( randate)
                Log.d(TAG, "Items received: $items")
                _galleryItems.value = items
            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch gallery items", ex)
            }
        }
    }

  // Generate Random Date
    fun randomDate(start: LocalDate, end: LocalDate): LocalDate {
        val daysBetween = ChronoUnit.DAYS.between(start, end)
        val randomDays = Random.nextLong(daysBetween + 1)
        return start.plusDays(randomDays)
    }


}
