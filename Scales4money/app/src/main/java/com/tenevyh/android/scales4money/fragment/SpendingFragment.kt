package com.tenevyh.android.scales4money.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tenevyh.android.scales4money.R
import com.tenevyh.android.scales4money.Spending
import com.tenevyh.android.scales4money.adapter.SpendingHistoryAdapter
import com.tenevyh.android.scales4money.viewmodel.BalanceViewModel
import kotlinx.android.synthetic.main.spending_fragment.spendingHistoryRV


class SpendingFragment : Fragment(R.layout.spending_fragment) {

    private var adapter: SpendingHistoryAdapter? = SpendingHistoryAdapter(emptyList())
    private val balanceViewModel: BalanceViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spendingHistoryRV.layoutManager = LinearLayoutManager(context)
        balanceViewModel.balanceSheetsLD.observe(
            viewLifecycleOwner,
            Observer { balances ->
        balanceViewModel.spendingSheetsLD.observe(
            viewLifecycleOwner,
            Observer { history ->
                history?.let {
                    updateUI(history)
                    balanceViewModel.updateSpending(balances, history)
                }
            })
            }
        )
    }

    private fun updateUI(spendingSheets: List<Spending>) {
        adapter = SpendingHistoryAdapter(spendingSheets)
        spendingHistoryRV.adapter = adapter
        if (spendingHistoryRV.adapter?.itemCount!! > 0) {
            spendingHistoryRV.smoothScrollToPosition(
                spendingHistoryRV.adapter?.itemCount!! - 1
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SpendingFragment()
    }
}