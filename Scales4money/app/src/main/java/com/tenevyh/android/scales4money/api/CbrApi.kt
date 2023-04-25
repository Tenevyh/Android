package com.tenevyh.android.scales4money.api

import retrofit2.Call
import retrofit2.http.GET


interface CbrApi {
    @GET("daily_json.js")
    fun getCurrencyList(): Call<ValCurs>
}