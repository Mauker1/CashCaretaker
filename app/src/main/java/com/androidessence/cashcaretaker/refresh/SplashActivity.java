package com.androidessence.cashcaretaker.refresh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Splash screen.
 *
 * Created by adam.mcneilly on 11/19/16.
 */
public class SplashActivity extends CoreActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(FirebaseUtils.getCurrentFirebaseUser() == null) {
            startLoginActivity();
        } else {
            startMainActivity();
        }
    }

    private void startLoginActivity() {
        Intent login = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(login);
    }

    private void startMainActivity() {
        Intent main = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(main);
    }
}
