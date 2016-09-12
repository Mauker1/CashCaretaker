package com.androidessence.cashcaretaker;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.wearable.view.DismissOverlayView;
import android.support.wearable.view.WatchViewStub;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class AccountsActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor>{
    private AccountCursorAdapter accountCursorAdapter;
    private static final int ACCOUNT_LOADER = 0;

    private DismissOverlayView dismissOverlay;
    private GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        accountCursorAdapter = new AccountCursorAdapter(this);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                setupListView();
            }
        });

        setupDismissOverlay();
    }

    private void setupListView() {
        ListView listView = (ListView) findViewById(R.id.account_list_view);
        listView.setAdapter(accountCursorAdapter);

        // Inflate headerview
        View headerView = getLayoutInflater().inflate(R.layout.list_item_account_header, null, false);
        listView.setHeaderDividersEnabled(true);
        listView.addHeaderView(headerView);

        // Footer
        View footerView = getLayoutInflater().inflate(R.layout.list_item_account_footer, null, false);
        listView.setFooterDividersEnabled(true);
        listView.addFooterView(footerView);

        //TODO: test
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dismissOverlay.show();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLoaderManager().initLoader(ACCOUNT_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch(id) {
            case ACCOUNT_LOADER:
                return new CursorLoader(
                        this,
                        CCContract.AccountEntry.CONTENT_URI,
                        AccountCursorAdapter.ACCOUNT_COLUMNS,
                        null,
                        null,
                        CCContract.AccountEntry.COLUMN_NAME + " ASC"
                );
            default:
                throw new UnsupportedOperationException("Unknown loader id: " + id);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch(loader.getId()) {
            case ACCOUNT_LOADER:
                accountCursorAdapter.swapCursor(data);
                break;
            default:
                throw new UnsupportedOperationException("Unknown loader id: " + loader.getId());
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        switch(loader.getId()) {
            case ACCOUNT_LOADER:
                accountCursorAdapter.swapCursor(null);
                break;
            default:
                throw new UnsupportedOperationException("Unknown loader id: " + loader.getId());
        }
    }

    private void setupDismissOverlay() {
        dismissOverlay = (DismissOverlayView) findViewById(R.id.dismiss_overlay);
        // dismissOverlay.setIntroText(android.R.string.long_press_intro);
        // dismissOverlay.showIntroIfNecessary();

        detector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public void onLongPress(MotionEvent e) {
                dismissOverlay.show();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event) || super.onTouchEvent(event);
    }
}
