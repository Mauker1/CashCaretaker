package com.androidessence.cashcaretaker.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.androidessence.cashcaretaker.dataTransferObjects.RepeatingPeriodR

/**
 * Access RepeatingPeriod information.
 */
@Dao
interface RepeatingPeriodDAOR {
    @Query("SELECT * FROM repeatingPeriodr")
    fun getRepeatingPeriods(): List<RepeatingPeriodR>

    @Query("DELETE FROM repeatingPeriodr")
    fun deleteAll(): Int

    @Insert
    fun insert(vararg repeatingPeriods: RepeatingPeriodR): List<Long>
}