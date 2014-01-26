package com.espian.remind.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.espian.remind.R;
import com.espian.remind.data.Person;
import com.espian.remind.data.PersonLoader;
import com.espian.remind.data.PhotoRequestCallback;
import com.espian.utils.LoadHideHelper;

public class RemindPersonView extends FrameLayout implements PhotoRequestCallback {

    private final Paint circlePaint;
    LoadHideHelper photoHideHelper;
    private Person person;
    public final ImageView photoImageView;

    public RemindPersonView(Context context) {
        this(context, null, 0);
    }

    public RemindPersonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RemindPersonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        // Set up UI
        LayoutInflater.from(context).inflate(R.layout.view_remind_person, this, true);
        photoImageView = (ImageView) findViewById(R.id.remind_photo);
        photoHideHelper = new LoadHideHelper(photoImageView);

        // Paints and things like that
        circlePaint = new Paint();

        // Common init
        init();
    }

    private void init() {

    }

    public void setPerson(PersonLoader loader, Person person) {
        this.person = person;
        loader.loadPhoto(person, this);
    }

    @Override
    public void onPhotoLoaded(Bitmap bitmap) {
        if (bitmap != null) {
            BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
            photoImageView.setImageDrawable(drawable);
            photoHideHelper.show();
        }
    }
}
