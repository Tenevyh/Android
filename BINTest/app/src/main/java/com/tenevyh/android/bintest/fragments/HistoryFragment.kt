package com.tenevyh.android.bintest.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tenevyh.android.bintest.CardNumberListVM
import com.tenevyh.android.bintest.R
import com.tenevyh.android.bintest.RequestNumber
import com.tenevyh.android.bintest.adapters.RequestNumbersAdapter
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment: Fragment(R.layout.fragment_history) {

    private var adapter: RequestNumbersAdapter?= RequestNumbersAdapter(emptyList())

    private val cardNumberListVM : CardNumberListVM by lazy {
        ViewModelProvider(this)[CardNumberListVM::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyRV.layoutManager = LinearLayoutManager(context)
        cardNumberListVM.cardNumberListLiveData.observe(
            viewLifecycleOwner,
            Observer{ history ->
                history?.let {
                    updateUI(history)
                    Log.d("My Log", "$history")
                }
            })
    }

    private fun updateUI(cardNumbers: List<RequestNumber>){
        adapter = RequestNumbersAdapter(cardNumbers)
        historyRV.adapter = adapter
    }


    companion object {
        @JvmStatic
        fun newInstance() = HistoryFragment()
    }
}