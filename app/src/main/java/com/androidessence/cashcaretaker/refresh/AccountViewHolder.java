package com.androidessence.cashcaretaker.refresh;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.androidessence.cashcaretaker.R;
import com.androidessence.utility.Utility;
import com.androidessence.utility.refresh.Account;

/**
 * ViewHolder for displaying Accounts.
 *
 * Created by adam.mcneilly on 11/22/16.
 */
public class AccountViewHolder extends RecyclerView.ViewHolder {
    private final TextView nameTextView;
    private final TextView balanceTextView;

    public AccountViewHolder(View view){
        super(view);

        nameTextView = (TextView) view.findViewById(R.id.account_name);
        balanceTextView = (TextView) view.findViewById(R.id.account_balance);
    }

    public void bind(Account account) {
        nameTextView.setText(account.getName());
        balanceTextView.setText(Utility.getCurrencyString(account.getStartingBalance()));
    }
}
