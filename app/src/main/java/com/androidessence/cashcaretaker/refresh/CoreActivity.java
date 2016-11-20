package com.androidessence.cashcaretaker.refresh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Core Activity class.
 *
 * Created by adam.mcneilly on 11/19/16.
 */
public class CoreActivity extends AppCompatActivity {
    protected FirebaseAuth firebaseAuth;
    protected FirebaseDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
    }

    protected FirebaseUser getCurrentUser() {
        return firebaseAuth.getCurrentUser();
    }

    protected DatabaseReference getReference(String node) {
        return database.getReference(node);
    }
}
