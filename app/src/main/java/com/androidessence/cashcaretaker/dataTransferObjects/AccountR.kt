package com.androidessence.cashcaretaker.dataTransferObjects

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Represents a finance account for the user.
 */
@Entity(tableName = "accountTable")
data class AccountR(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id") var id: Long = 0,
        @ColumnInfo(name = "accountName") var name: String = "",
        @ColumnInfo(name = "accountBalance") var balance: Double = 0.0)
