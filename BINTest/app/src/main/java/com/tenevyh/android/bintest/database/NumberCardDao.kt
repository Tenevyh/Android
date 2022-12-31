package com.tenevyh.android.bintest.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tenevyh.android.bintest.RequestNumber

@Dao
interface NumberCardDao {
    @Query("SELECT * FROM requestnumber")
    fun getRequestsNumbers(): LiveData<List<RequestNumber>>

    @Insert
    fun addRequestNumber(requestnumber: RequestNumber)
}