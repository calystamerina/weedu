package com.telkom.weedu;

/**
 * Created by Calysta M on 4/17/2017.
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.telkom.weedu.view.login.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityInstrumentationTest {



    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(
            LoginActivity.class); //this should be referring LoginActivityClass not main

    //Add valid username
    //Add valid password
    //Click on button login
    //Make sure label recommendation is displayed after login to the application
    //Make sure main page is displayed after login to the application
    @Test
    public void testLoginSuccess(){
        String username = "ganiamri";
        String password = "Telkom12345";

        onView(withId(R.id.edt_username)).perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.edt_password)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        //onView(withId(R.id.btn_login)).check(matches(not(isDisplayed())));
        onView(withText(R.string.label_recommendations)).check(matches(isDisplayed()));
        onView(withId(R.id.vp_main)).check(matches(isDisplayed()));

    }

    //Add invalid username
    //Add valid password
    //Click on button login
    //Make sure progress bar is not displayed
    //Make sure login button is displayed
    //Make sure login button facebook is displayed
    @Test
    public void testLoginFailed(){
        String user = "calysta";
        String pass = "calysta31";
        String errorMessage = "";

        onView(withId(R.id.edt_username)).perform(typeText(user), closeSoftKeyboard());
        onView(withId(R.id.edt_password)).perform(typeText(pass), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        onView(withId(R.id.pb_indicator)).check(matches(not(isDisplayed())));
        onView(withId(R.id.btn_login)).check(matches((isDisplayed())));
        onView(withId(R.id.btn_login_facebook)).check(matches(isDisplayed()));


        // check when user insert username and password then button login clicked
        // app will show progress indicator and hide the login button
    }

}
