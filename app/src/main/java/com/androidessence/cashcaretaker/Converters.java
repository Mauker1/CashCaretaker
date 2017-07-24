package com.androidessence.cashcaretaker;

import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import com.androidessence.utility.DateUtilsKt;
import com.androidessence.utility.StringUtilsKt;

import java.util.Date;

/**
 * Converts dates to strings to be stored in the database.
 */
public class Converters {
    @TypeConverter
    public static Date fromDateString(String value) {
        return (value == null) ? null : StringUtilsKt.asDatabaseDate(value);
    }

    @TypeConverter
    public static String fromDatabaseDate(Date value) {
        return (value == null) ? null : DateUtilsKt.asDatabaseString(value);
    }
}
