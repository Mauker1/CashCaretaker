package com.androidessence.utility

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Utility constants to be used for date utils.
 */
object DateUtils {
    // Formats
    private val DB_DATE_FORMAT = "yyyy-MM-dd"
    private val UI_DATE_FORMAT = "MMMM dd, yyyy"
    val dbDateFormatter = SimpleDateFormat(DB_DATE_FORMAT, Locale.getDefault())
    val uiDateFormatter = SimpleDateFormat(UI_DATE_FORMAT, Locale.getDefault())
}

/**
 * Returns a Calendar instance with this date as the time.
 */
fun Date.asCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar
}

/**
 * Retrieves a field from a Calendar with this date's time.
 */
fun Date.get(field: Int): Int {
    return this.asCalendar().get(field)
}

/**
 * Retrieves the year from a Calendar with this date.
 */
fun Date.year(): Int {
    return this.get(Calendar.YEAR)
}

/**
 * Converts a date to a string that can be saved in the database.
 */
fun Date.asDatabaseString(): String {
    if (this.year() < 0) {
        throw UnsupportedOperationException("Date has negative year value.")
    } else {
        return DateUtils.dbDateFormatter.format(this)
    }
}

/**
 * Returns a readable string representation of a date stored in the database.
 */
fun Date.asUiString(): String {
    if (this.year() < 0) {
        throw UnsupportedOperationException("Date has negative year value.")
    } else {
        return DateUtils.uiDateFormatter.format(this)
    }
}