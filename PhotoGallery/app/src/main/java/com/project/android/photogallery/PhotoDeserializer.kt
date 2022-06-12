package com.project.android.photogallery

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.project.android.photogallery.api.PhotoResponse
import java.lang.reflect.Type

class PhotoDeserializer: JsonDeserializer<PhotoResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PhotoResponse {
        TODO("Not yet implemented")
    }
}