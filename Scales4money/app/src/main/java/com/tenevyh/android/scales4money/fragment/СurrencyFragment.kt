package com.tenevyh.android.scales4money.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.tenevyh.android.scales4money.R
import com.tenevyh.android.scales4money.viewmodel.CurrencyViewModel
import kotlinx.android.synthetic.main.currency_fragment.*


class CurrencyFragment: Fragment(R.layout.currency_fragment) {
    private val currencyViewModel: CurrencyViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rateDollarTV.text = currencyViewModel.getDollarNominal().toString()
    }



    companion object {
        @JvmStatic
        fun newInstance() = CurrencyFragment()
    }
}