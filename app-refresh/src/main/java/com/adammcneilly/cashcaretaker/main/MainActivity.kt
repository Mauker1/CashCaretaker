package com.adammcneilly.cashcaretaker.main

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.adammcneilly.cashcaretaker.R
import com.adammcneilly.cashcaretaker.account.AccountFragment
import com.adammcneilly.cashcaretaker.addaccount.AddAccountDialog
import com.adammcneilly.cashcaretaker.transaction.TransactionFragment
import timber.log.Timber


class MainActivity : AppCompatActivity(), MainController, FragmentManager.OnBackStackChangedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportFragmentManager.addOnBackStackChangedListener(this)

        supportFragmentManager.beginTransaction()
                .add(R.id.container, AccountFragment.newInstance(), AccountFragment.FRAGMENT_NAME)
                .addToBackStack(AccountFragment.FRAGMENT_NAME)
                .commit()
    }

    override fun navigateToAddAccount() {
        val dialog = AddAccountDialog()
        dialog.show(supportFragmentManager, AddAccountDialog.FRAGMENT_NAME)
    }

    override fun onAccountInserted() {
        Timber.d("onAccountInserted")
        supportFragmentManager.popBackStackImmediate(AccountFragment.FRAGMENT_NAME, 0)
    }

    override fun showTransactions(accountName: String) {
        supportFragmentManager.beginTransaction()
                .add(R.id.container, TransactionFragment.newInstance(accountName), TransactionFragment.FRAGMENT_NAME)
                .addToBackStack(TransactionFragment.FRAGMENT_NAME)
                .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val backStackCount = supportFragmentManager.backStackEntryCount

        if (backStackCount > 1) {
            supportFragmentManager.popBackStackImmediate()
        } else {
            finish()
        }
    }

    override fun onBackStackChanged() {
        val shouldShowUp = supportFragmentManager.backStackEntryCount > 1
        supportActionBar?.setDisplayHomeAsUpEnabled(shouldShowUp)

        when (supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1).name) {
            AccountFragment.FRAGMENT_NAME -> supportActionBar?.setTitle(R.string.app_name)
        }
    }
}
