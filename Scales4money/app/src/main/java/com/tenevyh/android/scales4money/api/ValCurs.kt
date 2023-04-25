package com.tenevyh.android.scales4money.api

import com.google.gson.annotations.SerializedName

data class ValCurs(
    @SerializedName("Valute")
    val valute: Map<String, Valute>
)