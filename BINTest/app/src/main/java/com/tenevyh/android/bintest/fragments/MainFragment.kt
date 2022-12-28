package com.tenevyh.android.bintest.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.tenevyh.android.bintest.R

class MainFragment : Fragment(R.layout.fragment_main) {

    private val tList = listOf("Запрос", "История")
    private val fList = listOf(RequestFragment.newInstance(),HistoryFragment.newInstance())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}