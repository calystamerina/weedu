package com.telkom.weedu;

/**
 * Created by Calysta M on 4/19/2017.
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.telkom.weedu.view.topup.TopUpActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TopUpActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<TopUpActivity> mActivityRule = new ActivityTestRule<>(
            TopUpActivity.class);

    //Make sure the list is displayed
    @Test
    public void testTopUpActivity(){
        onView(withId(R.id.rv_how_to_top_up)).check(matches(isDisplayed()));

    }

}
