package com.bateman.makingnetworkrequestspexel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bateman.makingnetworkrequestspexel.api.GalleryItem
import com.bateman.makingnetworkrequestspexel.databinding.ListItemGalleryBinding


class PhotoViewHolder(
    private val binding: ListItemGalleryBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(galleryItem: GalleryItem, onPhotoClicked: (photoURL:String?, photoTITLE:String?) -> Unit) {

        binding.itemImageView.load(galleryItem.src.tiny)
        binding.root.setOnClickListener {
            onPhotoClicked(galleryItem.src.large, galleryItem.alt)

        }
    }
}


class PhotoListAdapter(
    private val galleryItems: List<GalleryItem>,
    private val onPhotoClicked: (photoURL:String?, photoDESC:String?) -> Unit
) : RecyclerView.Adapter<PhotoViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemGalleryBinding.inflate(inflater, parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = galleryItems[position]
        holder.bind(item, onPhotoClicked)
    }

    override fun getItemCount() = galleryItems.size
}