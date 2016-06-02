package com.androidessence.cashcaretaker;

import android.view.View;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


/**
 * Custom matchers used for Espresso tests.
 *
 * Created by adam.mcneilly on 6/2/16.
 */
public class MatcherUtils {

    /**
     * Matches an item at a specific position in a recyclerview.
     *
     * Came from StackOverflow: http://stackoverflow.com/a/34795431/3131147
     */
//    public static Matcher<View> atPosition(final int position, final Matcher<View> itemMatcher) {
//        // checkNotNull(itemMatcher);
//
//
//    }

    public static Matcher<View> withError(final String expectedError) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof EditText)) {
                    return false;
                }

                String error = ((EditText) view).getError().toString();

                return expectedError.equals(error);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }
}
