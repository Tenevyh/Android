package com.tenevyh.android.bintest

import android.app.Application
import com.tenevyh.android.bintest.database.NumberCardRepository

class BINTestApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        NumberCardRepository.initialize(this)
    }
}