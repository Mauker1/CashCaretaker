package com.androidessence.cashcaretaker.refresh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.androidessence.utility.refresh.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Core Activity class.
 *
 * Created by adam.mcneilly on 11/19/16.
 */
public class CoreActivity extends AppCompatActivity {
    private static final String LOG_TAG = CoreActivity.class.getSimpleName();

    protected FirebaseAuth firebaseAuth;
    protected FirebaseDatabase database;

    protected User currentUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        currentUser = new User(getCurrentUser().getUid());

        DatabaseReference ref = getUserReference(currentUser.getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if(user != null) {
                    currentUser = user;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(LOG_TAG, databaseError.getMessage());
            }
        });
    }

    protected FirebaseUser getCurrentUser() {
        return firebaseAuth.getCurrentUser();
    }

    protected void updateUser() {
        getUserReference(currentUser.getUid()).setValue(currentUser);
    }

    protected DatabaseReference getUserReference(String uid) {
        return getReference("users/" + uid);
    }

    protected DatabaseReference getReference(String node) {
        return database.getReference(node);
    }
}
