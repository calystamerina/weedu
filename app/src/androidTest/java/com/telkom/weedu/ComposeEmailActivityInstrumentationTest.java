package com.telkom.weedu;

/**
 * Created by Calysta M on 5/10/2017.
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.telkom.weedu.view.composeemail.ComposeEmailActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ComposeEmailActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<ComposeEmailActivity> mActivityRule = new ActivityTestRule<>(
            ComposeEmailActivity.class);

    @Test
    public void testComposeEmail() {
        String subject = "Ucapan";
        String email = "mcalysta@yahoo.com";
        String cc = "hahaha";
        String bcc = "yeyeye";
        String message = "Selamat anda telah mengirim email.";

//        onView(withId(R.id.tv_subject)).perform(typeText(subject)),closeSoftKeyboard();
//        onView(withId(R.id.edt_recipient)).perform(typeText(email)),closeSoftKeyboard();
//        onView(withId(R.id.edt_cc)).perform(typeText(cc)),closeSoftKeyboard();
//        onView(withId(R.id.edt_bcc)).perform(typeText(bcc)),closeSoftKeyboard();
//        onView(withId(R.id.edt_mail_body)).perform(typeText(message)),closeSoftKeyboard();

//send email success

        onView(withText("Confirmation")).check(matches(isDisplayed()));

    }
}

