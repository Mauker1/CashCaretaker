package com.androidessence.utility.refresh;

/**
 * Represents an account object.
 *
 * Created by adam.mcneilly on 11/19/16.
 */
public class Account {
    private String name;
    private double startingBalance;

    public Account(String name, double startingBalance) {
        this.name = name;
        this.startingBalance = startingBalance;
    }

    public String getName() {
        return name;
    }

    public double getStartingBalance() {
        return startingBalance;
    }
}
