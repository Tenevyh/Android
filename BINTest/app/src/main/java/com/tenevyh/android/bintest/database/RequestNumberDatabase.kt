package com.tenevyh.android.bintest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tenevyh.android.bintest.RequestNumber

@Database(entities = [RequestNumber::class], version = 2)
@TypeConverters(RequestNumberConverter::class)
abstract class RequestNumberDatabase: RoomDatabase() {
    abstract fun NumberCardDao(): NumberCardDao
}

val migration_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE RequestNumber ADD COLUMN history TEXT NOT NULL DEFAULT ''"

        )
    }
}