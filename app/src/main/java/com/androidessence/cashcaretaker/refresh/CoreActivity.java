package com.androidessence.cashcaretaker.refresh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Core Activity class.
 *
 * Created by adam.mcneilly on 11/19/16.
 */
public class CoreActivity extends AppCompatActivity {
    protected FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    protected FirebaseUser getCurrentUser() {
        return firebaseAuth.getCurrentUser();
    }
}
