package com.espian.remind.data;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

public class Person {
    private int id;

    public static Person fromCursor(Cursor cursor) {
        Person person = new Person();
        person.id = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));
        return person;
    }

    public int getId() {
        return id;
    }

    public Uri getUri() {
        return ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id);
    }

    public void setId(int id) {
        this.id = id;
    }

}
