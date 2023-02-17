package com.tenevyh.android.scales4money

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Balance (@PrimaryKey val id: UUID = UUID.randomUUID(),
                    var number: String = "0",
                    var date: String = "",
                    var img: Int = R.drawable.ic_launcher_foreground)