package com.androidessence.cashcaretaker.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.adammcneilly.CoreRecyclerViewAdapter
import com.adammcneilly.CoreViewHolder
import com.androidessence.cashcaretaker.R
import com.androidessence.cashcaretaker.dataTransferObjects.RepeatingPeriod

/**
 * Adapter for displaying repeating periods.
 */
class RepeatingPeriodAdapterR(items: MutableList<RepeatingPeriod> = ArrayList()) : CoreRecyclerViewAdapter<RepeatingPeriod, RepeatingPeriodAdapterR.RepeatingPeriodViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RepeatingPeriodViewHolder {
        return createViewHolder(::RepeatingPeriodViewHolder, R.layout.list_item_textview, parent)
    }

    class RepeatingPeriodViewHolder(view: View): CoreViewHolder<RepeatingPeriod>(view) {
        val repeatingPeriodNameTextView: TextView = view.findViewById(R.id.list_item_text_view) as TextView

        override fun bindItem(item: RepeatingPeriod?) {
            repeatingPeriodNameTextView.text = item?.name
        }
    }
}
