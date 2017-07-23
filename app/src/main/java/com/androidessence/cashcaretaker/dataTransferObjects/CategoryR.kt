package com.androidessence.cashcaretaker.dataTransferObjects

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import com.androidessence.utility.asBoolean

/**
 * Represents a category for a transaction.
 */
@Entity(tableName = "categoryTable")
data class CategoryR(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id") var id: Long = 0,
        @ColumnInfo(name = "categoryDescription") var description: String = "",
        @ColumnInfo(name = "categoryIsDefault") var isDefault: Boolean = false)
