package com.espian.remind.data;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;

import java.io.InputStream;
import java.util.concurrent.Executor;

public class ContactContractPersonLoader implements PersonLoader {

    public static final String[] PHOTO_PROJECTION = new String[]{ContactsContract.CommonDataKinds.Photo._ID, ContactsContract.CommonDataKinds.Photo.PHOTO_THUMBNAIL_URI};
    public static final String PHOTO_SELECTION = ContactsContract.Data._ID + "=?";
    private Activity activity;
    private Executor executor;

    public ContactContractPersonLoader(Activity activity, Executor executor) {
        this.activity = activity;
        this.executor = executor;
    }

    @Override
    public void loadPhoto(final Person person, PhotoRequestCallback callback) {
        executor.execute(createPhotoRequestRunnable(activity, person, callback));
    }

    private static Runnable createPhotoRequestRunnable(final Activity activity, final Person person, final PhotoRequestCallback callback) {
        return new Runnable() {
            @Override
            public void run() {
                InputStream stream = ContactsContract.Contacts.openContactPhotoInputStream(activity.getContentResolver(),
                        person.getUri(), true);
                final Bitmap bitmap = BitmapFactory.decodeStream(stream);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callback.onPhotoLoaded(bitmap);
                    }
                });
            }
        };
    }

}
