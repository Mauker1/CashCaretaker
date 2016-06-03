package com.androidessence.cashcaretaker;

import android.support.annotation.NonNull;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.RecyclerView;
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
    public static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            protected boolean matchesSafely(RecyclerView item) {
                RecyclerView.ViewHolder viewHolder = item.findViewHolderForAdapterPosition(position);

                // If we had no view holder, return false. Otherwise, match.
                boolean result = viewHolder != null && itemMatcher.matches(viewHolder.itemView);
                return result;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }
        };
    }

    /**
     * Matches that an EditText has a specified error.
     */
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
                description.appendText("with error text: ");
                description.appendText(expectedError);
            }
        };
    }
}
