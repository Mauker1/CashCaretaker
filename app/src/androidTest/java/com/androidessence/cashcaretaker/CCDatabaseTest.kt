package com.androidessence.cashcaretaker

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.androidessence.cashcaretaker.activities.AccountsActivity
import com.androidessence.cashcaretaker.core.*
import com.androidessence.cashcaretaker.dataTransferObjects.AccountR
import com.androidessence.cashcaretaker.dataTransferObjects.CategoryR
import com.androidessence.cashcaretaker.dataTransferObjects.RepeatingPeriodR
import com.androidessence.cashcaretaker.dataTransferObjects.TransactionR
import junit.framework.Assert
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class CCDatabaseTest {
    private lateinit var accountDao: AccountDAOR
    private lateinit var categoryDao: CategoryDAOR
    private lateinit var repeatingPeriodDao: RepeatingPeriodDAOR
    private lateinit var transactionDao: TransactionDAOR

    @Rule @JvmField val activityRule = ActivityTestRule<AccountsActivity>(AccountsActivity::class.java)

    @Before
    fun setup() {
        val context = activityRule.activity
        val database = Room.inMemoryDatabaseBuilder(context, CCDatabaseR::class.java)
                .allowMainThreadQueries()
                .build()
        accountDao = database.accountDao()
        categoryDao = database.categoryDao()
        repeatingPeriodDao = database.repeatingPeriodDao()
        transactionDao = database.transactionDao()
    }

    @After
    fun teardown() {
        transactionDao.deleteAll()
        accountDao.deleteAll()
        categoryDao.deleteAll()
        repeatingPeriodDao.deleteAll()
    }

    @Test
    fun testWriteReadAccounts() {
        val testAccount = AccountR(name = "Checking", balance = 0.0)
        val ids = accountDao.insert(testAccount)
        testAccount.id = ids.first()

        val accounts = accountDao.getAccounts()
        assertTrue(accounts.isNotEmpty())
        assertEquals(testAccount, accounts.first())
    }

    @Test
    fun testDeleteAccounts() {
        val testAccount = AccountR(name = "Checking", balance = 0.0)
        val ids = accountDao.insert(testAccount)
        assertTrue(ids.isNotEmpty())

        accountDao.deleteAll()
        val accounts = accountDao.getAccounts()
        Assert.assertTrue(accounts.isEmpty())
    }

    @Test
    fun testWriteReadCategories() {
        val testCategory = CategoryR(description = "Payday", isDefault = false)
        val ids = categoryDao.insert(testCategory)
        testCategory.id = ids.first()

        val categories = categoryDao.getCategories()
        assertTrue(categories.isNotEmpty())
        assertEquals(testCategory, categories.first())
    }

    @Test
    fun testDeleteCategories() {
        val testCategory = CategoryR(description = "Payday", isDefault  = false)
        val ids = categoryDao.insert(testCategory)
        assertTrue(ids.isNotEmpty())

        categoryDao.deleteAll()
        val categories = categoryDao.getCategories()
        assertTrue(categories.isEmpty())
    }

    @Test
    fun testWriteReadRepeatingPeriods() {
        val testRepeatingPeriod = RepeatingPeriodR("YEARLY")
        val ids = repeatingPeriodDao.insert(testRepeatingPeriod)
        assertTrue(ids.isNotEmpty())

        val repeatingPeriods = repeatingPeriodDao.getRepeatingPeriods()
        assertTrue(repeatingPeriods.isNotEmpty())
        assertEquals(testRepeatingPeriod, repeatingPeriods.first())
    }

    @Test
    fun testDeleteRepeatingPeriods() {
        val testRepeatingPeriod = RepeatingPeriodR("YEARLY")
        val ids = repeatingPeriodDao.insert(testRepeatingPeriod)
        assertTrue(ids.isNotEmpty())

        repeatingPeriodDao.deleteAll()
        val repeatingPeriods = repeatingPeriodDao.getRepeatingPeriods()
        assertTrue(repeatingPeriods.isEmpty())
    }

    @Test
    fun writeReadTransactions() {
        // Create test account and category
        val testAccount = AccountR(name = "Checking", balance = 100.00)
        val accountIds = accountDao.insert(testAccount)
        testAccount.id = accountIds.first()

        val testCategory = CategoryR(description = "Payday")
        val categoryIds = categoryDao.insert(testCategory)
        testCategory.id = categoryIds.first()

        val testTransaction = TransactionR(
                testAccount.id,
                "Test description",
                50.00,
                "",
                Date(),
                testCategory.id,
                false
        )
        val transactionIds = transactionDao.insert(testTransaction)
        testTransaction.id = transactionIds.first()

        val transactions = transactionDao.getTransactions()
        assertTrue(transactions.isNotEmpty())
        assertEquals(testTransaction, transactions.first())
    }

    companion object {
        private val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase?) {
                // No-Op
            }
        }
    }
}