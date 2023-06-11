package com.tenevyh.android.scales4money.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.tenevyh.android.scales4money.Balance
import com.tenevyh.android.scales4money.Limit
import com.tenevyh.android.scales4money.viewmodel.BalanceViewModel
import com.tenevyh.android.scales4money.R
import com.tenevyh.android.scales4money.adapter.BalanceHistoryAdapter
import com.tenevyh.android.scales4money.adapter.VpAdapterTwo
import kotlinx.android.synthetic.main.balance_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat

class BalanceFragment: Fragment(R.layout.balance_fragment) {

    private val dateFormat = SimpleDateFormat("d MMM yyyy, HH:mm")
    private val balanceViewModel: BalanceViewModel by activityViewModels()
    private val tList = listOf("Remainder","Spending")
    private val fList = listOf(RemainderFragment.newInstance(),SpendingFragment.newInstance())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        balanceViewModel.balanceSheetsLD.observe(
            viewLifecycleOwner,
            Observer { history ->
                history?.let {
                    if(history.isNotEmpty()) itemBalance.text = history.last().number+" руб."
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
    }

    private fun init(){
        val adapter = VpAdapterTwo(activity as FragmentActivity, fList)
        vp.adapter = adapter
        TabLayoutMediator(tabLayout2, vp){
                tab, pos -> tab.text = tList[pos]
        }.attach()
    }


    companion object {
        @JvmStatic
        fun newInstance() = BalanceFragment()
    }
}