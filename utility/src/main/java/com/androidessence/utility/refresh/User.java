package com.androidessence.utility.refresh;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user, user has a list of Accounts.
 *
 * Created by adam.mcneilly on 11/20/16.
 */
public class User {
    private String uid;
    private List<Account> accounts = new ArrayList<>();

    public User() {

    }

    public User(String uid) {
        this.uid = uid;
    }

    public boolean addAccount(Account account) {
        return accounts.add(account);
    }

    public String getUid() {
        return uid;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
