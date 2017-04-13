package com.androidessence.cashcaretaker.models

import android.os.Parcel
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit tests for the Category class.
 *
 * Created by adam.mcneilly on 4/12/17.
 */
class CategoryTest {
    @Test
    fun testParcel() {
        val category = Category()
        category.description = "None"
        category.isDefault = false

        val parcel = Parcel.obtain()
        category.writeToParcel(parcel, 0)

        parcel.setDataPosition(0)

        val testCategory = Category.CREATOR.createFromParcel(parcel)
        assertEquals(category, testCategory)
    }
}