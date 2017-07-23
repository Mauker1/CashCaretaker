package com.androidessence.cashcaretaker.dataTransferObjects

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.content.ContentValues
import android.database.Cursor
import android.os.Parcel

import com.androidessence.cashcaretaker.core.CoreDTO
import com.androidessence.cashcaretaker.data.CCContract

/**
 * Represents a repeating period for a transaction.
 */
@Entity(tableName = "repeatingPeriodTable")
data class RepeatingPeriodR(
        @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "repeatingPeriodName") var name: String = "")
