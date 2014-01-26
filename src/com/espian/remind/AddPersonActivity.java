package com.espian.remind;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

import com.espian.remind.data.ContactContractPersonLoader;
import com.espian.remind.data.Person;
import com.espian.remind.data.PersonLoader;
import com.espian.remind.view.RemindPersonView;

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
        adapter = new PersonAdapter(this, personLoader);
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

    public static class PersonAdapter extends SimpleCursorAdapter implements AbsListView.RecyclerListener {

        public static final int[] IDS_TO = new int[]{android.R.id.text1};
        public static final String[] COLUMNS_FROM = new String[]{ContactsContract.Contacts.DISPLAY_NAME_PRIMARY};
        private final Context context;
        private final PersonLoader loader;

        public PersonAdapter(Context context, PersonLoader loader) {
            super(context, R.layout.item_person, null, COLUMNS_FROM, IDS_TO,
                    CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
            this.context = context;
            this.loader = loader;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_person, parent, false);
            }

            getCursor().moveToPosition(position);
            Person thisRow = Person.fromCursor(getCursor());
            convertView.setTag(thisRow);
            ((RemindPersonView) convertView).setPerson(loader, thisRow);

            return convertView;
        }


        @Override
        public void onMovedToScrapHeap(View view) {
            if (view.getTag() != null) {
                ((Person) view.getTag()).cancelPhotoLoad();
            }
        }
    }

}
