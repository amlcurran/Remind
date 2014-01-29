package com.espian.remind;

import com.espian.remind.data.Person;
import com.espian.remind.data.PersonLoader;
import com.espian.remind.view.RemindPersonView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class PersonAdapterTest {

    private DataSourceAdapter<Person> adapter;
    private DataBinder<Person> mockDataBinder;
    private DataSource<Person> mockDataSource;
    private PersonLoader mockLoader;

    @Before
    public void setUp() {
        mockLoader = mock(PersonLoader.class);
        mockDataSource = mock(DataSource.class);
        mockDataBinder = mock(DataBinder.class);

        adapter = new DataSourceAdapter<Person>(mockDataSource, mockDataBinder);
    }

    @Test
    public void testWhenGetViewIsCalled_ThePersonIsBoundToTheView() {
        Person mockPerson = mock(Person.class);
        when(mockDataSource.getItem(2)).thenReturn(mockPerson);

        adapter.getView(2, null, null);

        verify(mockDataBinder).bindView(null, mockPerson);
    }

    @Test
    public void testWhenViewMovedToScrapHeap_PhotoLoadIsCancelled() {
        RemindPersonView mockRemindView = mock(RemindPersonView.class);

        adapter.onMovedToScrapHeap(mockRemindView);

        verify(mockDataBinder).unbindView(mockRemindView);
    }

}
