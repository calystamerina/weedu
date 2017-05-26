package com.telkom.weedu;

/**
 * Created by Calysta M on 4/19/2017.
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.telkom.weedu.view.contactus.ContactUsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ContactUsActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<ContactUsActivity> mActivityRule = new ActivityTestRule<>(
            ContactUsActivity.class);

    //Make sure splash screen is displayed
    //Make sure splash logo is displayed
    @Test
    public void testContactUsActivity(){
        String subject = "Feedback";
        String message = "Aplikasi yang sangat baik!";
        onView(withId(R.id.edt_subject)).perform(typeText(subject), closeSoftKeyboard());
        onView(withId(R.id.edt_message)).perform(typeText(message), closeSoftKeyboard());
        onView(withId(R.id.btn_send_message)).perform(click());

        onView(withText("Thank you, Your message has been sent")).check(matches(isDisplayed()));

    }

    @Test
    public void testContactUsActivityEmpty(){
        onView(withId(R.id.btn_send_message)).perform(click());

        onView(withId(R.id.edt_subject)).check(matches(hasErrorText("This Field cannot be empty")));
        onView(withId(R.id.edt_message)).check(matches(hasErrorText("This Field cannot be empty")));

    }

    @Test
    public void testContactUsActivityMessageEmpty(){
        String subject = "Feedback";
        String message = "Aplikasi yang sangat baik!";
        onView(withId(R.id.edt_subject)).perform(typeText(subject), closeSoftKeyboard());
        onView(withId(R.id.btn_send_message)).perform(click());

        onView(withId(R.id.edt_message)).check(matches(hasErrorText("This Field cannot be empty")));

    }

    @Test
    public void testContactUsActivitySubjectEmpty(){
        String subject = "Feedback";
        String message = "Aplikasi yang sangat baik!";

        onView(withId(R.id.edt_message)).perform(typeText(message), closeSoftKeyboard());
        onView(withId(R.id.btn_send_message)).perform(click());

        onView(withId(R.id.edt_subject)).check(matches(hasErrorText("This Field cannot be empty")));

    }



}

