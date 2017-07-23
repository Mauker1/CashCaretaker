package com.androidessence.cashcaretaker.core

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.androidessence.cashcaretaker.dataTransferObjects.CategoryR

/**
 * DAO to get category data.
 */
@Dao
interface CategoryDAOR {
    @Query("SELECT * FROM categoryTable")
    fun getCategories(): List<CategoryR>

    @Query("DELETE FROM categoryTable")
    fun deleteAll(): Int

    @Insert
    fun insert(vararg accounts: CategoryR): List<Long>
}