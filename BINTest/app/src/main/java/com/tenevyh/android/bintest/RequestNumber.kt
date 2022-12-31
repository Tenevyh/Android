package com.tenevyh.android.bintest

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class RequestNumber (@PrimaryKey val id: UUID = UUID.randomUUID(),
                  var number: String ="",
                  var date: String ="")