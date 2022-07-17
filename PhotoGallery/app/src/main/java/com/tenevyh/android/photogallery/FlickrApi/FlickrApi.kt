package com.tenevyh.android.photogallery.FlickrApi

import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "c7d5609553a4d9560b5b29dfd57e483b"

interface FlickrApi {
   @GET("services/rest/?method=flickr.interestingness.getList")
    suspend fun fetchPhotos(): FlickrResponse

    @GET("services/rest?method=flickr.photos.search")
    suspend fun searchPhotos(@Query("text") query: String): FlickrResponse
}