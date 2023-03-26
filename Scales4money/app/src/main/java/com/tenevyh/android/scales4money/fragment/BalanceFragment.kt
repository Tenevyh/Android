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
import com.tenevyh.android.scales4money.Limit
import com.tenevyh.android.scales4money.viewmodel.BalanceViewModel
import com.tenevyh.android.scales4money.R
import com.tenevyh.android.scales4money.adapter.BalanceHistoryAdapter
import kotlinx.android.synthetic.main.balance_fragment.*
import java.text.SimpleDateFormat

class BalanceFragment: Fragment(R.layout.balance_fragment) {

    private val dateFormat = SimpleDateFormat("d MMM yyyy, HH:mm")
    private var adapter: BalanceHistoryAdapter? = BalanceHistoryAdapter(emptyList())
    private val balanceViewModel: BalanceViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyRV.layoutManager = LinearLayoutManager(context)
        balanceViewModel.balanceSheetsLD.observe(
            viewLifecycleOwner,
            Observer { history ->
                history?.let {
                    if(history.isNotEmpty()) itemBalance.text = history.last().number+" руб."
                    updateUI(history)
                }
            }
        )
        balanceViewModel.limitsLD.observe(viewLifecycleOwner,
        Observer { lastLimits ->
            lastLimits?.let {
                if (lastLimits.isNotEmpty()) editLimit.hint = "${lastLimits.last().number} на " +
                        dateFormat.format(lastLimits.last().date)
            }
        })
    }

    override fun onStart() {
        super.onStart()
        buttonAdd.setOnClickListener {
            SelectionFragment().show(parentFragmentManager, "Selection fragment")
        }
        limitSaveButton.setOnClickListener {
            if (editLimit.text.isNotEmpty()) {
                val limit = Limit()
                limit.number = editLimit.text.toString()
                balanceViewModel.addLimit(limit)
            }
            editLimit.isFocusable = false
            editLimit.text.clear()
        }

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.ACTION_STATE_IDLE,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
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
                balanceViewModel.deleteBalance(adapter!!.balanceSheets!!.get(position)!! )
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
        fun newInstance() = BalanceFragment()
    }
}