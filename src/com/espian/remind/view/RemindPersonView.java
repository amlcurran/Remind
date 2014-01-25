package com.espian.remind.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.espian.remind.data.Person;
import com.espian.remind.data.PersonLoader;
import com.espian.remind.data.PhotoRequestCallback;

public class RemindPersonView extends FrameLayout implements PhotoRequestCallback {

    private final Paint mCirclePaint;
    private Person mPerson;
    private PersonLoader mLoader;
    public ImageView mImageView;

    public RemindPersonView(Context context) {
        this(context, null, 0);
    }

    public RemindPersonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RemindPersonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mCirclePaint = new Paint();
        mImageView = new ImageView(context);
        init();
    }

    private void init() {

    }

    public void setPerson(Person person) {
        mPerson = person;
        mLoader.loadPhoto(person, this);
    }

    public void setLoader(PersonLoader loader) {
        mLoader = loader;
    }

    @Override
    public void onPhotoLoaded(Bitmap bitmap) {
        BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
        mImageView.setImageDrawable(drawable);
    }
}
