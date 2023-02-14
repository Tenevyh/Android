package com.tenevyh.android.scales4money.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.tenevyh.android.scales4money.BalanceViewModel
import com.tenevyh.android.scales4money.R
import kotlinx.android.synthetic.main.balance_fragment.*

class BalanceFragment: Fragment(R.layout.balance_fragment) {

    private val balanceViewModel: BalanceViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        balanceViewModel.currentBalance.observe(viewLifecycleOwner){
            itemBalance.text=it
        }
    }

    override fun onStart() {
        super.onStart()
        buttonAdd.setOnClickListener {
            val fragment = SelectionFragment().show(parentFragmentManager, "Selection fragment")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = BalanceFragment()
    }
}