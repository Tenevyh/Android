package com.project.android.photogallery

import androidx.lifecycle.LiveData

class PhotoGalleryViewModel {
    val galleryItemLiveData: LiveData<List<GalleryItem>>

    init {

        galleryItemLiveData = FlickrFetchr().fetchPhotos()

        }
}