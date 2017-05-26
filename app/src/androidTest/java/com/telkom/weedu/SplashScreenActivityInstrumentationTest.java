package com.telkom.weedu;

/**
 * Created by Calysta M on 4/17/2017.
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.telkom.weedu.view.splash.SplashScreenActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SplashScreenActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<SplashScreenActivity> mActivityRule = new ActivityTestRule<>(
            SplashScreenActivity.class);

    //Make sure splash screen is displayed
    //Make sure splash logo is displayed
    @Test
    public void testSplashScreenActivity(){
        onView(withId(R.id.activity_splash_screen)).check(matches(isDisplayed()));
        onView(withId(R.id.img_splash_logo)).check(matches(isDisplayed()));

    }

}

