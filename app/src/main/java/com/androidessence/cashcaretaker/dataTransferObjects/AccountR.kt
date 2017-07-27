package com.androidessence.cashcaretaker.dataTransferObjects

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Represents a finance account for the user.
 */
@Entity
data class AccountR(
        @PrimaryKey(autoGenerate = true) var id: Long = 0,
        var name: String = "",
        var balance: Double = 0.0)
