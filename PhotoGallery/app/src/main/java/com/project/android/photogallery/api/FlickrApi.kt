package com.project.android.photogallery.api

import com.project.android.photogallery.PhotoDeserializer
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface FlickrApi {

    @GET(

        "services/rest/?method=flickr.interestingness.getList" +

    "&api_key=c7d5609553a4d9560b5b29dfd57e483b" +

    "&format=json" +

    "&nojsoncallback=1" +

    "&extras=url_s"

    )

   fun fetchPhotos(): Call<PhotoDeserializer>

   @GET
   fun fetchUrlBytes(@Url url: String): Call<ResponseBody>

}