package com.tenevyh.android.scales4money.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tenevyh.android.scales4money.Balance

@Database(entities = [Balance::class], version = 1)
@TypeConverters(RequestBalanceConverter::class)
abstract class RequestBalanceDatabase : RoomDatabase(){
    abstract fun BalanceDao(): BalanceDao
}

val migration_0_1 = object : Migration(0, 1){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "AFTER TABLE RequestBalance ADD COLUMN history TEXT NOT NULL DEFAULT ''"
        )
    }

}