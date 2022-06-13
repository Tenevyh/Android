package com.project.android.photogallery

import com.google.gson.*
import com.project.android.photogallery.api.PhotoResponse
import java.lang.reflect.Type

class PhotoDeserializer: JsonDeserializer<PhotoResponse> {

    lateinit var photos: PhotoResponse

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PhotoResponse {
        val jsonObject: JsonObject = json?.asJsonObject!!

        photos = Gson().fromJson(jsonObject, PhotoResponse::class.java)

        return photos
    }
}