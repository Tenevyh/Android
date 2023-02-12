package com.tenevyh.android.scales4money

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BalanceViewModel: ViewModel() {
    var currentBalance = MutableLiveData<String>()

    /*fun getCurBalance(): String{
        return currentBalance.toString()
    }

    fun setCurBalance(balance: MutableLiveData<String>){
        currentBalance = balance
    }
     */
}