package com.androidessence.cashcaretaker.models

import android.content.ContentValues

/**
 * Interface for SQLite models that have a set of ContentValues to be inserted into the database.
 *
 * Created by adam.mcneilly on 4/12/17.
 */
interface SQLiteModel {
    val contentValues: ContentValues
}