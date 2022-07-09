package com.tenevyh.android.photogallery.FlickrApi

import retrofit2.http.GET

private const val API_KEY = "c7d5609553a4d9560b5b29dfd57e483b"

interface FlickrApi {
    @GET(
        "services/rest/?method=flickr.interestingness.getList"+
                "&api_key=$API_KEY"+
                "&format=json"+
                "&nojsoncallback=1"+
                "&extras=url_s"
    )
    suspend fun fetchPhotos(): FlickrResponse
}