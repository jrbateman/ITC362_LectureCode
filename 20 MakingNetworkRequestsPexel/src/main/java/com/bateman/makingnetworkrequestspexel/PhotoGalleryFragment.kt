package com.bateman.makingnetworkrequestspexel

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bateman.makingnetworkrequestspexel.databinding.FragmentPhotoGalleryBinding
import com.bateman.makingnetworkrequestspexel.PhotoGalleryFragmentDirections
import kotlinx.coroutines.launch

private const val TAG = "PhotoGalleryFragment"

class PhotoGalleryFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private var _binding: FragmentPhotoGalleryBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentPhotoGalleryBinding.inflate(
                inflater,
                container,
                false
            )
        binding.photoGrid.layoutManager = GridLayoutManager(context, 3)
        return binding.root
    }

    private val photoGalleryViewModel: PhotoGalleryViewModel by viewModels{
        PhotoGalleryViewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)

        progressBar = view.findViewById(R.id.progressBar)
        progressBar.visibility = View.GONE


        binding.swipeRefreshLayout.setOnRefreshListener {

            progressBar.visibility = View.VISIBLE
            photoGalleryViewModel.getStuff(progressBar)
            swipeRefreshLayout.isRefreshing = false
        }



        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                photoGalleryViewModel.galleryItems.collect() { items ->

                    Log.d(TAG, "Response received:: $items")
                    binding.photoGrid.adapter = PhotoListAdapter(items) { photoURL, photoDESC ->
                        findNavController().navigate(
                            PhotoGalleryFragmentDirections.showPhotoSingle(photoURL, photoDESC)
                        )
                    }
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}