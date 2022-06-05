package com.project.android.photogallery.api

import com.google.gson.annotations.SerializedName
import com.project.android.photogallery.GalleryItem

class PhotoResponse {
    @SerializedName("photo")
    lateinit var galleryItems: List<GalleryItem>
}