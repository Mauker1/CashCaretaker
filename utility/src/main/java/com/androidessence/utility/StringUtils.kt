package com.androidessence.utility

import java.text.ParseException
import java.util.*

/**
 * Converts a string from the database to a date string to be displayed on the UI.
 */
fun String.asUiDateString(): String {
    return DateUtils.uiDateFormatter.format(this.asDatabaseDate())
}

/**
 * Converts a string from the database to a Date object.
 */
fun String.asDatabaseDate(): Date {
    try {
        val retDate = DateUtils.dbDateFormatter.parse(this)

        if (retDate.year() < 0) {
            throw UnsupportedOperationException("Date has negative year value.")
        } else {
            return retDate
        }
    } catch (pe: ParseException) {
        pe.printStackTrace()
        return Date()
    }
}