package com.project.android.photogallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.project.android.photogallery.api.FlickrApi
import com.project.android.photogallery.api.FlickrResponse
import com.project.android.photogallery.api.PhotoResponse
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "FlickrFetchr"

class FlickrFetchr {

    private val flickrApi: FlickrApi

    init {
        val gsonPhotoDeserializer = GsonBuilder()
            .registerTypeAdapter(PhotoResponse::class.java, PhotoDeserializer())
            .create()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(GsonConverterFactory.create(gsonPhotoDeserializer)).build()

        flickrApi = retrofit.create(FlickrApi::class.java)
    }

    fun fetchPhotos(): LiveData<List<GalleryItem>>{
        val responseLiveData: MutableLiveData<List<GalleryItem>> = MutableLiveData()
        val flickrRequest: Call<PhotoDeserializer> = flickrApi.fetchPhotos()


        flickrRequest.enqueue(object : Callback<PhotoDeserializer> {
            override fun onFailure(call: Call<PhotoDeserializer>, t: Throwable){
                Log.e(TAG, "Failed to fetch photos", t)
            }

            override fun onResponse(call: Call<PhotoDeserializer>, response: Response<PhotoDeserializer>) {
                Log.d(TAG, "Response received: ${response.body()}")
                val flickrResponse: PhotoDeserializer? = response.body()
                val photoResponse: PhotoResponse? = flickrResponse?.photos
                var galleryItems: List<GalleryItem> = photoResponse?.galleryItems?: mutableListOf()
                galleryItems = galleryItems.filterNot{
                    it.url.isBlank()
                }
               responseLiveData.value = galleryItems
            }
        })
        return responseLiveData
        }
}