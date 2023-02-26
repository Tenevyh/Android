package com.tenevyh.android.scales4money.database.balance

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tenevyh.android.scales4money.Balance
import com.tenevyh.android.scales4money.Limit

@Dao
interface BalanceDao {

    @Query("SELECT * FROM balance")
    fun getBalance(): LiveData<List<Balance>>

    @Insert
    fun addBalance(balance: Balance)

    @Query("SELECT * FROM limit")
    fun getLimit(): LiveData<List<Limit>>

    @Insert
    fun addLimit(limit: Limit)
}