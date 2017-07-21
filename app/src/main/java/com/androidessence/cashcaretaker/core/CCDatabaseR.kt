package com.androidessence.cashcaretaker.core

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.androidessence.cashcaretaker.dataTransferObjects.AccountR

/**
 * Database.
 */
@Database(entities = arrayOf(AccountR::class), version = 5)
abstract class CCDatabaseR : RoomDatabase() {
    abstract fun accountDao(): AccountDAOR

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