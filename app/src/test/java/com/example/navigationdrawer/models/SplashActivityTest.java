/*
package com.example.navigationdrawer.models;

import android.content.Intent;

import com.example.navigationdrawer.activity.MainActivity;
import com.example.navigationdrawer.activity.SplashActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.shadows.ShadowLooper;

import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class SplashActivityTest {

    @Test
    public void test() {
        ActivityController<SplashActivity> controller = Robolectric.buildActivity(SplashActivity.class).create().start();
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks();

        SplashActivity splashScreenActivity = controller.get();
        Intent expectedIntent = new Intent(splashScreenActivity, MainActivity.class);

        assertEquals(expectedIntent,shadowOf(splashScreenActivity).getNextStartedActivity());
    }
}
*/
