package com.androidessence.cashcaretaker.dataTransferObjects

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Represents a repeating period for a transaction.
 */
@Entity
data class RepeatingPeriodR(
        @PrimaryKey(autoGenerate = false) var name: String = "")
