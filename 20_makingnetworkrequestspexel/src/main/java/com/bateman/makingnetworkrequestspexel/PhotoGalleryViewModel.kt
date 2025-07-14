package com.bateman.makingnetworkrequestspexel

import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bateman.makingnetworkrequestspexel.api.GalleryItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.random.Random

private const val TAG = "PhotoGalleryViewModel"

open class PhotoGalleryViewModel :ViewModel() {

    private val photoRepository = PhotoRepository()
    private var page = 1

    private val _galleryItems: MutableStateFlow<List<GalleryItem>> =
        MutableStateFlow(emptyList())
    open val galleryItems: Flow<List<GalleryItem>>
        get() = _galleryItems.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                var items = photoRepository.fetchPhotos(page)
                page ++
                Log.d(TAG, "Initial Items received: $items")

                while (items.isEmpty()) {
                    val reloadedItems = photoRepository.fetchPhotos(page)
                    items = reloadedItems
                    Log.d(TAG, " Reloaded Items received: $items")

                }


               //  _galleryItems.value = items
               _galleryItems.value = filterOutNullUrls(items)


            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch Initial gallery items", ex)
                refreshData()
            }
        }
    }

    fun getStuff(progressBar : ProgressBar){

        viewModelScope.launch {
            try {
                progressBar.visibility = View.GONE
                var items = photoRepository.fetchPhotos(page)
                page++
                Log.d(TAG, " Refreshed Items received: $items")
               //   _galleryItems.value = items
              _galleryItems.value = filterOutNullUrls(items)


            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch Refresh gallery items", ex)
            }
        }
    }


    fun refreshData(){

        viewModelScope.launch {
            try {

                var items = photoRepository.fetchPhotos(page)
                page ++

                Log.d(TAG, " Refreshed Items received: $items")

                while (items.isEmpty()) {
                    val reloadedItems = photoRepository.fetchPhotos(page)
                    page ++
                    items = reloadedItems
                    Log.d(TAG, " Reloaded Items received: $items")

                }

                // _galleryItems.value = items
                _galleryItems.value = filterOutNullUrls(items)
            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch Refresh gallery items", ex)
            }
        }
    }


    // Function to filter out GalleryItems with null URLs
    fun  filterOutNullUrls(items: List<GalleryItem>): List<GalleryItem> {
        return items.filter { it.url != null && it.src.medium != null }
            .map { galleryItem ->
                 if (galleryItem.src.medium == null) {
                    // Create a new 'src' object with the 'medium' property updated.
                    // This assumes 'galleryItem.src' is not null and has a 'copy' method (e.g., it's a data class).
                    val updatedSrc = galleryItem.src.copy(medium = "https://via.placeholder.com/150")
                    // Create a new 'galleryItem' with the updated 'src' object.
                    galleryItem.copy(src = updatedSrc)
                } else {
                    galleryItem
                }
            }
    }

    fun setGalleryItems(dummyData: List<GalleryItem>) {

    }
}

class PhotoGalleryViewModelFactory : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>):
            T {
        return PhotoGalleryViewModel() as T
    }
}