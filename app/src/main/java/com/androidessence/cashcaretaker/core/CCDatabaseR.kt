package com.androidessence.cashcaretaker.core

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.androidessence.cashcaretaker.dataTransferObjects.AccountR
import com.androidessence.cashcaretaker.dataTransferObjects.CategoryR

/**
 * Database.
 */
@Database(entities = arrayOf(AccountR::class, CategoryR::class), version = 6)
abstract class CCDatabaseR : RoomDatabase() {
    abstract fun accountDao(): AccountDAOR
    abstract fun categoryDao(): CategoryDAOR

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