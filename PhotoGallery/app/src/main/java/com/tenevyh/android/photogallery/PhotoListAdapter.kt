package com.tenevyh.android.photogallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tenevyh.android.photogallery.FlickrApi.GalleryItem
import com.tenevyh.android.photogallery.databinding.ListItemGalleryBinding

class PhotoViewHolder(private val binding: ListItemGalleryBinding)
    : RecyclerView.ViewHolder(binding.root){
    fun bind(galleryItem: GalleryItem) = with(binding){
        itemImageView.load(galleryItem.url)
    }
}

class PhotoListAdapter: RecyclerView.Adapter<PhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder()
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}