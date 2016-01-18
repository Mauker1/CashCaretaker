package com.androidessence.cashcaretaker.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.androidessence.cashcaretaker.R;
import com.androidessence.cashcaretaker.adapters.ReportAdapter;
import com.androidessence.cashcaretaker.fragments.reporting_fragments.ReportingFragment;
import com.androidessence.cashcaretaker.fragments.reporting_fragments.SpentPerCategoryFragment;

public class ReportingActivity extends AppCompatActivity implements ReportAdapter.OnReportSelectedListener{
    private int mState;
    private static final int STATE_REPORTS = 0;
    private static final int STATE_MONEY_SPENT_PER_CATEGORY = 1;

    private static final String ARG_STATE = "argState";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Read arguments.
        mState = savedInstanceState != null ? savedInstanceState.getInt(ARG_STATE, STATE_REPORTS) : STATE_REPORTS;

        showFragment();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_STATE, mState);
    }

    private void showFragment() {
        Fragment fragment;

        switch(mState) {
            case STATE_MONEY_SPENT_PER_CATEGORY:
                fragment = new SpentPerCategoryFragment();
                break;
            case STATE_REPORTS:
            default:
                fragment = new ReportingFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_reporting, fragment).commit();
    }

    @Override
    public void onReportSelected(String report) {
        if(report.equals(getString(R.string.spent_per_category))) {
            mState = STATE_MONEY_SPENT_PER_CATEGORY;
        }

        showFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                goBack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        goBack();
    }

    private void goBack() {
        // If we are in the reports fragment, finish activity. Otherwise, go back to reports list.
        if(mState == STATE_REPORTS) {
            finish();
        } else {
            mState = STATE_REPORTS;
            showFragment();
        }
    }
}
