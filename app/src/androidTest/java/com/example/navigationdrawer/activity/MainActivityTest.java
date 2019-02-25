package com.example.navigationdrawer.activity;

import android.content.Intent;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.fragment.LoginFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import org.junit.Test;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends AppCompatActivity {

    @Rule
    public final ActivityTestRule<MainActivity> testRule
            = new ActivityTestRule<>(MainActivity.class, false, false);

    private static final Intent intent = new Intent(MainActivity.class);

    @Before
    public void setup() {
        testRule.launchActivity(intent);
    }

    @Test
    public void signUpTest () {
        LoginFragment loginFragment = new LoginFragment();
        testRule.getActivity().getSupportFragmentManager().beginTransaction();
        onView(withId(R.id.bRegister)).perform(click());
        onView(withId(R.id.fragment_register).check(matches(isDisplayed())));
    }

    @Test
    public void signInTest () {

    }
}