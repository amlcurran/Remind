package com.espian.remind.view;

import android.view.LayoutInflater;
import android.view.View;

import com.espian.remind.R;
import com.espian.remind.data.Person;
import com.espian.remind.data.PersonLoader;
import com.espian.remind.widgets.RemindPersonView;

public class RemindPersonDataBinder implements DataBinder<Person> {

    private final LayoutInflater layoutInflater;
    private final PersonLoader loader;

    public RemindPersonDataBinder(LayoutInflater layoutInflater, PersonLoader loader) {
        this.layoutInflater = layoutInflater;
        this.loader = loader;
    }

    @Override
    public View bindView(View recycledView, Person data) {
        if (recycledView == null) {
            recycledView = layoutInflater.inflate(R.layout.item_person, null);
        }

        recycledView.setTag(data);
        ((RemindPersonView) recycledView).setPerson(loader, data);
        return recycledView;
    }

    @Override
    public void unbindView(View view) {
        ((RemindPersonView) view).cancelPhotoLoad();
    }

}
