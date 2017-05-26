package com.telkom.weedu;

/**
 * Created by Calysta M on 4/18/2017.
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.telkom.weedu.view.register.RegisterActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegisterActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<RegisterActivity> mActivityRule = new ActivityTestRule<>(
            RegisterActivity.class);

    //Click on full name and add fullname
    //Click on phonenumber and add phonenumber
    //Click on Date of Birth
    //
    //
    //
    //Click on email and add a valid email
    //Click on password and add a valid password

    @Test
    public void testRegisterActivitySuccess(){
        String fullname = "Calysta Merina";
        String phonenumber = "081249455327";
        String dateofbirth = "31-05-1995";
        String email = "mcalysta@yahoo.com";
        String passwd = "Calysta31051995";

        onView(withId(R.id.edt_fullname)).perform(typeText(fullname), closeSoftKeyboard());
        onView(withId(R.id.edt_phonenumber)).perform(typeText(phonenumber), closeSoftKeyboard());
        onView(withId(R.id.edt_dateofbirth)).perform(replaceText(dateofbirth));
        onView(withId(R.id.edt_email)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.edt_password)).perform(typeText(passwd), closeSoftKeyboard());
        onView(withId(R.id.cb_toc)).perform(click());
        onView(withId(R.id.btn_register)).perform(click());

        onView(withText("Confirmation")).check(matches(isDisplayed()));

    }

    @Test
    public void testRegisterActivityEmailFailed(){
        String fullname = "Calysta Merina";
        String phonenumber = "081249455327";
        String dateofbirth = "31-05-1995";
        String email = "calysta";
        String passwd = "Calysta31051995";

        onView(withId(R.id.edt_fullname)).perform(typeText(fullname), closeSoftKeyboard());
        onView(withId(R.id.edt_phonenumber)).perform(typeText(phonenumber), closeSoftKeyboard());
        onView(withId(R.id.edt_dateofbirth)).perform(replaceText(dateofbirth));
        onView(withId(R.id.edt_email)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.edt_password)).perform(typeText(passwd), closeSoftKeyboard());
        onView(withId(R.id.cb_toc)).perform(click());
        onView(withId(R.id.btn_register)).perform(click());

        onView(withId(R.id.edt_email)).check(matches(hasErrorText("Please input a valid email address")));

    }

    @Test
    public void testRegisterActivityPasswordFailed(){
        String fullname = "Calysta Merina";
        String phonenumber = "081249455327";
        String dateofbirth = "31-05-1995";
        String email = "mcalysta@yahoo.com";
        String passwd = "calystamerina";

        onView(withId(R.id.edt_fullname)).perform(typeText(fullname), closeSoftKeyboard());
        onView(withId(R.id.edt_phonenumber)).perform(typeText(phonenumber), closeSoftKeyboard());
        onView(withId(R.id.edt_dateofbirth)).perform(replaceText(dateofbirth));
        onView(withId(R.id.edt_email)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.edt_password)).perform(typeText(passwd), closeSoftKeyboard());
        onView(withId(R.id.cb_toc)).perform(click());
        onView(withId(R.id.btn_register)).perform(click());

        onView(withId(R.id.edt_password)).check(matches(hasErrorText("Password must have at least an uppercase character, a number and 8 character length minimum")));

    }

    @Test
    public void testRegisterActivityTermAndConditionisNotClicked(){
        String fullname = "Calysta Merina";
        String phonenumber = "081249455327";
        String dateofbirth = "31-05-1995";
        String email = "mcalysta@yahoo.com";
        String passwd = "Calysta31051995";

        onView(withId(R.id.edt_fullname)).perform(typeText(fullname), closeSoftKeyboard());
        onView(withId(R.id.edt_phonenumber)).perform(typeText(phonenumber), closeSoftKeyboard());
        onView(withId(R.id.edt_dateofbirth)).perform(replaceText(dateofbirth));
        onView(withId(R.id.edt_email)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.edt_password)).perform(typeText(passwd), closeSoftKeyboard());
        onView(withId(R.id.btn_register)).perform(click());

        onView(withText("You need to accept the terms and condition to complete the registration")).check(matches(isDisplayed()));

    }

    @Test
    public void testRegisterActivityEmpty(){

        onView(withId(R.id.btn_register)).perform(click());


        onView(withId(R.id.edt_fullname)).check(matches(hasErrorText("This Field cannot be empty")));
        onView(withId(R.id.edt_phonenumber)).check(matches(hasErrorText("This Field cannot be empty")));
        onView(withId(R.id.edt_dateofbirth)).check(matches(hasErrorText("This Field cannot be empty")));
        onView(withId(R.id.edt_email)).check(matches(hasErrorText("This Field cannot be empty")));
        onView(withId(R.id.edt_password)).check(matches(hasErrorText("This Field cannot be empty")));
    }
}

