package com.androidessence.cashcaretaker.adapters

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.adammcneilly.CoreRecyclerViewAdapter
import com.adammcneilly.CoreViewHolder
import com.androidessence.cashcaretaker.R
import com.androidessence.cashcaretaker.data.CCContract
import com.androidessence.cashcaretaker.dataTransferObjects.RepeatingTransaction
import com.androidessence.utility.asCurrency
import com.androidessence.utility.asUiString

/**
 * Adapter for displaying repeating transactions.
 */
class RepeatingTransactionAdapterR(items: MutableList<RepeatingTransaction> = ArrayList(), val context: Context) : CoreRecyclerViewAdapter<RepeatingTransaction, RepeatingTransactionAdapterR.RepeatingTransactionViewHolder>(items) {
    private var actionMode: ActionMode? = null

    private val actionModeCallback = object : ActionMode.Callback {
        // Called when the action mode is created; startActionMode() was called
        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            // Inflate a menu resource providing context menu items
            mode.menuInflater.inflate(R.menu.repeating_transaction_context_menu, menu)
            return true
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
            return false // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.action_delete_transaction -> {
                    // The transaction that was selected is passed as the tag
                    // for the action mode.
                    showDeleteAlertDialog(actionMode!!.tag as RepeatingTransaction)
                    mode.finish() // Action picked, so close the CAB
                    return true
                }
                else -> return false
            }
        }

        // Called when the user exits the action mode
        override fun onDestroyActionMode(mode: ActionMode) {
            actionMode = null
        }
    }

    private fun showDeleteAlertDialog(transaction: RepeatingTransaction) {
        AlertDialog.Builder(context)
                .setTitle("Delete Repeating Transaction")
                .setMessage("Are you sure you want to delete " + transaction.description + "?")
                .setPositiveButton("Yes") { dialog, which ->
                    //TODO: Handle update
                    // Remove
                    context.contentResolver.delete(
                            CCContract.RepeatingTransactionEntry.CONTENT_URI,
                            CCContract.RepeatingTransactionEntry._ID + " = ?",
                            arrayOf(transaction.identifier.toString())
                    )
                    dialog.dismiss()
                }
                .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
                .create().show()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RepeatingTransactionViewHolder {
        return createViewHolder(::RepeatingTransactionViewHolder, R.layout.list_item_repeating_transaction, parent)
    }

    class RepeatingTransactionViewHolder(view: View) : CoreViewHolder<RepeatingTransaction>(view) {
        private val red: Int = ContextCompat.getColor(view.context, R.color.mds_red_500)
        private val green: Int = ContextCompat.getColor(view.context, R.color.mds_green_500)
        private val primaryTextColor: Int = ContextCompat.getColor(view.context, android.R.color.primary_text_light)
        private val mDescriptionTextView: TextView = view.findViewById(R.id.transaction_description) as TextView
        private val mAmountTextView: TextView = view.findViewById(R.id.transaction_amount) as TextView
        private val mNextDateTextView: TextView = view.findViewById(R.id.transaction_date) as TextView
        private val mCategoryTextView: TextView = view.findViewById(R.id.transaction_category) as TextView
        private val mAccountTextView: TextView = view.findViewById(R.id.transaction_account) as TextView
        private val mNotesTextView: TextView = view.findViewById(R.id.transaction_notes) as TextView
        private val mIndicatorView: View = view.findViewById(R.id.transaction_indicator)

        override fun bindItem(item: RepeatingTransaction?) {
            val isWithdrawal = item?.isWithdrawal ?: false

            val amount = item?.amount
            val prependString = if (isWithdrawal) "-" else ""
            val amountText = prependString + amount?.asCurrency()

            val amountColor = if (isWithdrawal) red else primaryTextColor
            val indicatorColor = if (isWithdrawal) red else green

            val dateString = item?.nextDate?.asUiString().orEmpty()

            mDescriptionTextView.text = item?.description
            mAmountTextView.text = amountText
            mAmountTextView.setTextColor(amountColor)
            mIndicatorView.setBackgroundColor(indicatorColor)
            mNextDateTextView.text = String.format("Next date: %s", dateString)
            //TODO: mCategoryTextView.text = (item?.category)
            //TODO: mAccountTextView.text = String.format("Account: %s", item?.account)
            mNotesTextView.text = String.format("Notes: %s", item?.notes)
        }

        override fun onClick(v: View) {
            // Toggle notes
            mNotesTextView.visibility = if (mNextDateTextView.visibility == View.VISIBLE)
                View.GONE
            else
                View.VISIBLE
        }

        //TODO: Sort this out
//        override fun onLongClick(view: View): Boolean {
//            // Get current item and start action mode
//            mCursorAdapter.cursor.moveToPosition(adapterPosition)
//            startActionMode(RepeatingTransaction(mCursorAdapter.cursor))
//            return true
//        }
//
//        private fun startActionMode(transaction: RepeatingTransaction) {
//            // Don't fire if action mode is already being used
//            if (actionMode == null) {
//                // Start the CAB using the ActionMode.Callback already defined
//                actionMode = (mContext as AppCompatActivity).startSupportActionMode(actionModeCallback)
//                // Get name to set as title for action bar
//                // Need to subtract one to account for Header position
//                actionMode!!.title = transaction.description
//                // Get account ID to pass as tag.
//                actionMode!!.tag = transaction
//            }
//        }
    }
}
