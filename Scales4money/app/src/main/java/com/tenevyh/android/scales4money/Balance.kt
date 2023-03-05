package com.tenevyh.android.scales4money

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity
data class Balance (@PrimaryKey val date: Date = Date(),
                    var number: String = "0",
                    var img: Int = R.drawable.ic_launcher_foreground)