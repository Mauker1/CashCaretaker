package com.androidessence.cashcaretaker.dataTransferObjects

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Represents a finance account for the user.
 */
@Entity(tableName = "accountTable")
class AccountR(
        @PrimaryKey(autoGenerate = false) var name: String = "",
        var balance: Double = 0.0)
