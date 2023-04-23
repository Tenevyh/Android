package com.tenevyh.android.scales4money.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.tenevyh.android.scales4money.R
import com.tenevyh.android.scales4money.adapter.VpAdapter
import kotlinx.android.synthetic.main.general_fragment.*

class MainFragment: Fragment(R.layout.general_fragment) {

    private val tList = listOf("Баланс", "График","Валюта")
    private val fList = listOf(BalanceFragment.newInstance(),GraphFragment.newInstance(), CurrencyFragment.newInstance())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        val adapter = VpAdapter(activity as FragmentActivity, fList)
        vp.adapter = adapter
        TabLayoutMediator(tabLayout2, vp){
                tab, pos -> tab.text = tList[pos]
        }.attach()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}