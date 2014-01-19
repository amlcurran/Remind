package com.espian.remind;

import android.app.LoaderManager;
import android.widget.SimpleCursorAdapter;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class AddPersonActivityTest {

    @Test
    public void whenActivityIsCreated_TheListIsBackedByACursorAdapter() {
        AddPersonActivity activity = Robolectric.buildActivity(AddPersonActivity.class)
                .create().get();
        assertEquals(SimpleCursorAdapter.class, activity.getListAdapter().getClass());
    }

    @Test
    @Ignore
    public void whenActivityIsCreated_TheLoaderManagerIsInited() {
        ActivityController<AddPersonActivity> activityController = Robolectric.buildActivity(AddPersonActivity.class);
        AddPersonActivity spyActivity = spy(activityController.get());
        LoaderManager mockLoaderManager = mock(LoaderManager.class);
        doReturn(mockLoaderManager).when(spyActivity).getLoaderManager();
        activityController.create();
        verify(mockLoaderManager).initLoader(AddPersonActivity.LOADER_CONTACTS, null, activityController.get());
    }

}
