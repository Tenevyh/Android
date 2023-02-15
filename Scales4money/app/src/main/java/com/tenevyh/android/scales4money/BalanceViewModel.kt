package com.tenevyh.android.scales4money

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tenevyh.android.scales4money.database.BalanceRepository

class BalanceViewModel: ViewModel() {
    private val balanceRepository = BalanceRepository.get()
    val balanceSheetsLD = balanceRepository.getBalanceSheets()

    fun addBalance(balance: Balance){
        balanceRepository.addRequestBalance(balance)
    }
}