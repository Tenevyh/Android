package com.tenevyh.android.scales4money

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "balances")
data class Balance (@PrimaryKey var date: Date = Date(),
                    var number: String = "0",
                    var img: Int = R.drawable.ic_launcher_foreground)