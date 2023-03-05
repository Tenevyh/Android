package com.tenevyh.android.scales4money

import android.app.Application
import com.tenevyh.android.scales4money.database.balance.BalanceRepository

class ScalesForMoney: Application() {
    override fun onCreate() {
        super.onCreate()
        BalanceRepository.initialize(this)
    }
}