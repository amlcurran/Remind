package com.espian.remind;

import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.tester.android.view.TestMenuItem;

import static org.junit.Assert.assertEquals;
import static org.robolectric.Robolectric.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void test() {
        assert 2 == 1 + 1;
    }

    @Test
    public void testWhenAddPersonIsClicked_ANewPersonActivityIsStarted() {

        MainActivity activity = Robolectric.buildActivity(MainActivity.class)
                .create().start().resume().get();

        activity.onOptionsItemSelected(new TestMenuItem(R.id.menu_add_person));

        Intent intent = shadowOf(activity).getNextStartedActivity();
        assertEquals(shadowOf(intent).getIntentClass(), AddPersonActivity.class);
    }

}
