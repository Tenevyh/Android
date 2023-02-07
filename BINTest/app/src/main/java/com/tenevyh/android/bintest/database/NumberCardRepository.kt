package com.tenevyh.android.bintest.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.tenevyh.android.bintest.RequestNumber
import java.util.concurrent.Executors

private const val DATABASE_NAME = "NumberCard repository"

class NumberCardRepository private constructor(context: Context){
    private val database: RequestNumberDatabase = Room.databaseBuilder(
        context.applicationContext, RequestNumberDatabase::class.java, DATABASE_NAME)
        .addMigrations(
        migration_1_2).build()

    private val numberCardDao = database.NumberCardDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getRequestsNumbers() : LiveData<List<RequestNumber>> = numberCardDao.getRequestsNumbers()

    fun addRequestNumber(number: RequestNumber){
        executor.execute{
            numberCardDao.addRequestNumber(number)
        }
    }

    companion object{
        private var INSTANCE : NumberCardRepository? =  null

        fun  initialize(context: Context){
            if (INSTANCE == null){
                INSTANCE = NumberCardRepository(context)
            }
        }

        fun get() : NumberCardRepository{
            return INSTANCE ?: throw IllegalStateException("NumberCardRepository must be initialized")
        }
    }
}