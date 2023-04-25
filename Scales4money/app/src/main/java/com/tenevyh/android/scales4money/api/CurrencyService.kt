package com.tenevyh.android.scales4money.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CurrencyService {
    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.cbr-xml-daily.ru/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: CbrApi = retrofit.create(CbrApi::class.java)
}