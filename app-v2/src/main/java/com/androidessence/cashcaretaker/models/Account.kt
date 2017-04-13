package com.androidessence.cashcaretaker.models

import android.content.ContentValues
import android.os.Parcel
import com.androidessence.cashcaretaker.data.CCContract
import com.androidessence.utility.creator

/**
 * Represents a bank account the user has.
 *
 * Created by adam.mcneilly on 3/5/17.
 */
open class Account: BaseModel {
    var id: Long = 0.toLong()
    var name: String = ""
    var balance: Double = 0.toDouble()

    override val contentValues: ContentValues
        get() {
            val values = super.contentValues
            if (id != 0L) values.put(CCContract.AccountEntry._ID, id)
            values.put(CCContract.AccountEntry.COLUMN_NAME, name)
            values.put(CCContract.AccountEntry.COLUMN_BALANCE, balance)
            return values
        }

    constructor(): super()

    constructor(parcel: Parcel): super(parcel) {
        id = parcel.readLong()
        name = parcel.readString()
        balance = parcel.readDouble()
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        super.writeToParcel(dest, flags)

        dest?.writeLong(id)
        dest?.writeString(name)
        dest?.writeDouble(balance)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Account) return false

        return this.id == other.id
                && this.name == other.name
                && this.balance == other.balance
    }

    override fun hashCode(): Int {
        return id.hashCode() * name.hashCode() * balance.hashCode()
    }

    companion object {
        @JvmField val CREATOR = creator(::Account)
    }
}