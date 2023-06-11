package com.tenevyh.android.scales4money.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tenevyh.android.scales4money.R
import com.tenevyh.android.scales4money.api.Valute
import com.tenevyh.android.scales4money.viewmodel.BalanceViewModel
import com.tenevyh.android.scales4money.viewmodel.CurrencyViewModel
import kotlinx.android.synthetic.main.currency_fragment.*
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.round


class CurrencyFragment: Fragment(R.layout.currency_fragment) {
    private val currencyViewModel: CurrencyViewModel by activityViewModels()
    private val balanceViewModel: BalanceViewModel by activityViewModels()
    private val dateFormat = SimpleDateFormat("d MMM yyyy")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dateTV.text = dateFormat.format(Date())
        currencyViewModel.currencyList.observe(viewLifecycleOwner, Observer { currencyList ->
            val dollarValue = currencyList?.getValue("USD")
            val euroValue = currencyList?.getValue("EUR")
            val yuanValue = currencyList?.getValue("CNY")

            rateDollarTV.text = dollarValue?.value?.toString()
            rateEuroTV.text = euroValue?.value?.toString()
            rateYuanTV.text = yuanValue?.value?.toString()
            balanceViewModel.balanceSheetsLD.observe(viewLifecycleOwner) { history ->
                val balance = history.last().number.toDouble()

                dollarTV.text = (round(balance / dollarValue!!.value*100)/100).toString()
                eurTV.text = (round(balance / euroValue!!.value*100)/100).toString()
                yuanTV.text = (round(balance / yuanValue!!.value*100)/100).toString()
            }
        })
    }



    companion object {
        @JvmStatic
        fun newInstance() = CurrencyFragment()
    }
}