package com.androidessence.cashcaretaker.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.androidessence.cashcaretaker.BuildConfig;
import com.androidessence.cashcaretaker.R;
import com.androidessence.cashcaretaker.core.CoreActivity;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutActivity extends CoreActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setupToolbar(true);

        Element versionElement = new Element();
        versionElement.setTitle("Version: " + BuildConfig.VERSION_NAME);

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.cashcaretaker)
                .setDescription("Cash Caretaker is a finance tracker that will keep track of all of your bank accounts and credit cards in one place. You will no longer have to deal with multiple apps to keep track of your accounts at the bank, and each of your different credit cards. With this personal checkbook you will be able to record all of your financial information to get a quick overview of all of your money at once.")
                .addItem(versionElement)
                .addGroup("Connect")
                .addWebsite("http://androidessence.com")
                .addPlayStore("com.androidessence.cashcaretaker")
                .addGitHub("adammc331/cashcaretaker")
                .create();

        LinearLayout rootView = (LinearLayout) findViewById(R.id.root);
        rootView.addView(aboutPage);
    }
}
