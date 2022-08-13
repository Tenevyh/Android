package com.tenevyh.android.weatherforyou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.tenevyh.android.weatherforyou.databinding.ActivityMainBinding

private const val API_KEY = "f9724f9d686549189fb80258221308"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getCur.setOnClickListener{
            getResult("London")
        }
    }

    private fun getResult(name: String) {
        val url = "https://api.weatherapi.com/v1/search.json?key=$API_KEY&q=$name"
        val queue  = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET,
            url,
            {
                    response -> Log.d("MyLog", "Response: $response")
            },
            {
                Log.d("MyLog", "Volley error: $it")
            }
        )
        queue.add(stringRequest)
    }
}