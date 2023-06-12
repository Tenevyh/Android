package com.tenevyh.android.scales4money

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "spending")
data class Spending  (@PrimaryKey var date: Date = Date(), var number: Int = 0, var preDate: Date)