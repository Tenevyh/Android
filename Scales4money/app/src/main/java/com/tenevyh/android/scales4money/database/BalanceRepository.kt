package com.tenevyh.android.scales4money.database

import android.content.Context
import androidx.room.Room

private const val DATABASE_NAME = "Balances repository"

class BalanceRepository private constructor(context: Context){
    private val database: RequestBalanceDatabase = Room.databaseBuilder(
        context.applicationContext, RequestBalanceDatabase::class.java, DATABASE_NAME)
        .addMigrations(migration_0_1).build()
}