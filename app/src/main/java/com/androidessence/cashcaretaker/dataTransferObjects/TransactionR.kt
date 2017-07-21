package com.androidessence.cashcaretaker.dataTransferObjects

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcel
import android.os.Parcelable

import com.androidessence.cashcaretaker.core.CoreDTO
import com.androidessence.cashcaretaker.data.CCContract
import com.androidessence.utility.*

import java.util.Date

/**
 * Represents a Transaction entry.
 */
class TransactionR() {

    var identifier: Long = 0
    var account: Long = 0
    var description: String = ""
    var amount: Double = 0.toDouble()
    var notes: String = ""
    var date: Date? = null
    var categoryID: Long = 0
    var isWithdrawal: Boolean = false

    constructor(source: Parcel) : this() {
        account = source.readLong()
        description = source.readString()
        amount = source.readDouble()
        notes = source.readString()
        date = source.readSerializable() as Date
        categoryID = source.readLong()
        isWithdrawal = source.readInt() == 1
    }
}
