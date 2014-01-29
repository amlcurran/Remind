package com.espian.remind;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

import com.espian.remind.data.Person;
import com.espian.remind.data.PersonLoader;
import com.espian.remind.view.RemindPersonView;

public class PersonAdapter extends SimpleCursorAdapter implements AbsListView.RecyclerListener {

    public static final int[] IDS_TO = new int[]{android.R.id.text1};
    public static final String[] COLUMNS_FROM = new String[]{ContactsContract.Contacts.DISPLAY_NAME_PRIMARY};
    private final Context context;
    private final PersonLoader loader;
    private final DataSource<Person> personSource;
    private final DataBinder<Person> dataBinder;

    public PersonAdapter(Context context, PersonLoader loader, DataSource<Person> dataSource, DataBinder<Person> dataBinder) {
        super(context, R.layout.item_person, null, COLUMNS_FROM, IDS_TO,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        this.context = context;
        this.loader = loader;
        this.personSource = dataSource;
        this.dataBinder = dataBinder;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_person, parent, false);
        }

        Person thisRow = (Person) getItem(position);
        convertView.setTag(thisRow);
        ((RemindPersonView) convertView).setPerson(loader, thisRow);

        return convertView;
    }

    @Override
    public int getCount() {
        return personSource.getCount();
    }

    @Override
    public Object getItem(int position) {
        return personSource.getItem(position);
    }

    @Override
    public void onMovedToScrapHeap(View view) {
        if (view.getTag() != null) {
            ((RemindPersonView) view).cancelPhotoLoad();
        }
    }
}
