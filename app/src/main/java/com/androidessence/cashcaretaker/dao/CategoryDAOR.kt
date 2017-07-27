package com.androidessence.cashcaretaker.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.androidessence.cashcaretaker.dataTransferObjects.CategoryR

/**
 * DAO to get category data.
 */
@Dao
interface CategoryDAOR {
    @Query("SELECT * FROM categoryr")
    fun getCategories(): List<CategoryR>

    @Query("DELETE FROM categoryr")
    fun deleteAll(): Int

    @Insert
    fun insert(vararg accounts: CategoryR): List<Long>
}