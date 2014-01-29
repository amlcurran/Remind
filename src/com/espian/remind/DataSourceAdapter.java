package com.espian.remind;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

public class DataSourceAdapter<T> extends BaseAdapter implements AbsListView.RecyclerListener {

    private final DataSource<T> source;
    private final DataBinder<T> binder;

    public DataSourceAdapter(DataSource<T> source, DataBinder<T> binder) {
        this.source = source;
        this.binder = binder;
    }

    @Override
    public int getCount() {
        return source.getCount();
    }

    @Override
    public T getItem(int i) {
        return source.getItem(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        return binder.bindView(view, getItem(position));
    }

    @Override
    public void onMovedToScrapHeap(View view) {
        binder.unbindView(view);
    }
}
