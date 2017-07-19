package com.androidessence.cashcaretaker.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.view.ActionMode
import android.view.*
import android.widget.TextView
import com.adammcneilly.CoreRecyclerViewAdapter
import com.adammcneilly.CoreViewHolder
import com.androidessence.cashcaretaker.R
import com.androidessence.cashcaretaker.activities.TransactionsActivity
import com.androidessence.cashcaretaker.core.CoreActivity
import com.androidessence.cashcaretaker.data.CCContract
import com.androidessence.cashcaretaker.dataTransferObjects.Account
import com.androidessence.utility.asCurrency

/**
 * Adapter that displays all accounts.
 */
class AccountAdapterR(items: MutableList<Account> = ArrayList(), val context: Context) : CoreRecyclerViewAdapter<Account, AccountAdapterR.AccountViewHolder>(items) {

    private var actionMode: ActionMode? = null

    private val actionModeCallback = object : ActionMode.Callback {
        // Called when the action mode is created; startActionMode() was called
        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            // Inflate a menu resource providing context menu items
            mode.menuInflater.inflate(R.menu.account_context_menu, menu)
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
                R.id.action_delete_account -> {
                    // The account that was selected is passed as the tag
                    // for the action mode.
                    showAccountDeleteAlertDialog(actionMode?.tag as Account)
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

    private fun showAccountDeleteAlertDialog(account: Account) {
        AlertDialog.Builder(context)
                .setTitle("Delete Account")
                .setMessage("Are you sure you want to delete " + account.name + "?")
                .setPositiveButton("Yes") { dialog, _ ->
                    //TODO: Handle the update with the new activity
                    context.contentResolver.delete(
                            CCContract.AccountEntry.CONTENT_URI,
                            CCContract.AccountEntry._ID + " = ?",
                            arrayOf(account.identifier.toString())
                    )
                    (context as OnAccountDeletedListener).onAccountDeleted(account.identifier)
                    dialog.dismiss()
                }
                .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
                .create().show()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AccountViewHolder {
        return createViewHolder(::AccountViewHolder, R.layout.list_item_account, parent)
    }

    private fun startTransactionActivity(account: Account) {
        // Create intent
        val transactionsActivity = Intent(context, TransactionsActivity::class.java)

        // Build and set arguments.
        val args = Bundle()
        args.putParcelable(TransactionsActivity.ARG_ACCOUNT, account)
        transactionsActivity.putExtras(args)

        // Start activity
        context.startActivity(transactionsActivity)
    }

    class AccountViewHolder(view: View) : CoreViewHolder<Account>(view) {
        private val red: Int = ContextCompat.getColor(view.context, R.color.mds_red_500)
        private val primaryTextColor: Int = ContextCompat.getColor(view.context, android.R.color.primary_text_light)
        private val nameTextView: TextView = view.findViewById(R.id.account_name) as TextView
        private val balanceTextView: TextView = view.findViewById(R.id.account_balance) as TextView

        override fun bindItem(item: Account?) {
            nameTextView.text = item?.name

            val balance = item?.balance ?: 0.0
            balanceTextView.text = balance.asCurrency()

            val textColor = if (balance < 0) red else primaryTextColor
            balanceTextView.setTextColor(textColor)
        }

        //TODO: Sort this out
//        fun startActionMode(account: Account) {
//            // Don't fire if the action mode is already active.
//            if (actionMode == null) {
//                // Start the CAB using the ActionMode.Callback already defined
//                actionMode = (itemView.context as CoreActivity).startSupportActionMode(actionModeCallback)
//                // Get name to set as title for action bar
//                actionMode?.title = account.name
//                // Get account ID to pass as tag.
//                actionMode?.tag = account
//            }
//        }
//
//        override fun onClick(v: View) {
//            startTransactionActivity(items[adapterPosition])
//        }
//
//        override fun onLongClick(v: View?): Boolean {
//            startActionMode(items[adapterPosition])
//            return true
//        }
    }

    interface OnAccountDeletedListener {
        fun onAccountDeleted(id: Long)
    }
}
