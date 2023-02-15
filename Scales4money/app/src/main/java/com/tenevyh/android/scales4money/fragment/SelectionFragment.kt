package com.tenevyh.android.scales4money.fragment

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.tenevyh.android.scales4money.Balance
import com.tenevyh.android.scales4money.BalanceViewModel
import com.tenevyh.android.scales4money.R
import kotlinx.android.synthetic.main.selection_fragment.*
import java.text.SimpleDateFormat
import java.util.*

class SelectionFragment: DialogFragment(R.layout.selection_fragment) {

    private val dateFormat = SimpleDateFormat("d MMM yyyy, HH:mm")
    private val balanceViewModel: BalanceViewModel by activityViewModels()

    override fun onStart() {
        super.onStart()
        bOkey.setOnClickListener {
            if (editBalance.text.isNotEmpty()){
                addBalance()
                onDestroyView()
            }
        }
        bCancel.setOnClickListener {
            onDestroyView()
        }

    }

    fun addBalance(){
        val date = Date()
        val balance = Balance()
        balance.date = dateFormat.format(date).toString()
        balance.number = editBalance.text.toString()
        balanceViewModel.addBalance(balance)
    }
}