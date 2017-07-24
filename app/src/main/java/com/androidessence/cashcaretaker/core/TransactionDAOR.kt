package com.androidessence.cashcaretaker.core

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.androidessence.cashcaretaker.dataTransferObjects.TransactionR

/**
 * DAO to get transaction data.
 */
@Dao
interface TransactionDAOR {
    @Query("SELECT * FROM transactionTable")
    fun getTransactions(): List<TransactionR>

    @Query("SELECT * FROM transactionTable WHERE transactionAccount = :arg0")
    fun getTransactionsForAccount(account: Long): List<TransactionR>

    @Query("DELETE FROM transactionTable")
    fun deleteAll(): Int

    @Insert
    fun insert(vararg transactions: TransactionR): List<Long>
}