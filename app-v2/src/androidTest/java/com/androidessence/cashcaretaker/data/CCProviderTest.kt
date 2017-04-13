package com.androidessence.cashcaretaker.data

import android.content.ContentUris
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.androidessence.cashcaretaker.AccountsActivity
import com.androidessence.cashcaretaker.models.Account
import junit.framework.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Tests our ContentProvider.
 *
 * Created by adam.mcneilly on 3/22/17.
 */
@RunWith(AndroidJUnit4::class)
open class CCProviderTest {

    @Rule @JvmField
    var activityRule = ActivityTestRule<AccountsActivity>(AccountsActivity::class.java)

    @Before
    fun setUp() {
        testDeleteAllRecords()
    }

    @After
    fun tearDown() {
        testDeleteAllRecords()
    }

    /**
     * Deletes all records from the database and ensures they were deleted.
     */
    @Test
    fun testDeleteAllRecords() {
        // Delete transactions
        activityRule.activity.contentResolver.delete(
                CCContract.TransactionEntry.CONTENT_URI,
                null,
                null
        )

        var cursor = activityRule.activity.contentResolver.query(
                CCContract.TransactionEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        )
        assertNotNull(cursor)
        assertEquals(0, cursor.count)
        cursor.close()

        // Delete repeating transactions
        activityRule.activity.contentResolver.delete(
                CCContract.RepeatingTransactionEntry.CONTENT_URI,
                null,
                null
        )

        cursor = activityRule.activity.contentResolver.query(
                CCContract.RepeatingTransactionEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        )
        assertNotNull(cursor)
        assertEquals(0, cursor.count)
        cursor.close()

        // Delete accounts
        activityRule.activity.contentResolver.delete(
                CCContract.AccountEntry.CONTENT_URI,
                null,
                null
        )

        // Verify deletion
        cursor = activityRule.activity.contentResolver.query(
                CCContract.AccountEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        )
        assertNotNull(cursor)
        assertEquals(0, cursor.count)
        cursor.close()
    }

    /**
     * Verifies the response of the `getType()` method required by content provider for each of the supported URIs in this application.
     */
    @Test
    fun testGetType() {
        //-- ACCOUNT --//
        var type: String = activityRule.activity.contentResolver.getType(CCContract.AccountEntry.CONTENT_URI)
        assertEquals(CCContract.AccountEntry.CONTENT_TYPE, type)

        //-- ACCOUNT_ID --//
        type = activityRule.activity.contentResolver.getType(CCContract.AccountEntry.buildAccountUri(0))
        assertEquals(CCContract.AccountEntry.CONTENT_ITEM_TYPE, type)

        //-- CATEGORY --//
        type = activityRule.activity.contentResolver.getType(CCContract.CategoryEntry.CONTENT_URI)
        assertEquals(CCContract.CategoryEntry.CONTENT_TYPE, type)

        //-- TRANSACTION --//
        type = activityRule.activity.contentResolver.getType(CCContract.TransactionEntry.CONTENT_URI)
        assertEquals(CCContract.TransactionEntry.CONTENT_TYPE, type)

        //-- TRANSACTION_ID --//
        type = activityRule.activity.contentResolver.getType(CCContract.TransactionEntry.buildTransactionUri(0))
        assertEquals(CCContract.TransactionEntry.CONTENT_ITEM_TYPE, type)

        //-- TRANSACTION_FOR_ACCOUNT --//
        type = activityRule.activity.contentResolver.getType(CCContract.TransactionEntry.buildTransactionsForAccountUri(0))
        assertEquals(CCContract.TransactionEntry.CONTENT_TYPE, type)

        //-- REPEATING_PERIOD --//
        type = activityRule.activity.contentResolver.getType(CCContract.RepeatingPeriodEntry.CONTENT_URI)
        assertEquals(CCContract.RepeatingPeriodEntry.CONTENT_TYPE, type)

        //-- REPEATING_TRANSACTION --//
        type = activityRule.activity.contentResolver.getType(CCContract.RepeatingTransactionEntry.CONTENT_URI)
        assertEquals(CCContract.RepeatingTransactionEntry.CONTENT_TYPE, type)

        //-- REPEATING_TRANSACTION_DETAILS
        type = activityRule.activity.contentResolver.getType(CCContract.RepeatingTransactionEntry.CONTENT_DETAILS_URI)
        assertEquals(CCContract.RepeatingTransactionEntry.CONTENT_TYPE, type)

        //-- REPEATING_TRANSACTION_ID --//
        type = activityRule.activity.contentResolver.getType(CCContract.RepeatingTransactionEntry.buildRepeatingTransactionUri(0))
        assertEquals(CCContract.RepeatingTransactionEntry.CONTENT_ITEM_TYPE, type)
    }

    @Test
    fun testInsertAccount() {
        // Create an account and insert it
        val account = Account()
        account.name = ACCOUNT_NAME
        account.balance = ACCOUNT_BALANCE.toDouble()

        val uri = activityRule.activity.contentResolver.insert(CCContract.AccountEntry.CONTENT_URI, account.contentValues)
        assertNotNull(uri)

        val id = ContentUris.parseId(uri)
        assertTrue(id > 0)
    }

    companion object {
        private val ACCOUNT_NAME = "Checking"
        private val ACCOUNT_BALANCE = 100
    }
}