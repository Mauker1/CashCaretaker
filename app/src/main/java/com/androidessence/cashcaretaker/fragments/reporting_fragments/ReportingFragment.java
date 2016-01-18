package com.androidessence.cashcaretaker.fragments.reporting_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidessence.cashcaretaker.DividerItemDecoration;
import com.androidessence.cashcaretaker.R;
import com.androidessence.cashcaretaker.adapters.ReportAdapter;

/**
 * Fragment for displaying the list of possible reports.
 *
 * Created by adammcneilly on 1/16/16.
 */
public class ReportingFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reporting, container, false);

        // Setup RecyclerView
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.report_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        ReportAdapter reportAdapter = new ReportAdapter(getActivity());
        recyclerView.setAdapter(reportAdapter);

        return view;
    }
}
