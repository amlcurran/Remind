package com.espian.remind.widgets;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.espian.remind.data.Person;
import com.espian.remind.data.PersonLoader;
import com.espian.utils.LoadHideHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class RemindPersonViewTest {

    private RemindPersonView remindView;
    private PersonLoader mockPersonLoader;

    @Before
    public void setUp() {
        mockPersonLoader = mock(PersonLoader.class);
        remindView = new RemindPersonView(Robolectric.application);
        remindView.photoHideHelper = mock(LoadHideHelper.class);
    }

    @Test
    public void testSetPerson_InitiatesACallToGetThePersonsPhoto() {
        Person mockPerson = mock(Person.class);

        remindView.setPerson(mockPersonLoader, mockPerson);

        verify(mockPersonLoader).loadPhoto(mockPerson, remindView);
    }

    @Test
    public void testWhenAPersonPhotoIsLoaded_ItIsSetOnTheImageView() {
        Bitmap expectedBitmap = Bitmap.createBitmap(12, 12, Bitmap.Config.ALPHA_8);

        remindView.onPhotoLoaded(expectedBitmap);

        Drawable setDrawable = remindView.photoImageView.getDrawable();
        assertEquals("Image set isn't a bitmap drawable", BitmapDrawable.class, setDrawable.getClass());
        assertEquals("Image set isn't the correct one", expectedBitmap, ((BitmapDrawable) setDrawable).getBitmap());
    }

}
