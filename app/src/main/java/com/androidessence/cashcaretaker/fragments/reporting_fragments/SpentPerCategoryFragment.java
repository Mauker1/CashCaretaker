package com.androidessence.cashcaretaker.fragments.reporting_fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidessence.cashcaretaker.R;
import com.androidessence.cashcaretaker.data.CCContract;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment for displaying money spent per category.
 *
 * Created by adammcneilly on 1/11/16.
 */
public class SpentPerCategoryFragment extends Fragment {
    private HorizontalBarChart mMoneyPerCategoryChart;

    private static final String[] MONEY_PER_CATEGORY_COLUMNS = new String[] {
            CCContract.CategoryEntry.COLUMN_DESCRIPTION,
            "SUM(CASE WHEN " + CCContract.TransactionEntry.COLUMN_WITHDRAWAL + " THEN " + CCContract.TransactionEntry.COLUMN_AMOUNT + " ELSE 0 - " + CCContract.TransactionEntry.COLUMN_AMOUNT + " END) AS " + CCContract.CategoryEntry.ALIAS_SPENT_IN_CATEGORY
    };

    private static final int CATEGORY_INDEX = 0;
    private static final int SPENT_INDEX = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spent_per_category, container, false);

        mMoneyPerCategoryChart = (HorizontalBarChart) view.findViewById(R.id.money_per_category_chart);

        setupMoneyPerCategoryReport();

        return view;
    }

    private void setupMoneyPerCategoryReport() {
        Legend l = mMoneyPerCategoryChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setFormSize(8f);
        l.setXEntrySpace(4f);

        Cursor cursor = getActivity().getContentResolver().query(
                CCContract.CategoryEntry.MONEY_PER_CATEGORY_URI,
                MONEY_PER_CATEGORY_COLUMNS,
                null,
                null,
                null
        );

        assert cursor != null;

        // Create array list for category names and values
        List<BarEntry> values = new ArrayList<>();
        List<String> categoryNames = new ArrayList<>();

        int index = 0;
        while(cursor.moveToNext()) {
            // index can be current list size, and get value
            values.add(new BarEntry(cursor.getFloat(SPENT_INDEX), index++));
            categoryNames.add(cursor.getString(CATEGORY_INDEX));
        }

        cursor.close();

        BarDataSet barDataSet = new BarDataSet(values, "Spent Per Category");
        List<Integer> chartColors = new ArrayList<>();
        chartColors.add(R.color.mds_blue_500);
        //barDataSet.setColors(chartColors);

        ArrayList<BarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);

        BarData data = new BarData(categoryNames, dataSets);
        data.setValueTextSize(10f);

        mMoneyPerCategoryChart.setData(data);
    }
}
