package com.tenevyh.android.scales4money.fragment

import androidx.fragment.app.Fragment
import com.tenevyh.android.scales4money.R
import kotlinx.android.synthetic.main.balance_fragment.*

class BalanceFragment: Fragment(R.layout.balance_fragment) {

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