package com.androidessence.cashcaretaker.refresh;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.androidessence.cashcaretaker.R;
import com.androidessence.utility.refresh.Account;
import com.androidessence.utility.refresh.User;

/**
 * Fragment for adding an account.
 *
 * Created by adam.mcneilly on 11/22/16.
 */
public class AddAccountFragment extends CoreFragment {
    private EditText accountName;
    private EditText startingBalance;

    public static AddAccountFragment newInstance() {
        Bundle args = new Bundle();

        AddAccountFragment fragment = new AddAccountFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_account, container, false);

        accountName = (EditText) view.findViewById(R.id.account_name);
        startingBalance = (EditText) view.findViewById(R.id.starting_balance);
        Button submit = (Button) view.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hasValidInput()) {
                    Account account = new Account(accountName.getText().toString(), Double.parseDouble(startingBalance.getText().toString()));
                    User user = getCurrentUser();
                    user.addAccount(account);
                    updateUser();
                    //TODO: Handle completion?
                }
            }
        });

        return view;
    }

    private boolean hasValidInput() {
        boolean isValid = true;

        if(TextUtils.isEmpty(accountName.getText())) {
            accountName.setError(getString(R.string.err_account_blank));
            isValid = false;
        }

        if(TextUtils.isEmpty(startingBalance.getText())) {
            startingBalance.setError(getString(R.string.err_starting_balance_blank));
            isValid = false;
        }

        return isValid;
    }

    @Override
    public String getFragmentTag() {
        return AddAccountFragment.class.getSimpleName();
    }
}
