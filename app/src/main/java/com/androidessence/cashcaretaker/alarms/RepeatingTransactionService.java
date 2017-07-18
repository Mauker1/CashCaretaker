package com.androidessence.cashcaretaker.alarms;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;

import com.androidessence.cashcaretaker.data.CCContract;
import com.androidessence.cashcaretaker.dataTransferObjects.RepeatingTransaction;
import com.androidessence.utility.DateUtilsKt;
import com.androidessence.utility.StringUtilsKt;

import java.util.Calendar;
import java.util.Date;

/**
 * Service that checks for repeating transactions and runs them if necessary.
 *
 * Created by adammcneilly on 11/15/15.
 */
public class RepeatingTransactionService extends IntentService {
    private Context context;

    public RepeatingTransactionService() {
        super("RepeatingTransactionService");
    }

    // Keep track of indexes for repeating periods. These are hard coded.
    //TODO: Find better idea
    private static final int MONTHLY = 1;
    private static final int YEARLY = 2;

    // Necessary repeating transaction columns
    private static final String[] REPEATING_TRANSACTION_COLUMNS = new String[] {
            CCContract.RepeatingTransactionEntry.TABLE_NAME + "." + CCContract.RepeatingTransactionEntry._ID,
            CCContract.RepeatingTransactionEntry.COLUMN_DESCRIPTION,
            CCContract.RepeatingTransactionEntry.COLUMN_AMOUNT,
            CCContract.RepeatingTransactionEntry.COLUMN_WITHDRAWAL,
            CCContract.RepeatingTransactionEntry.COLUMN_NEXT_DATE,
            CCContract.RepeatingTransactionEntry.COLUMN_NOTES,
            CCContract.RepeatingTransactionEntry.COLUMN_ACCOUNT,
            CCContract.RepeatingTransactionEntry.COLUMN_REPEATING_PERIOD,
            CCContract.RepeatingTransactionEntry.COLUMN_CATEGORY
    };

    private static final int ID_INDEX = 0;
    private static final int DESCRIPTION_INDEX = 1;
    private static final int DATE_INDEX = 4;
    private static final int REPEATING_PERIOD_INDEX = 7;

    @Override
    protected void onHandleIntent(Intent intent) {
        context = getApplicationContext();

        // Check for any alarms prior to today
        updateRepeatingTransactions();
    }

    /**
     * Updates any current or previous RepeatingTransaction entries that need to be run.
     */
    private void updateRepeatingTransactions() {
        String currentDate = DateUtilsKt.asDatabaseString(new Date());

        // We need an outer loop so we continue to check until we are caught up.
        // If we process *any* transaction, set `hasTrans` to yes, and continue until we don't.

        boolean hasTrans;

        do {
            hasTrans = false;

            Cursor cursor = context.getContentResolver().query(
                    CCContract.RepeatingTransactionEntry.CONTENT_URI,
                    REPEATING_TRANSACTION_COLUMNS,
                    CCContract.RepeatingTransactionEntry.COLUMN_NEXT_DATE + " <= ?",
                    new String[]{currentDate},
                    null
            );

            assert cursor != null;

            while(cursor.moveToNext()) {
                hasTrans = true;
                // Get repeating transaction
                RepeatingTransaction repeatingTransaction = new RepeatingTransaction(cursor);

                // Insert transaction
                context.getContentResolver().insert(CCContract.TransactionEntry.CONTENT_URI, repeatingTransaction.getTransactionContentValues());

                // Switch based on update
                String transDateString = cursor.getString(DATE_INDEX);
                Date transDate = StringUtilsKt.asDatabaseDate(transDateString);
                Date nextDate = null;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(transDate);
                switch(cursor.getInt(REPEATING_PERIOD_INDEX)) {
                    case MONTHLY:
                        // Update monthly
                        calendar.add(Calendar.MONTH, 1);
                        nextDate = calendar.getTime();
                        break;
                    case YEARLY:
                        // Update yearly
                        calendar.add(Calendar.YEAR, 1);
                        nextDate = calendar.getTime();
                        break;
                    default:
                        break;
                }

                if(nextDate != null) {
                    String description = cursor.getString(DESCRIPTION_INDEX);
                    Log.v("ADAM", description);
                    Log.v("ADAM", "Current date: " + transDateString);
                    Log.v("ADAM", "Future date: " + DateUtilsKt.asDatabaseString(nextDate));

                    // Update to next date.
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(CCContract.RepeatingTransactionEntry.COLUMN_NEXT_DATE, DateUtilsKt.asDatabaseString(nextDate));

                    long id = cursor.getLong(ID_INDEX);
                    context.getContentResolver().update(
                            CCContract.RepeatingTransactionEntry.CONTENT_URI,
                            contentValues,
                            CCContract.RepeatingTransactionEntry._ID + " = ?",
                            new String[]{String.valueOf(id)}
                    );
                }
            }

            cursor.close();
        } while(hasTrans);

        Log.v("ADAM", "Service called.");
    }
}
