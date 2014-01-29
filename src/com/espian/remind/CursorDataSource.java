package com.espian.remind;

import android.database.Cursor;

public abstract class CursorDataSource<T> implements DataSource<T> {

    private Cursor cursor;

    @Override
    public int getCount() {
        return cursor == null ? 0 : cursor.getCount();
    }

    @Override
    public abstract T getItem(int position);

    public Cursor getCursor() {
        return cursor;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }
}
