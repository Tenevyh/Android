package com.tenevyh.android.bintest.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.tenevyh.android.bintest.R
import com.tenevyh.android.bintest.adapters.VpAdapter
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private val tList = listOf("Request", "History")
    private val fList = listOf(RequestFragment.newInstance(),HistoryFragment.newInstance())

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