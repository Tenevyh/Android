package com.tenevyh.android.bintest.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.tenevyh.android.bintest.CardNumberListVM
import com.tenevyh.android.bintest.R
import com.tenevyh.android.bintest.RequestNumber
import kotlinx.android.synthetic.main.fragment_request.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date

class RequestFragment: Fragment(R.layout.fragment_request) {

    private val dateFormat = SimpleDateFormat("d MMM yyyy, HH:mm")
    private val cardNumberListVM : CardNumberListVM by lazy {
        ViewModelProvider(this)[CardNumberListVM::class.java]
    }

    override fun onStart() {
        super.onStart()
        bSearch.setOnClickListener {
            val number = numberCardTV.text.toString()
            if(number.length==8 && number[0] != ' ') {
                addNumber()
                numberRequest(number)
            }
        }
    }

    private fun numberRequest(number: String){
        val url = "https://lookup.binlist.net/$number"
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(Request.Method.GET, url,
            { result -> parseRequest(result)},
            { error -> Log.d("MyLog", "Error: $error")}
        )
        queue.add(request)
    }

    private fun parseRequest(result: String){
        val mainObj = JSONObject(result)
        updateInfoCard(mainObj)
    }

    private fun updateInfoCard(mainObj: JSONObject){
        schemeTV.text = mainObj.getString("scheme")
        brandTV.text = mainObj.getString("brand")
        lengthTV.text = mainObj.getJSONObject("number").getString("length")
        if(mainObj.getJSONObject("number").getString("luhn").toBoolean()){
            luhnTV.text = "Yes"
        } else {
            luhnTV.text = "No"
        }
        typeTV.text = mainObj.getString("type")
        prepaidTV.text = mainObj.getString("prepaid")
        alpha2TV.text = mainObj.getJSONObject("country").getString("alpha2")
        countryNameTV.text = mainObj.getJSONObject("country").getString("name")
        latitudeTV.text = mainObj.getJSONObject("country").getString("latitude")
        longitudeTV.text = mainObj.getJSONObject("country").getString("longitude")
        bankNameTV.text = mainObj.getJSONObject("bank").getString("name")
        cityTV.text = mainObj.getJSONObject("bank").getString("city")
        urlTV.text = mainObj.getJSONObject("bank").getString("url")
        phoneTV.text = mainObj.getJSONObject("bank").getString("phone")
    }

    private fun addNumber(){
        val date = Date()
        val number = RequestNumber()
        number.number = numberCardTV.text.toString()
        number.date = dateFormat.format(date).toString()
        cardNumberListVM.addNumber(number)
    }

    companion object {
        @JvmStatic
        fun newInstance() = RequestFragment()
    }
}