package com.androidessence.cashcaretaker;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.androidessence.cashcaretaker.activities.AccountsActivity;
import com.androidessence.cashcaretaker.data.CCContract;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static com.androidessence.cashcaretaker.MatcherUtils.withError;

/**
 * Runs various tests needed for the AccountsActivity.
 *
 * Created by adam.mcneilly on 6/1/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestAccountsActivity {
    private static final String ACCOUNT_NAME = "Checking";
    private static final double ACCOUNT_BALANCE = 100.00;
    private static final String YES = "Yes";

    @Rule
    public ActivityTestRule<AccountsActivity> mActivityRule = new ActivityTestRule<>(AccountsActivity.class);

    @Before
    @After
    public void deleteAllAccounts() {
        // Delete all accounts
        mActivityRule.getActivity().getContentResolver().delete(CCContract.AccountEntry.CONTENT_URI, null, null);
    }

    @Test
    public void addAccount() {
        addAccountForNameAndBalance(ACCOUNT_NAME, String.valueOf(ACCOUNT_BALANCE));

        // Check for an item with this name, should be first.
        onView(withId(R.id.account_recycler_view)).check(matches(hasDescendant(withText(ACCOUNT_NAME))));
    }

    @Test
    public void addAccountWithBlankName() {
        addAccountForNameAndBalance("", String.valueOf(ACCOUNT_BALANCE));

        // Check that name error appears
        onView(withId(R.id.account_name)).check(matches(withError(mActivityRule.getActivity().getString(R.string.blank_account_name))));
    }

    @Test
    public void addAccountWithBlankBalance() {
        addAccountForNameAndBalance(ACCOUNT_NAME, "");

        // Check that balance error appears
        onView(withId(R.id.starting_balance)).check(matches(withError(mActivityRule.getActivity().getString(R.string.blank_account_balance))));
    }

    @Test
    public void addDuplicateAccount() {
        // Add account
        addAccountForNameAndBalance(ACCOUNT_NAME, String.valueOf(ACCOUNT_BALANCE));

        // Add account again
        addAccountForNameAndBalance(ACCOUNT_NAME, String.valueOf(ACCOUNT_BALANCE));

        // Account name should have error
        onView(withId(R.id.account_name)).check(matches(withError(mActivityRule.getActivity().getString(R.string.duplicate_account_error))));
    }

    @Test
    public void deleteAccount() {
        addAccount();

        // Long click this item
        onView(withText(ACCOUNT_NAME)).perform(longClick());

        // Click delete
        onView(withId(R.id.action_delete_account)).perform(click());

        // Click yes
        onView(withText(YES)).perform(click());

        // Verify recyclerview no longer has checking
        onView(withText(ACCOUNT_NAME)).check(doesNotExist());
    }

    /**
     * Adds an account with a given name and balance, does not do any verification.
     */
    private void addAccountForNameAndBalance(String accountName, String accountBalance) {
        // Click add fab
        onView(withId(R.id.add_account_fab)).perform(click());

        // Type account name
        onView(withId(R.id.account_name)).perform(typeText(accountName));

        // Type balance
        onView(withId(R.id.starting_balance)).perform(typeText(accountBalance), closeSoftKeyboard());

        // Submit
        onView(withId(R.id.submit)).perform(click());
    }
}
