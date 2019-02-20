package com.example.navigationdrawer.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.example.navigationdrawer.R;
import com.example.navigationdrawer.fragment.HomeFragment;
import com.example.navigationdrawer.fragment.JobsFragment;
import com.example.navigationdrawer.fragment.MessagesFragment;
import com.example.navigationdrawer.fragment.SettingsFragment;
import com.example.navigationdrawer.fragment.WeatherFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class NavigationActivityTest {

    @Rule
    public final ActivityTestRule<NavigationActivity> testRule = new ActivityTestRule<>(NavigationActivity.class);

    @Test
    public void homeTest() {
        HomeFragment fragment = new HomeFragment();
        testRule.getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment).commit();

        onView(withId(R.id.drawer_layout)).check(matches(hasDescendant(withText("Home"))));
    }

    @Test
    public void weatherTest() {
        WeatherFragment fragment = new WeatherFragment();
        testRule.getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment).commit();

        onView(withId(R.id.drawer_layout)).check(matches(hasDescendant(withText("Weather"))));
    }

    @Test
    public void jobsTest() {
        JobsFragment fragment = new JobsFragment();
        testRule.getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment).commit();

        onView(withId(R.id.drawer_layout)).check(matches(hasDescendant(withText("Jobs"))));
    }

    @Test
    public void messagesTest() {
        MessagesFragment fragment = new MessagesFragment();
        testRule.getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment).commit();

        onView(withId(R.id.drawer_layout)).check(matches(hasDescendant(withText("Messages"))));
    }

    @Test
    public void onBackPressed() {
        onView(withId(R.id.drawer_layout))
                .perform(click())
                .check(matches(isDisplayed()));
    }
}