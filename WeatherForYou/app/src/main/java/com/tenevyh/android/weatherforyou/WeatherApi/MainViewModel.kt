package com.tenevyh.android.weatherforyou.WeatherApi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tenevyh.android.weatherforyou.adapters.WeatherModel

class MainViewModel: ViewModel(){
    val liveDataCurrent = MutableLiveData<WeatherModel>()
    val liveDataList = MutableLiveData<List<WeatherModel>>()
}