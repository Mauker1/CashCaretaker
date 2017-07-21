package com.androidessence.cashcaretaker.dataTransferObjects

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcel

import com.androidessence.cashcaretaker.core.CoreDTO
import com.androidessence.cashcaretaker.data.CCContract
import com.androidessence.utility.*

import java.util.Date

/**
 * Represents a transaction that repeats monthly or yearly.

 * Created by adam.mcneilly on 9/7/16.
 */
class RepeatingTransactionR(){

    var repeatingPeriod: Long = 0
    var account: Long = 0
    var description: String? = null
    var amount: Double = 0.toDouble()
    var notes: String? = null
    var nextDate: Date? = null
    var category: Long = 0
    var isWithdrawal: Boolean = false

    constructor(parcel: Parcel) : this() {
        this.repeatingPeriod = parcel.readLong()
        this.account = parcel.readLong()
        this.description = parcel.readString()
        this.amount = parcel.readDouble()
        this.notes = parcel.readString()
        this.nextDate = parcel.readSerializable() as Date
        this.category = parcel.readLong()
        this.isWithdrawal = parcel.readInt() == 1
    }
}
