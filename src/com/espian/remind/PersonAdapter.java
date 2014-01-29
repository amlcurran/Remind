package com.espian.remind;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

import com.espian.remind.data.Person;

public class PersonAdapter extends SimpleCursorAdapter implements AbsListView.RecyclerListener {

    public static final int[] IDS_TO = new int[]{android.R.id.text1};
    public static final String[] COLUMNS_FROM = new String[]{ContactsContract.Contacts.DISPLAY_NAME_PRIMARY};
    private final DataSource<Person> dataSource;
    private final DataBinder<Person> dataBinder;

    public PersonAdapter(Context context, DataSource<Person> dataSource, DataBinder<Person> dataBinder) {
        super(context, R.layout.item_person, null, COLUMNS_FROM, IDS_TO,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        this.dataSource = dataSource;
        this.dataBinder = dataBinder;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return dataBinder.bindView(convertView, dataSource.getItem(position));
    }

    @Override
    public long getItemId(int position) {
        return ((Person) getItem(position)).getId();
    }

    @Override
    public int getCount() {
        return dataSource.getCount();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.getItem(position);
    }

    @Override
    public void onMovedToScrapHeap(View view) {
        dataBinder.unbindView(view);
    }
}
