package com.tenevyh.android.scales4money.fragment

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.tenevyh.android.scales4money.BalanceViewModel
import com.tenevyh.android.scales4money.R
import kotlinx.android.synthetic.main.selection_fragment.*

class SelectionFragment: DialogFragment(R.layout.selection_fragment) {

    private val balanceViewModel: BalanceViewModel by activityViewModels()

    override fun onStart() {
        super.onStart()
        editBalance.setOnClickListener{
            balanceViewModel.currentBalance.value = editBalance.text.toString()
        }
    }
}