package com.espian.remind;

import android.view.View;

public interface DataBinder<T> {
    public View bindView(View recycledView, T data);

    void unbindView(View view);
}
