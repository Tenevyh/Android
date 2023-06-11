package com.tenevyh.android.scales4money.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.tenevyh.android.scales4money.Balance
import com.tenevyh.android.scales4money.Limit
import com.tenevyh.android.scales4money.Spending
import com.tenevyh.android.scales4money.database.balance.BalanceRepository
import java.text.SimpleDateFormat

class BalanceViewModel : ViewModel() {
    private val balanceRepository = BalanceRepository.get()
    val balanceSheetsLD = balanceRepository.getBalanceSheets()
    val limitsLD = balanceRepository.getLimits()
    val spendingSheetsLD = balanceRepository.getSpending()
    private val dateFormat = SimpleDateFormat("dMMyyyy")

    fun addBalance(balance: Balance) {
        balanceRepository.addRequestBalance(balance)
    }

    fun addLimit(limit: Limit) {
        balanceRepository.addRequestLimit(limit)
    }

    fun deleteBalance(balance: Balance) {
        balanceRepository.deleteBalance(balance)
    }

    private fun addSpending(spending: Spending) {
        balanceRepository.addRequestSpending(spending)
    }

    fun updateSpending(balanceList: List<Balance>?, spendingList: List<Spending>) {
        Log.d("Spending", "spending: ${spendingSheetsLD.value}")
        Log.d("Spending", "balances: ${balanceSheetsLD.value}")
        if (spendingList.isNotEmpty() && !balanceList.isNullOrEmpty()) {
            if (balanceList.size >= 2) {
                val lastSpendingDate = spendingList.last().date
            for (i in 0 until balanceList.size - 1) {
                val currentBalance = balanceList[i]
                val nextBalance = balanceList[i + 1]
                Log.d("Spending", "${dateFormat.format(nextBalance.date).reversed()}  > ${dateFormat.format(lastSpendingDate).reversed()}")
                if (dateFormat.format(nextBalance.date).reversed()
                        .toInt() > dateFormat.format(lastSpendingDate).reversed().toInt()
                ) {
                    Log.d("Spending", "${balanceList.size} add new resp")
                    val spending = Spending(
                        date = nextBalance.date,
                        number = nextBalance.number.toInt() - currentBalance.number.toInt(),
                        preDate = currentBalance.date
                    )
                    addSpending(spending)
                    Log.d("Spending", "add new spending: $spending")
                    Log.d("Spending", "spending: ${spendingSheetsLD.value}")
                }
            }
            }
        } else if (spendingList.isEmpty() && !balanceList.isNullOrEmpty()) {
            Log.d("Spending", "${balanceList.size} new list")
            if (balanceList.size >= 2) {
                for (i in 0 until balanceList.size - 1) {
                    val spending = Spending(
                        date = balanceList[i + 1].date,
                        number = balanceList[i + 1].number.toInt() - balanceList[i].number.toInt(),
                        preDate = balanceList[i].date
                    )
                    Log.d("Spending", "new List: $spending")
                    addSpending(spending)
                }
            }
        } else {
            Log.d("Spending", "Что то не так")

        }
    }


    suspend fun checkSpendingList(balanceList: List<Balance>, spendingList: List<Spending>) {
        
    }
}