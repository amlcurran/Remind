package com.espian.remind.data;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;

import java.io.InputStream;

public class ContactContractPersonLoader implements PersonLoader {

    public static final String[] PHOTO_PROJECTION = new String[]{ContactsContract.CommonDataKinds.Photo._ID, ContactsContract.CommonDataKinds.Photo.PHOTO_THUMBNAIL_URI};
    public static final String PHOTO_SELECTION = ContactsContract.Data._ID + "=?";
    private Activity activity;

    public ContactContractPersonLoader(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void loadPhoto(final Person person, PhotoRequestCallback callback) {
        InputStream stream = ContactsContract.Contacts.openContactPhotoInputStream(activity.getContentResolver(),
                person.getUri());
        Bitmap bitmap = BitmapFactory.decodeStream(stream);
        callback.onPhotoLoaded(bitmap);
    }

}
