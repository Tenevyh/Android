package com.tenevyh.android.scales4money.database.balance

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.tenevyh.android.scales4money.Balance
import com.tenevyh.android.scales4money.Limit
import com.tenevyh.android.scales4money.Spending
import java.util.concurrent.Executors

private const val DATABASE_NAME = "Balances repository"

class BalanceRepository private constructor(context: Context){
    private val database: RequestBalanceDatabase = Room.databaseBuilder(
        context.applicationContext, RequestBalanceDatabase::class.java, DATABASE_NAME
    ).addMigrations(MIGRATION_1_2).build()

    private val balanceDao = database.balanceDao()
    private val limitDao = database.limitDao()
    private val spendingDao = database.spendingDao()
    private val executor = Executors.newSingleThreadExecutor()  //worker?

    fun getBalanceSheets() : LiveData<List<Balance>> = balanceDao.getBalance()

    fun addRequestBalance(balance: Balance){
        executor.execute{
            balanceDao.addBalance(balance)
        }
    }

    fun deleteBalance(balance: Balance){
        executor.execute {
            balanceDao.deleteBalance(balance)
        }
    }

    fun getLimits() : LiveData<List<Limit>> = limitDao.getLimit()

    fun addRequestLimit(limit: Limit){
        executor.execute{
            limitDao.addLimit(limit)
        }
    }

    fun getSpending(): LiveData<List<Spending>> = spendingDao.getSpending()

    fun addRequestSpending(spending: Spending){
        executor.execute {
            spendingDao.addSpending(spending)
        }
    }

    fun deleteSpending(spending: Spending){
        executor.execute {
            spendingDao.deleteSpending(spending)
        }
    }

    companion object{
        private var INSTANCE : BalanceRepository? = null

        fun initialize(context: Context){
            if(INSTANCE == null){
                INSTANCE = BalanceRepository(context)
            }
        }

        fun get(): BalanceRepository {
            return INSTANCE ?: throw java.lang.IllegalStateException(
                "BalanceRepository must be initialized")
        }
    }
}