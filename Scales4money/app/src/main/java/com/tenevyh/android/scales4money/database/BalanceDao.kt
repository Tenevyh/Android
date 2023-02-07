package com.tenevyh.android.scales4money.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tenevyh.android.scales4money.Balance

@Dao
interface BalanceDao {
    @Query("SELECT * FROM balance")
    fun getBalance(): LiveData<List<Balance>>

    @Insert
    fun addBalance(balance: Balance)
}