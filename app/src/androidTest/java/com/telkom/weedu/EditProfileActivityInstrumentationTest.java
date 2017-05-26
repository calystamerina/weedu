package com.telkom.weedu;

/**
 * Created by Calysta M on 5/8/2017.
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.telkom.weedu.view.editprofile.EditProfileActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EditProfileActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<EditProfileActivity> mActivityRule = new ActivityTestRule<>(
            EditProfileActivity.class);


    @Test
    public void testEditProfileActivitySuccess(){
        String name = "morientez";
        String phonenumber = "082816287";

        onView(withId(R.id.edt_user_name)).perform(typeText(name), closeSoftKeyboard());
        onView(withId(R.id.edt_phone_number)).perform(typeText(phonenumber), closeSoftKeyboard());
        onView(withId(R.id.btn_edit_profile)).perform(click());
        assertTrue(mActivityRule.getActivity().isFinishing());


        //onView(withText("editprofile profile berhasil")).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        //        onView(withText("editprofile profile berhasil")).inRoot(withDecorVIew(n‌​ot(mActivityRule.get‌​Activity().getWindow‌​().getDecorView())))‌​.check(matches(isDis‌​played()));

    }

    }