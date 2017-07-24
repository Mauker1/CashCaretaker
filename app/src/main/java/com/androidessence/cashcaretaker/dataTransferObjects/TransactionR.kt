package com.androidessence.cashcaretaker.dataTransferObjects

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import android.content.ContentValues
import android.database.Cursor
import android.os.Parcel
import android.os.Parcelable
import android.text.style.LineHeightSpan

import com.androidessence.cashcaretaker.core.CoreDTO
import com.androidessence.cashcaretaker.data.CCContract
import com.androidessence.utility.*

import java.util.Date

/**
 * Represents a Transaction entry.
 */
@Entity(tableName = "transactionTable",
        foreignKeys = arrayOf(
                ForeignKey(entity = AccountR::class, parentColumns = arrayOf("_id"), childColumns = arrayOf("transactionAccount"), onDelete = ForeignKey.CASCADE),
                ForeignKey(entity = CategoryR::class, parentColumns = arrayOf("_id"), childColumns = arrayOf("transactionCategory"))))
data class TransactionR(
        @ColumnInfo(name = "transactionAccount") var account: Long = 0,
        @ColumnInfo(name = "transactionDescription") var description: String = "",
        @ColumnInfo(name = "transactionAmount") var amount: Double = 0.0,
        @ColumnInfo(name = "transactionNotes") var notes: String = "",
        @ColumnInfo(name = "transactionDate") var date: Date? = null,
        @ColumnInfo(name = "transactionCategory") var category: Long = 0,
        @ColumnInfo(name = "transactionWithdrawal") var isWithdrawal: Boolean = true,
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id") var id: Long = 0
)
