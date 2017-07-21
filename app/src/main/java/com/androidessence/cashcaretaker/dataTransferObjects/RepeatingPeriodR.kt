package com.androidessence.cashcaretaker.dataTransferObjects

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcel

import com.androidessence.cashcaretaker.core.CoreDTO
import com.androidessence.cashcaretaker.data.CCContract

/**
 * Represents a repeating period for a transaction.
 */
class RepeatingPeriodR(var name: String = "") {
    constructor(parcel: Parcel) : this(parcel.readString())
}
