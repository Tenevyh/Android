package com.tenevyh.android.scales4money

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "limits")
data class Limit(@PrimaryKey val date: Date = Date(),
                 var number: String = "0")