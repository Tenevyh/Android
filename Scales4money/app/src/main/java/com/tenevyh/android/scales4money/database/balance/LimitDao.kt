package com.tenevyh.android.scales4money.database.balance

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tenevyh.android.scales4money.Limit

@Dao
interface LimitDao {
    @Query("SELECT * FROM limits")
    fun getLimit(): LiveData<List<Limit>>

    @Insert
    fun addLimit(limit: Limit)
}