package com.androidessence.utility.refresh;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user, user has a list of Accounts.
 *
 * Created by adam.mcneilly on 11/20/16.
 */
public class User {
    private String uuid;
    private List<Account> accounts = new ArrayList<>();

    public User(String uuid) {
        this.uuid = uuid;
    }

    public boolean addAccount(Account account) {
        return accounts.add(account);
    }

    public String getUuid() {
        return uuid;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
