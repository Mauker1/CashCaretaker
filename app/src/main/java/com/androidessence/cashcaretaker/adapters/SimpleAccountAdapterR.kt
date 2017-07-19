package com.androidessence.cashcaretaker.adapters

import android.content.Context
import android.database.Cursor
import android.support.v4.widget.CursorAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.adammcneilly.CoreRecyclerViewAdapter
import com.adammcneilly.CoreViewHolder

import com.androidessence.cashcaretaker.R
import com.androidessence.cashcaretaker.data.CCContract
import com.androidessence.cashcaretaker.dataTransferObjects.Account

/**
 * Adapter that displays basic account information.
 */
class SimpleAccountAdapterR(items: MutableList<Account> = ArrayList()) : CoreRecyclerViewAdapter<Account, SimpleAccountAdapterR.SimpleAccountViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SimpleAccountViewHolder {
        return createViewHolder(::SimpleAccountViewHolder, R.layout.list_item_textview, parent)
    }

    class SimpleAccountViewHolder(view: View): CoreViewHolder<Account>(view) {
        private val accountNameTextView: TextView = view.findViewById(R.id.list_item_text_view) as TextView

        override fun bindItem(item: Account?) {
            accountNameTextView.text = item?.name
        }
    }
}
