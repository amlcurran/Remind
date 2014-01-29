package com.espian.remind;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.AbsListView;

import com.espian.remind.data.ContactContractPersonLoader;

import java.util.concurrent.Executors;

public class AddPersonActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String[] PROJECTION = {
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.LOOKUP_KEY,
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY };
    public static final int LOADER_CONTACTS = 0;

    PersonAdapter adapter;
    ContactContractPersonLoader personLoader;
    AbsListView adapterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        personLoader = new ContactContractPersonLoader(this, Executors.newSingleThreadExecutor());
        adapter = new PersonAdapter(this, personLoader, null, null);
        adapterView = (AbsListView) findViewById(R.id.grid_view);
        adapterView.setAdapter(adapter);
        adapterView.setRecyclerListener(adapter);

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI,
                PROJECTION,
                ContactsContract.Contacts.HAS_PHONE_NUMBER + "<>?",
                new String[] { "0" },
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        adapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        adapter.swapCursor(null);
    }

}
