package com.tenevyh.android.scales4money.database.balance

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tenevyh.android.scales4money.Balance
import com.tenevyh.android.scales4money.Spending

@Dao
interface SpendingDao {
    @Query("SELECT * FROM spending")
    fun getSpending(): LiveData<List<Spending>>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun addSpending(spending: Spending)

    @Delete
    fun deleteSpending(spending: Spending)
}