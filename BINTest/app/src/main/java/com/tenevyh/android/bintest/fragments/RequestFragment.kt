package com.tenevyh.android.bintest.fragments

import android.content.Intent
import android.net.Uri
import android.util.Log
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
import java.util.*


class RequestFragment: Fragment(R.layout.fragment_request) {

    private val dateFormat = SimpleDateFormat("d MMM yyyy, HH:mm")
    private val cardNumberListVM : CardNumberListVM by lazy {
        ViewModelProvider(this)[CardNumberListVM::class.java]
    }

    override fun onStart() {
        super.onStart()
        bSearch.setOnClickListener {
            val number = numberCardTV.text.toString()
            if(number.length>=8 && number[0] != ' ') {
                addNumber()
                numberRequest(number)
            }
        }
        urlTV.setOnClickListener {
            if (urlTV.text.isNotEmpty()) {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://${urlTV.text}")
                )
                startActivity(browserIntent)
            }
        }
        phoneTV.setOnClickListener {
            if(phoneTV.text.isNotEmpty()){
                val phoneIntent = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:${phoneTV.text}"))
                startActivity(phoneIntent)
            }
        }

        countryNameTV.setOnClickListener {
            if (latitudeTV.text.isNotEmpty()){
                val mapIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("geo:${latitudeTV.text}, ${longitudeTV.text}"))
                startActivity(mapIntent)
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
        schemeTV.text = mainObj.optString("scheme")
        brandTV.text = mainObj.optString("brand")
        lengthTV.text = mainObj.getJSONObject("number").optString("length")
        if(mainObj.getJSONObject("number").optString("luhn").toBoolean()){
            luhnTV.text = "Yes"
        } else {
            luhnTV.text = "No"
        }
        typeTV.text = mainObj.optString("type")
        prepaidTV.text = mainObj.optString("prepaid")
        alpha2TV.text = mainObj.getJSONObject("country").optString("alpha2")
        countryNameTV.text = mainObj.getJSONObject("country").optString("name")
        latitudeTV.text = mainObj.getJSONObject("country").optString("latitude")
        longitudeTV.text = mainObj.getJSONObject("country").optString("longitude")
        bankNameTV.text = mainObj.getJSONObject("bank").optString("name")
        cityTV.text = mainObj.getJSONObject("bank").optString("city")
        urlTV.text = mainObj.getJSONObject("bank").optString("url")
        phoneTV.text = mainObj.getJSONObject("bank").optString("phone")
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