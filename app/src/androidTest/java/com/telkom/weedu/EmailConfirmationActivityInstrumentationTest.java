package com.telkom.weedu;

/**
 * Created by Calysta M on 4/19/2017.
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.telkom.weedu.view.emailconfirmation.EmailConfirmationActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EmailConfirmationActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<EmailConfirmationActivity> mActivityRule = new ActivityTestRule<>(
            EmailConfirmationActivity.class);


    @Test
    public void testEmailEmpty(){
        onView(withId(R.id.btn_edumail)).perform(click());
        onView(withId(R.id.edt_email)).check(matches(hasErrorText("This Field cannot be empty")));

    }


}
