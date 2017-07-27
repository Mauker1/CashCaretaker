package com.androidessence.cashcaretaker.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.androidessence.cashcaretaker.Converters
import com.androidessence.cashcaretaker.dao.AccountDAOR
import com.androidessence.cashcaretaker.dao.CategoryDAOR
import com.androidessence.cashcaretaker.dao.RepeatingPeriodDAOR
import com.androidessence.cashcaretaker.dao.TransactionDAOR
import com.androidessence.cashcaretaker.dataTransferObjects.AccountR
import com.androidessence.cashcaretaker.dataTransferObjects.CategoryR
import com.androidessence.cashcaretaker.dataTransferObjects.RepeatingPeriodR
import com.androidessence.cashcaretaker.dataTransferObjects.TransactionR

/**
 * Database.
 */
@Database(entities = arrayOf(AccountR::class, CategoryR::class, RepeatingPeriodR::class, TransactionR::class), version = 5)
@TypeConverters(Converters::class)
abstract class CCDatabaseR : RoomDatabase() {
    abstract fun accountDao(): AccountDAOR
    abstract fun categoryDao(): CategoryDAOR
    abstract fun repeatingPeriodDao(): RepeatingPeriodDAOR
    abstract fun transactionDao(): TransactionDAOR

    companion object {
        private var INSTANCE: CCDatabaseR? = null
            private set

        fun getInMemoryDatabase(context: Context): CCDatabaseR {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context,
                        CCDatabaseR::class.java, "cashcaretaker.db")
                        .build()
            }

            return INSTANCE!!
        }
    }
}