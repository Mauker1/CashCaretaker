package com.androidessence.cashcaretaker.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.androidessence.cashcaretaker.BuildConfig;
import com.androidessence.cashcaretaker.R;
import com.androidessence.cashcaretaker.core.CoreActivity;

public class AboutActivity extends CoreActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setupToolbar(true);

        // Setup version
        TextView versionTextView = (TextView) findViewById(R.id.app_version);
        versionTextView.setText(String.format(getString(R.string.version_string), BuildConfig.VERSION_NAME));
    }
}
