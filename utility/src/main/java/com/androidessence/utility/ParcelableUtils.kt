package com.androidessence.utility

/**
 * Utility extension methods to make parcelables easier.
 */

fun Int.asBoolean(): Boolean {
    return (this != 0)
}

fun Boolean.asInt(): Int {
    return if (this) 1 else 0
}