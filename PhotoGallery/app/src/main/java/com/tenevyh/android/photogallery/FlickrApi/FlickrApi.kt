package com.tenevyh.android.photogallery.FlickrApi

import retrofit2.http.GET

interface FlickrApi {
    @GET("/")
    suspend fun fetchContents(): String
}