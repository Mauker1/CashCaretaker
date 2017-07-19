package com.androidessence.cashcaretaker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.adammcneilly.CoreRecyclerViewAdapter
import com.adammcneilly.CoreViewHolder
import com.androidessence.cashcaretaker.R
import com.androidessence.cashcaretaker.dataTransferObjects.Category

/**
 * Adapter that displays a list of categories.
 */
class CategoryAdapterR(items: MutableList<Category> = ArrayList()) : CoreRecyclerViewAdapter<Category, CategoryAdapterR.CategoryViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CategoryViewHolder {
        return createViewHolder(::CategoryViewHolder, R.layout.list_item_textview, parent)
    }

    class CategoryViewHolder(view: View): CoreViewHolder<Category>(view) {
        private val categoryNameTextView: TextView = view.findViewById(R.id.list_item_text_view) as TextView

        override fun bindItem(item: Category?) {
            categoryNameTextView.text = item?.description
        }
    }
}
