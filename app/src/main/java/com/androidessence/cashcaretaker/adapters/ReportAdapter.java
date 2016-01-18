package com.androidessence.cashcaretaker.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidessence.cashcaretaker.R;

/**
 * Adapter for displaying the types of reports.
 *
 * Created by adammcneilly on 1/16/16.
 */
public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {
    private Context mContext;
    private String[] mReports;

    public ReportAdapter(Context context) {
        this.mContext = context;

        this.mReports = mContext.getResources().getStringArray(R.array.reports);
    }

    @Override
    public ReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReportViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_report, parent, false));
    }

    @Override
    public void onBindViewHolder(ReportViewHolder holder, int position) {
        holder.bindReport(mReports[position]);
    }

    @Override
    public int getItemCount() {
        return mReports.length;
    }

    public class ReportViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mReport;

        public ReportViewHolder(View view) {
            super(view);

            mReport = (TextView) view.findViewById(R.id.report);

            view.setOnClickListener(this);
        }

        public void bindReport(String report) {
            mReport.setText(report);
        }

        @Override
        public void onClick(View v) {
            ((OnReportSelectedListener)mContext).onReportSelected(mReports[getAdapterPosition()]);
        }
    }

    public interface OnReportSelectedListener{
        void onReportSelected(String report);
    }
}
