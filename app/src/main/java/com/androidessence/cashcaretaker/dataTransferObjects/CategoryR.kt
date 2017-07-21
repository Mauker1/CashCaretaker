package com.androidessence.cashcaretaker.dataTransferObjects

import android.os.Parcel
import com.androidessence.utility.asBoolean

/**
 * Represents a category for a transaction.
 */
class CategoryR(var description: String = "", var isDefault: Boolean = false) {
    constructor(parcel: Parcel) : this(parcel.readString(), parcel.readInt().asBoolean())
}
