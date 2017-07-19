package com.androidessence.cashcaretaker.adapters

import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.adammcneilly.CoreRecyclerViewAdapter
import com.adammcneilly.CoreViewHolder
import com.androidessence.cashcaretaker.R
import com.androidessence.cashcaretaker.dataTransferObjects.Transaction
import com.androidessence.utility.asCurrency
import com.androidessence.utility.asUiString

/**
 * Adapter for displaying a list of transactions.
 */
class TransactionAdapterR(items: MutableList<Transaction> = ArrayList()) : CoreRecyclerViewAdapter<Transaction, TransactionAdapterR.TransactionViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TransactionViewHolder {
        return createViewHolder(::TransactionViewHolder, R.layout.list_item_transaction, parent)
    }

    class TransactionViewHolder(view: View) : CoreViewHolder<Transaction>(view) {
        private val red: Int = ContextCompat.getColor(view.context, R.color.mds_red_500)
        private val green: Int = ContextCompat.getColor(view.context, R.color.mds_green_500)
        private val primaryTextColor: Int = ContextCompat.getColor(view.context, android.R.color.primary_text_light)
        private val mDescriptionTextView: TextView = view.findViewById(R.id.transaction_description) as TextView
        private val mAmountTextView: TextView = view.findViewById(R.id.transaction_amount) as TextView
        private val mDateTextView: TextView = view.findViewById(R.id.transaction_date) as TextView
        private val mCategoryTextView: TextView = view.findViewById(R.id.transaction_category) as TextView
        private val mNotesTextView: TextView = view.findViewById(R.id.transaction_notes) as TextView
        private val mIndicatorView: View = view.findViewById(R.id.transaction_indicator)

        override fun bindItem(item: Transaction?) {
            val isWithdrawal = item?.isWithdrawal ?: false

            val amount = item?.amount
            val prependString = if (isWithdrawal) "-" else ""
            val amountText = prependString + amount?.asCurrency()

            val amountColor = if (isWithdrawal) red else primaryTextColor
            val indicatorColor = if (isWithdrawal) red else green

            val dateString = item?.date?.asUiString().orEmpty()

            mDescriptionTextView.text = item?.description
            mAmountTextView.text = amountText
            mAmountTextView.setTextColor(amountColor)
            mIndicatorView.setBackgroundColor(indicatorColor)
            mDateTextView.text = String.format("Next date: %s", dateString)
            //TODO: mCategoryTextView.text = (item?.category)
            //TODO: mAccountTextView.text = String.format("Account: %s", item?.account)
            mNotesTextView.text = String.format("Notes: %s", item?.notes)
        }

        override fun onClick(v: View) {
            mNotesTextView.visibility = if (mNotesTextView.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }

        //TODO:
//        override fun onLongClick(view: View): Boolean {
//            // Get current item and call back to activity
//            mCursorAdapter.cursor.moveToPosition(adapterPosition)
//            (mContext as OnTransactionLongClickListener).onTransactionLongClick(TransactionDetails(mCursorAdapter.cursor))
//            return true
//        }
    }
}
