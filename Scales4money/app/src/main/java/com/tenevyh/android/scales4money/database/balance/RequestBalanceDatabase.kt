package com.tenevyh.android.scales4money.database.balance

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tenevyh.android.scales4money.Balance
import com.tenevyh.android.scales4money.Limit

@Database(entities = [Balance::class, Limit::class], version = 1)
@TypeConverters(RequestBalanceConverter::class)
abstract class RequestBalanceDatabase : RoomDatabase(){
    abstract fun balanceDao(): BalanceDao
    abstract fun limitDao(): LimitDao
}