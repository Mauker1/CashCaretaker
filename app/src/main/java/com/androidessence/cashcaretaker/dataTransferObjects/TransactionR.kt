package com.androidessence.cashcaretaker.dataTransferObjects

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Represents a Transaction entry.
 */
@Entity(foreignKeys = arrayOf(
                ForeignKey(entity = AccountR::class, parentColumns = arrayOf("id"), childColumns = arrayOf("account"), onDelete = ForeignKey.CASCADE),
                ForeignKey(entity = CategoryR::class, parentColumns = arrayOf("id"), childColumns = arrayOf("category"))))
data class TransactionR(
        var account: Long = 0,
        var description: String = "",
        var amount: Double = 0.0,
        var notes: String = "",
        var date: Date? = null,
        var category: Long = 0,
        var isWithdrawal: Boolean = true,
        @PrimaryKey(autoGenerate = true) var id: Long = 0
) {
    override fun equals(other: Any?): Boolean {
        return (other is TransactionR
                && other.account == this.account
                && other.description == this.description
                && other.amount == this.amount
                && other.notes == this.notes
                //TODO: && other.date?.compareTo(this.date) == 0
                && other.category == this.category
                && other.isWithdrawal == this.isWithdrawal
                && other.id == this.id)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}
