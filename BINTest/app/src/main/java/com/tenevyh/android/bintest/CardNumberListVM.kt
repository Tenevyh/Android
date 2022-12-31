package com.tenevyh.android.bintest

import androidx.lifecycle.ViewModel
import com.tenevyh.android.bintest.database.NumberCardRepository

class CardNumberListVM : ViewModel(){

    private val numberCardRepository = NumberCardRepository.get()
    val cardNumberListLiveData = numberCardRepository.getRequestsNumbers()

    fun addNumber(number: RequestNumber) {
       numberCardRepository.addRequestNumber(number)
    }
}