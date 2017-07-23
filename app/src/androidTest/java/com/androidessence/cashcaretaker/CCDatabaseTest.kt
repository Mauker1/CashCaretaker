package com.androidessence.cashcaretaker

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.androidessence.cashcaretaker.activities.AccountsActivity
import com.androidessence.cashcaretaker.core.AccountDAOR
import com.androidessence.cashcaretaker.core.CCDatabaseR
import com.androidessence.cashcaretaker.dataTransferObjects.AccountR
import junit.framework.Assert
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CCDatabaseTest {
    private lateinit var accountDao: AccountDAOR

    @Rule @JvmField val activityRule = ActivityTestRule<AccountsActivity>(AccountsActivity::class.java)

    @Before
    fun setup() {
        val context = activityRule.activity
        val database = Room.inMemoryDatabaseBuilder(context, CCDatabaseR::class.java)
                .allowMainThreadQueries()
                .build()
        accountDao = database.accountDao()
    }

    @After
    fun teardown() {
        accountDao.deleteAll()
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

    companion object {
        private val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase?) {
                // No-Op
            }
        }
    }
}

//@RunWith(AndroidJUnit4.class)
//public class SimpleEntityReadWriteTest {
//    private UserDao mUserDao;
//    private TestDatabase mDb;
//
//    @Before
//    public void createDb() {
//        Context context = InstrumentationRegistry.getTargetContext();
//        mDb = Room.inMemoryDatabaseBuilder(context, TestDatabase.class).build();
//        mUserDao = mDb.getUserDao();
//    }
//
//    @After
//    public void closeDb() throws IOException {
//        mDb.close();
//    }
//
//    @Test
//    public void writeUserAndReadInList() throws Exception {
//        User user = TestUtil.createUser(3);
//        user.setName("george");
//        mUserDao.insert(user);
//        List<User> byName = mUserDao.findUsersByName("george");
//        assertThat(byName.get(0), equalTo(user));
//    }
//}