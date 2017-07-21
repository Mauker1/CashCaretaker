package com.androidessence.cashcaretaker.core

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.androidessence.cashcaretaker.dataTransferObjects.AccountR

/**
 * DAO to get account data.
 */
@Dao
interface AccountDAOR {
    @Query("SELECT * FROM accountTable")
    fun getAccounts(): List<AccountR>
}