package com.tenevyh.android.scales4money.api

import com.google.gson.annotations.SerializedName
import java.util.*

data class ValCurs(
    @SerializedName("Valute")
    val valute: Map<String, Valute>,
    @SerializedName("Date")
    val date: Date
)