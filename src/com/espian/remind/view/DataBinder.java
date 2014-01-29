package com.espian.remind.view;

import android.view.View;

public interface DataBinder<T> {
    public View bindView(View recycledView, T data);

    void unbindView(View view);
}
