package com.tenevyh.android.scales4money.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tenevyh.android.scales4money.api.Valute
import com.tenevyh.android.scales4money.api.CurrencyService
import com.tenevyh.android.scales4money.api.ValCurs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Date


class CurrencyViewModel : ViewModel() {

    private val _currencyList = MutableLiveData<Map<String, Valute>?>()
    val currencyList: LiveData<Map<String, Valute>?> = _currencyList

    private var _date = Date()
    val date = _date

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        fetchCurrencyList()
    }

    private fun fetchCurrencyList() {
        _isLoading.value = true
        CurrencyService.api.getCurrencyList().enqueue(object : Callback<ValCurs> {
            override fun onResponse(call: Call<ValCurs>, response: Response<ValCurs>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val currencyMap = response.body()?.valute
                    _date = response.body()!!.date
                    if (!currencyMap.isNullOrEmpty()) {
                        val currencyList = currencyMap.values.toList()
                        _currencyList.value = currencyMap
                    } else {
                        _errorMessage.value = "Список валют пуст"
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    _errorMessage.value = "Ошибка при получении данных: ${response.code()} ${errorBody ?: ""}"
                }
            }

            override fun onFailure(call: Call<ValCurs>, t: Throwable) {
                _isLoading.value = false
                _errorMessage.value = "Ошибка при получении данных: ${t.message}"
            }
        })
    }

    fun refreshCurrencyList() {
        fetchCurrencyList()
    }
}