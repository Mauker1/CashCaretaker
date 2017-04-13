package com.androidessence.cashcaretaker.models

import android.content.ContentValues
import android.os.Parcel
import com.androidessence.cashcaretaker.data.CCContract
import com.androidessence.utility.asBoolean
import com.androidessence.utility.asInt
import com.androidessence.utility.creator

/**
 * Represents a Category that can be applied to a transaction.
 *
 * Created by adam.mcneilly on 4/12/17.
 */
open class Category : BaseModel {
    var id: Long = 0.toLong()
    var description: String = ""
    var isDefault: Boolean = false

    override val contentValues: ContentValues
        get() {
            val values = super.contentValues
            if (id != 0L) values.put(CCContract.CategoryEntry._ID, id)
            values.put(CCContract.CategoryEntry.COLUMN_DESCRIPTION, description)
            values.put(CCContract.CategoryEntry.COLUMN_IS_DEFAULT, isDefault)
            return values
        }

    constructor(): super()

    constructor(parcel: Parcel): super(parcel) {
        id = parcel.readLong()
        description = parcel.readString()
        isDefault = parcel.readInt().asBoolean()
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        super.writeToParcel(dest, flags)

        dest?.writeLong(id)
        dest?.writeString(description)
        dest?.writeInt(isDefault.asInt())
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Category) return false

        return this.id == other.id
                && this.description == other.description
                && this.isDefault == other.isDefault
    }

    override fun hashCode(): Int {
        return id.hashCode() * description.hashCode() * isDefault.hashCode()
    }

    companion object {
        @JvmField val CREATOR = creator(::Category)
    }
}