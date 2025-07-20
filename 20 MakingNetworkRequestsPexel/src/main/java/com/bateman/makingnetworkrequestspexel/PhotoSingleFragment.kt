package com.bateman.makingnetworkrequestspexel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.bateman.makingnetworkrequestspexel.api.GalleryItem
import com.bateman.makingnetworkrequestspexel.databinding.FragmentPhotoSingleBinding

class PhotoSingleFragment: Fragment() {

    private lateinit var photo: GalleryItem
    private var _binding: FragmentPhotoSingleBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visisble"

        }
    private val viewModel: PhotoGalleryViewModel by viewModels()

     override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoSingleBinding.inflate(
            layoutInflater,
            container,
            false
        )

        val imageUrl = arguments?.getString("PhotoURL")
        val explanation = arguments?.getString("PhotoTITLE")

        imageUrl?.let {
            binding.photosingleImageview.load(it) {
                // placeholder(R.drawable.placeholder_image) // You can set a placeholder image
                // error(R.drawable.error_image) // You can set an error image
            }
        }

        binding.titleTextView.text = explanation

        return binding.root
    }

}