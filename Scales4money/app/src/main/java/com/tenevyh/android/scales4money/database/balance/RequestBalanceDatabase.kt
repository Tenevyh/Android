package com.tenevyh.android.scales4money.database.balance

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tenevyh.android.scales4money.Balance
import com.tenevyh.android.scales4money.Limit
import com.tenevyh.android.scales4money.Spending

@Database(entities = [Balance::class, Limit::class, Spending::class], version = 2)
@TypeConverters(RequestBalanceConverter::class)
abstract class RequestBalanceDatabase : RoomDatabase(){
    abstract fun balanceDao(): BalanceDao
    abstract fun limitDao(): LimitDao
    abstract fun spendingDao(): SpendingDao
}

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // SQL-запрос для создания таблицы `spending`
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS `spending` " +
                    "(`date` TEXT PRIMARY KEY NOT NULL, " +
                    "`number` INTEGER NOT NULL, " +
                    "`preDate` INTEGER NOT NULL)"
        )
    }
}