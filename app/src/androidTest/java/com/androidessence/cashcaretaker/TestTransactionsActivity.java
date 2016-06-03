package com.androidessence.cashcaretaker;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.androidessence.cashcaretaker.activities.TransactionsActivity;
import com.androidessence.cashcaretaker.data.CCContract;
import com.androidessence.cashcaretaker.dataTransferObjects.Account;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Tests various things on the TransactionsActivity.
 *
 * Created by adam.mcneilly on 6/3/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestTransactionsActivity {
    private static final String ACCOUNT_NAME = "Checking";
    private static final double ACCOUNT_BALANCE = 100.00;
    private long accountId;

    private static final String TRANSACTION_DESCRIPTION = "Gas";
    private static final double TRANSACTION_AMOUNT = 20.35;

    @Rule
    public ActivityTestRule<TransactionsActivity> mActivityRule = new ActivityTestRule<>(TransactionsActivity.class, true, false);

    @Before
    public void createAccount() {
        Account account = new Account(ACCOUNT_NAME, ACCOUNT_BALANCE);

        Uri accountUri = InstrumentationRegistry.getInstrumentation().getTargetContext().getContentResolver().insert(
                CCContract.AccountEntry.CONTENT_URI, account.getContentValues());

        accountId = ContentUris.parseId(accountUri);

        // Set intent
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), TransactionsActivity.class);
        intent.putExtra(TransactionsActivity.ARG_ACCOUNT, account);

        mActivityRule.launchActivity(intent);
    }

    @After
    public void deleteTransactionsAndAccount() {
        mActivityRule.getActivity().getContentResolver().delete(
                CCContract.TransactionEntry.CONTENT_URI,
                CCContract.TransactionEntry.COLUMN_ACCOUNT + " = ?",
                new String[] {String.valueOf(accountId)}
        );

        mActivityRule.getActivity().getContentResolver().delete(
                CCContract.AccountEntry.CONTENT_URI,
                CCContract.AccountEntry._ID + " = ?",
                new String[]{String.valueOf(accountId)}
        );
    }

    @Test
    public void addTransaction() {
        // Click add fab
        onView(withId(R.id.add_transaction_fab)).perform(click());

        // Enter description
        onView(withId(R.id.transaction_description)).perform(typeText(TRANSACTION_DESCRIPTION));

        // Enter amount
        onView(withId(R.id.transaction_amount)).perform(typeText(String.valueOf(TRANSACTION_AMOUNT)), closeSoftKeyboard());

        // Click submit
        onView(withId(R.id.submit)).perform(click());

        // Verify we have an item with this description
        onView(withId(R.id.transaction_recycler_view)).check(matches(hasDescendant(withText(TRANSACTION_DESCRIPTION))));
    }
}
