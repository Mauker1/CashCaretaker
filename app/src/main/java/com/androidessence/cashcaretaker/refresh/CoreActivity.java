package com.androidessence.cashcaretaker.refresh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.androidessence.cashcaretaker.R;
import com.androidessence.utility.refresh.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Core Activity class.
 *
 * Created by adam.mcneilly on 11/19/16.
 */
public class CoreActivity extends AppCompatActivity {
    private static final String LOG_TAG = CoreActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseReference ref = FirebaseUtils.getCurrentUserReference();
        if(ref != null) {
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);
                    if(user != null) {
                        FirebaseUtils.setCurrentUser(user);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e(LOG_TAG, databaseError.getMessage());
                }
            });
        }
    }

    protected void showFragment(CoreFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment, fragment.getFragmentTag()).addToBackStack(fragment.getFragmentTag()).commit();
    }
}
