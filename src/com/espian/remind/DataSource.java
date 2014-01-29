package com.espian.remind;

public interface DataSource<T> {
    int getCount();

    T getItem(int position);
}
