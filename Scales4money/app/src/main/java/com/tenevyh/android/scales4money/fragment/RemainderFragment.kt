package com.tenevyh.android.scales4money.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tenevyh.android.scales4money.Balance
import com.tenevyh.android.scales4money.R
import com.tenevyh.android.scales4money.adapter.BalanceHistoryAdapter
import com.tenevyh.android.scales4money.viewmodel.BalanceViewModel
import kotlinx.android.synthetic.main.remainder_fragment.historyRV


class RemainderFragment: Fragment(R.layout.remainder_fragment) {
    private var adapter: BalanceHistoryAdapter? = BalanceHistoryAdapter(emptyList())
    private val balanceViewModel: BalanceViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyRV.layoutManager = LinearLayoutManager(context)
        balanceViewModel.balanceSheetsLD.observe(
            viewLifecycleOwner,
            Observer { history ->
                history?.let {
                    updateUI(history)
                }
            }
        )
    }

    override fun onStart() {
        super.onStart()
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.ACTION_STATE_IDLE,
            ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                balanceViewModel.deleteBalance(adapter!!.balanceSheets!![position])
            }
        })

        itemTouchHelper.attachToRecyclerView(historyRV)
    }


    private fun updateUI(balanceSheets: List<Balance>){
        adapter = BalanceHistoryAdapter(balanceSheets)
        historyRV.adapter = adapter
        if(historyRV.adapter?.itemCount!! > 0) {
            historyRV.smoothScrollToPosition(historyRV.adapter?.itemCount!! - 1)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = RemainderFragment()
    }
}