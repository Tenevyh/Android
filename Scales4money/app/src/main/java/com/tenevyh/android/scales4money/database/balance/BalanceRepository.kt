package com.tenevyh.android.scales4money.database.balance

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.tenevyh.android.scales4money.Balance
import com.tenevyh.android.scales4money.Limit
import java.util.concurrent.Executors

private const val DATABASE_NAME = "Balances repository"

class BalanceRepository private constructor(context: Context){
    private val database: RequestBalanceDatabase = Room.databaseBuilder(
        context.applicationContext, RequestBalanceDatabase::class.java, DATABASE_NAME
    )
        .addMigrations(migration_0_1).build()

    private val balanceDao = database.BalanceDao()
    private val executor = Executors.newSingleThreadExecutor()  //worker?

    fun getBalanceSheets() : LiveData<List<Balance>> = balanceDao.getBalance()

    fun addRequestBalance(balance: Balance){
        executor.execute{
            balanceDao.addBalance(balance)
        }
    }

    fun getLimits() : LiveData<List<Limit>> = balanceDao.getLimit()

    fun addRequestLimit(limit: Limit){
        executor.execute{
            balanceDao.addLimit(limit)
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