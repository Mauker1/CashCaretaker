package com.androidessence.cashcaretaker.refresh;

import com.androidessence.utility.refresh.Account;
import com.androidessence.utility.refresh.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * Utility methods for connecting with firebase.
 *
 * Created by adam.mcneilly on 11/23/16.
 */
public class FirebaseUtils {
    private static final String SEPARATOR = "/";
    private static final String USERS = "users";
    private static final String ACCOUNTS = "accounts";
    private static final String ACCOUNT_NAME = "name";

    private static User currentUser;

    public static FirebaseAuth getFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    public static FirebaseDatabase getFirebaseDatabase() {
        return FirebaseDatabase.getInstance();
    }

    public static FirebaseUser getCurrentFirebaseUser() {
        return getFirebaseAuth().getCurrentUser();
    }

    public static DatabaseReference getReference(String node) {
        return getFirebaseDatabase().getReference(node);
    }

    public static DatabaseReference getCurrentUserReference() {
        return (getCurrentUser() != null)
                ? getReference(USERS + SEPARATOR + getCurrentUser().getUid())
                : null;
    }

    public static Query getUserAccounts() {
        return (getCurrentUserReference() != null)
                ? getCurrentUserReference().child(ACCOUNTS).orderByChild(ACCOUNT_NAME)
                : null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static void addAccount(Account account) {
        if(currentUser != null) {
            currentUser.addAccount(account);
        }
    }

    public static void updateUser() {
        if(getCurrentUserReference() != null) {
            getCurrentUserReference().setValue(currentUser);
        }
    }
}
