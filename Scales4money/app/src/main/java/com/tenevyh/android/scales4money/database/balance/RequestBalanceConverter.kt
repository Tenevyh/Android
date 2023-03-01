package com.tenevyh.android.scales4money.database.balance

import androidx.room.TypeConverter
import java.util.*

class RequestBalanceConverter {
    @TypeConverter
    fun fromDate(date: Date?): Long?{
        return date?.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long): Date? {
        return millisSinceEpoch?.let { Date(it) }
    }
}