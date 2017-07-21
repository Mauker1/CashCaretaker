package com.androidessence.cashcaretaker.dataTransferObjects

import android.os.Parcel

/**
 * Represents a finance account for the user.
 */
class AccountR(var name: String = "", var balance: Double = 0.0) {
    constructor(parcel: Parcel) : this(parcel.readString(), parcel.readDouble())
}
