package com.androidessence.cashcaretaker.dataTransferObjects

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Represents a category for a transaction.
 */
@Entity
data class CategoryR(
        @PrimaryKey(autoGenerate = true) var id: Long = 0,
        var description: String = "",
        var isDefault: Boolean = false)
