package com.androidessence.cashcaretaker

import android.arch.persistence.room.TypeConverter
import com.androidessence.utility.asDatabaseDate
import com.androidessence.utility.asDatabaseString
import java.util.*


/**
 * Converts a date to and from a timestamp for DB storage.
 */
class Converters {
    companion object {
        @TypeConverter
        fun fromDateString(value: String): Date? {
            return value.asDatabaseDate()
        }

        @TypeConverter
        fun toDateString(value: Date?): String {
            return value?.asDatabaseString().orEmpty()
        }
    }
}