package com.espian.remind;

import android.database.Cursor;

import com.espian.remind.data.Person;
import com.espian.remind.data.PersonLoader;
import com.espian.remind.view.RemindPersonView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class PersonAdapterTest {

    private PersonAdapter adapter;

    @Before
    public void setUp() {
        PersonLoader mockLoader = mock(PersonLoader.class);
        Person mockPerson = mock(Person.class);
        Cursor mockCursor = mock(Cursor.class);

        when(mockPerson.getName()).thenReturn("Person name");

        PersonAdapter realAdapter = new PersonAdapter(Robolectric.application, mockLoader);
        adapter = spy(realAdapter);

        doReturn(mockCursor).when(this.adapter).getCursor();
        doReturn(mockPerson).when(this.adapter).getItem(2);
    }

    @Test
    public void testWhenGetViewIsCalled_TheCorrectPersonIsSetOnTheView() {
        RemindPersonView view = (RemindPersonView) adapter.getView(2, null, null);

        assertEquals("Person name", view.labelView.getText());
    }

    @Test
    public void testWhenViewMovedToScrapHeap_PhotoLoadIsCancelled() {
        RemindPersonView mockRemindView = mock(RemindPersonView.class);
        when(mockRemindView.getTag()).thenReturn("ANYTHING");

        adapter.onMovedToScrapHeap(mockRemindView);

        verify(mockRemindView).cancelPhotoLoad();
    }

}
