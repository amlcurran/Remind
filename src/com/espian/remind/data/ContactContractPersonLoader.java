package com.espian.remind.data;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;

import com.espian.remind.R;
import com.espian.remind.view.CircleImageView;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;

public class ContactContractPersonLoader implements PersonLoader {

    private Activity activity;
    private ExecutorService executor;

    public ContactContractPersonLoader(Activity activity, ExecutorService executor) {
        this.activity = activity;
        this.executor = executor;
    }

    @Override
    public void loadPhoto(final Person person, PhotoRequestCallback callback) {
        executor.submit(createPhotoRequestRunnable(activity, person, callback));
    }

    private static Runnable createPhotoRequestRunnable(final Activity activity, final Person person, final PhotoRequestCallback callback) {
        return new Runnable() {
            @Override
            public void run() {
                InputStream stream = ContactsContract.Contacts.openContactPhotoInputStream(activity.getContentResolver(),
                        person.getUri(), true);
                int dimen = (int) activity.getResources().getDimension(R.dimen.grid_circle_diameter);
                final Bitmap bitmap = CircleImageView.getCroppedBitmap(BitmapFactory.decodeStream(stream), dimen);
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
