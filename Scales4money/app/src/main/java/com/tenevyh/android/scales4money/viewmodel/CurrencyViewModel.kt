package com.tenevyh.android.scales4money.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tenevyh.android.scales4money.API.Currency
import com.tenevyh.android.scales4money.API.CurrencyService
import com.tenevyh.android.scales4money.API.ValCurs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CurrencyViewModel: ViewModel() {
    private var _currencyList = MutableLiveData<List<Currency>>()
    val currencyList: LiveData<List<Currency>> = _currencyList

    fun getDollarNominal(): Int? {
        return currencyList.value?.find { it.CharCode == "USD" }?.Nominal
    }

    fun fetchCurrencyList() {
        CurrencyService.api.getCurrencyList().enqueue(object : Callback<ValCurs> {
            override fun onResponse(call: Call<ValCurs>, response: Response<ValCurs>) {
                if (response.isSuccessful) {
                    _currencyList.value = response.body()?.Valute?.values?.toList()
                } else {
                    Log.d("Ошибка", "Ошибка получения списка валют")
                }
            }

            override fun onFailure(call: Call<ValCurs>, t: Throwable) {
                Log.d("Ошибка", "Ошибка получения списка валют", t)
            }
        })
    }
}