package com.androidessence.cashcaretaker.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.androidessence.cashcaretaker.R;
import com.androidessence.cashcaretaker.alarms.RepeatingTransactionAlarm;
import com.androidessence.cashcaretaker.core.CoreActivity;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Activity that displays a list of accounts to the user.
 *
 * Created by adam.mcneilly on 9/5/16.
 */
public class AccountsActivity extends CoreActivity {
    private static final String LOG_TAG = AccountsActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_accounts);

        setupToolbar(false);
        startAlarm();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_manage_repeating_transactions:
                startRepeatingTransactionsActivity();
                return true;
            case R.id.action_manage_categories:
                startManageCategoriesActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startRepeatingTransactionsActivity() {
        Intent repeatingTransactionsIntent = new Intent(this, RepeatingTransactionsActivity.class);
        startActivity(repeatingTransactionsIntent);
    }

    private void startManageCategoriesActivity() {
        Intent manageCategories = new Intent(this, ManageCategoriesActivity.class);
        startActivity(manageCategories);
    }

    private void startAlarm(){
        ComponentName receiver = new ComponentName(this, RepeatingTransactionAlarm.class);
        PackageManager pm = getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

        RepeatingTransactionAlarm alarm = new RepeatingTransactionAlarm();
        alarm.setAlarm(this);
    }
}
