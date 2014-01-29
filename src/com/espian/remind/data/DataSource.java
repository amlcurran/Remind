package com.espian.remind.data;

public interface DataSource<T> {
    int getCount();

    T getItem(int position);
}
