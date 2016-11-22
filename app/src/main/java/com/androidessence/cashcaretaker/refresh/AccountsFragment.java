package com.androidessence.cashcaretaker.refresh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidessence.cashcaretaker.R;
import com.androidessence.utility.refresh.Account;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

/**
 * Fragment that displays a list of accounts
 *
 * Created by adam.mcneilly on 11/22/16.
 */
public class AccountsFragment extends Fragment {

    public static AccountsFragment newInstance() {
        Bundle args = new Bundle();

        AccountsFragment fragment = new AccountsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.r_fragment_accounts, container, false);

        Query accounts = ((CoreActivity)getActivity()).getUserReference().child("accounts").orderByChild("name");

        FirebaseRecyclerAdapter<Account, AccountViewHolder> adapter = new FirebaseRecyclerAdapter<Account, AccountViewHolder>(
                Account.class, R.layout.list_item_account, AccountViewHolder.class, accounts
        ) {
            @Override
            protected void populateViewHolder(AccountViewHolder viewHolder, Account model, int position) {
                viewHolder.bind(model);
            }
        };

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.account_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
