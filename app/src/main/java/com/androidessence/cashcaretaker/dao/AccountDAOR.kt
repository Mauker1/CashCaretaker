package com.androidessence.cashcaretaker.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.androidessence.cashcaretaker.dataTransferObjects.AccountR

/**
 * DAO to get account data.
 */
@Dao
interface AccountDAOR {
    @Query("SELECT * FROM accountr")
    fun getAccounts(): List<AccountR>

    @Query("DELETE FROM accountr")
    fun deleteAll(): Int

    @Insert
    fun insert(vararg accounts: AccountR): List<Long>
}