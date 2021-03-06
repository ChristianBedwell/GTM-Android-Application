package com.example.navigationdrawer.activity;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.fragment.RegisterFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public final ActivityTestRule<MainActivity> testRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void signUpTest () {
        LoginFragment loginFragment = new LoginFragment();
        testRule.getActivity().getSupportFragmentManager().beginTransaction()
            .add(R.id.fragment_frame, loginFragment).commit();
        onView(withId(R.id.bRegisterLink)).perform(click());
    }

    @Test
    public void signInTest () {
        RegisterFragment registerFragment = new RegisterFragment();
        testRule.getActivity().getSupportFragmentManager().beginTransaction()
            .add(R.id.fragment_frame, registerFragment).commit();
        onView(withId(R.id.tvLoginLink)).perform(click());
    }

    @Test
    public void registerTest () {
        RegisterFragment registerFragment = new RegisterFragment();
        testRule.getActivity().getSupportFragmentManager().beginTransaction()
            .add(R.id.fragment_frame, registerFragment).commit();

        onView(withId(R.id.etFullName)).perform(clearText(), typeText("Hunter Bedwell"));
        onView(withId(R.id.etEmail)).perform(clearText(), typeText("bedwellhb@gmail.com"));
        onView(withId(R.id.etPassword)).perform(clearText(), typeText("test123"));
        onView(withId(R.id.bRegister)).perform(click());
    }

    @Test
    public void loginTest() {
        LoginFragment loginFragment = new LoginFragment();
        testRule.getActivity().getSupportFragmentManager().beginTransaction()
            .add(R.id.fragment_frame, loginFragment).commit();

        onView(withId(R.id.etEmail)).perform(clearText(), typeText("bedwellhb@gmail.com"));
        onView(withId(R.id.etPassword)).perform(clearText(), typeText("test123"));
        onView(withId(R.id.bLogin)).perform(click());
    }
}
