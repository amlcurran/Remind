package com.espian.remind;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;

import com.espian.remind.data.ContactContractPersonLoader;

import java.util.concurrent.Executors;

public class TestDataActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String[] PROJECTION = {
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.LOOKUP_KEY,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY };

    SimpleCursorAdapter adapter;
    ContactContractPersonLoader personLoader;
    AdapterView adapterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        personLoader = new ContactContractPersonLoader(this, Executors.newCachedThreadPool());
        adapter = new AddPersonActivity.PersonAdapter(this, personLoader);
        adapterView = (AdapterView) findViewById(R.id.grid_view);
        adapterView.setAdapter(adapter);

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
    public void onLoadFinished(Loader<Cursor> objectLoader, Cursor cursor) {
        adapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> objectLoader) {

    }
}
