package com.androidessence.cashcaretaker

import android.arch.persistence.room.Room
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.androidessence.cashcaretaker.activities.AccountsActivity
import com.androidessence.cashcaretaker.core.AccountDAOR
import com.androidessence.cashcaretaker.core.CCDatabaseR
import junit.framework.TestCase
import junit.framework.TestCase.assertTrue
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

    @Test
    fun testReadAccounts() {
        val accounts = accountDao.getAccounts()
        assertTrue(accounts.isNotEmpty())
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