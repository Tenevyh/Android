package com.tenevyh.android.scales4money.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.tenevyh.android.scales4money.Balance
import com.tenevyh.android.scales4money.viewmodel.BalanceViewModel
import com.tenevyh.android.scales4money.R
import kotlinx.android.synthetic.main.selection_fragment.*
import java.text.SimpleDateFormat
import java.util.*

private const val DIALOG_DATE = "DialogDate"
private const val REQUEST_DATE = 0

class SelectionFragment: DialogFragment(R.layout.selection_fragment), DatePickerFragment.Callbacks {

    private lateinit var balance: Balance

    private val balanceViewModel: BalanceViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        balance = Balance()
    }


    override fun onStart() {
        super.onStart()
        bOkey.setOnClickListener {
            if (editBalance.text.isNotEmpty()){
                updateBalance(addBalance())
                onDestroyView()
            }
        }
        bCancel.setOnClickListener {
            onDestroyView()
        }
        btDate.setOnClickListener {
            DatePickerFragment.newInstance(balance.date).apply {
                setTargetFragment(this@SelectionFragment, REQUEST_DATE)
                show(this@SelectionFragment.parentFragmentManager, DIALOG_DATE)
            }
        }
    }

    private fun addBalance(): Balance{
        balance.number = editBalance.text.toString()
        balanceViewModel.addBalance(balance)
        return balance
    }

    private fun updateBalance(balance: Balance){
        val lastBalance =
            if (balanceViewModel.balanceSheetsLD.value?.isNotEmpty() == true)
                balanceViewModel.balanceSheetsLD.value!!.last().number.toInt() else 0
        if(balance.number.toInt()<lastBalance){
            balance.img = R.drawable.down_balance
        } else {
            balance.img = R.drawable.up_balance
        }
    }

    override fun onDateSelected(date: Date) {
        balance.date = date
    }
}