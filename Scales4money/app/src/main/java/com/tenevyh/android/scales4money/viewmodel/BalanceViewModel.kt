package com.tenevyh.android.scales4money.viewmodel

import androidx.lifecycle.ViewModel
import com.tenevyh.android.scales4money.Balance
import com.tenevyh.android.scales4money.Limit
import com.tenevyh.android.scales4money.database.balance.BalanceRepository

class BalanceViewModel: ViewModel() {
    private val balanceRepository = BalanceRepository.get()
    val balanceSheetsLD = balanceRepository.getBalanceSheets()
    val limitsLD = balanceRepository.getLimits()

    fun addBalance(balance: Balance){
        balanceRepository.addRequestBalance(balance)
    }

    fun addLimit(limit: Limit){
        balanceRepository.addRequestLimit(limit)
    }

    fun deleteBalance(balance: Balance){
        balanceRepository.deleteBalance(balance)
    }
}