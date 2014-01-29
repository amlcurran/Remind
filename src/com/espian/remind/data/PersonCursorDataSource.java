package com.espian.remind.data;

public class PersonCursorDataSource extends CursorDataSource<Person> {

    @Override
    public Person getItem(int position) {
        boolean moved = getCursor().moveToPosition(position);
        return moved ? Person.fromCursor(getCursor()) : null;
    }

}
