package com.telkom.weedu;

/**
 * Created by Calysta M on 4/19/2017.
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.telkom.weedu.view.about.AboutActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AboutActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<AboutActivity> mActivityRule = new ActivityTestRule<>(
            AboutActivity.class);

    //Make sure application name is displayed
    //Make sure application version is displayed
    @Test
    public void testAboutActivity(){
        onView(withId(R.id.tv_application_name)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_application_version)).check(matches(isDisplayed()));

    }

}
