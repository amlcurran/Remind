package com.espian.remind;

import android.app.LoaderManager;
import android.database.Cursor;

import com.espian.remind.view.DataSourceAdapter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class AddPersonActivityTest {

    @Mock
    private static LoaderManager mockLoaderManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void whenActivityIsCreated_TheListIsBackedByACursorAdapter() {
        AddPersonActivity activity = Robolectric.buildActivity(AddPersonActivity.class)
                .create().get();
        assertEquals(DataSourceAdapter.class, activity.adapterView.getAdapter().getClass());
    }

    @Test
    public void whenActivityIsCreated_TheLoaderManagerIsInited() {
        ActivityController<TestableAddPersonActivity> activityController = Robolectric.buildActivity(TestableAddPersonActivity.class);
        AddPersonActivity activity = activityController.get();
        activityController.create();
        verify(mockLoaderManager).initLoader(AddPersonActivity.LOADER_CONTACTS, null, activity);
    }

    @Test
    public void whenTheCursorHasFinishedLoading_TheAdaptersCursorIsSwappedOut() {
        Cursor swappedCursor = mock(Cursor.class);
        AddPersonActivity activity = Robolectric.buildActivity(AddPersonActivity.class).create().get();
        activity.onLoadFinished(null, swappedCursor);

        Cursor actualResult = activity.dataSource.getCursor();

        assertEquals("Cursor result is not swapped in", swappedCursor, actualResult);
    }

    public static class TestableAddPersonActivity extends AddPersonActivity {

        @Override
        public LoaderManager getLoaderManager() {
            return mockLoaderManager;
        }
    }

}
