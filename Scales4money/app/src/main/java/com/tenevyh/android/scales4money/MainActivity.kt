package com.tenevyh.android.scales4money

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tenevyh.android.scales4money.fragment.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.placeHolder,
            MainFragment.newInstance()).commit()
    }
}