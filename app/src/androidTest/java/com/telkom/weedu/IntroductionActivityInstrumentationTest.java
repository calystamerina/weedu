package com.telkom.weedu;

/**
 * Created by Calysta M on 4/19/2017.
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.telkom.weedu.view.intro.IntroductionActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.telkom.weedu.R.id.btn_prev;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class IntroductionActivityInstrumentationTest{

    @Rule
    public ActivityTestRule<IntroductionActivity> mActivityRule = new ActivityTestRule<>(
            IntroductionActivity.class);

    @Test
    public void testIntroductionNextButton(){
        onView(withText("Nikmati Pengalaman Baru Dengan Aplikasi Weedu")).check(matches(isDisplayed()));
        onView(withId(R.id.btn_next)).perform(click());

        onView(withText("Kemudahan Dalam Satu Genggaman Ke Berbagai Platform")).check(matches(isDisplayed()));

        onView(withId(R.id.btn_next)).perform(click());
        onView(withText("Dapatkan Akun EduMail Anda Secara Gratis")).check(matches(isDisplayed()));

    }



    @Test
    public void testIntroductionSkipButton(){
        onView(withId(R.id.btn_skip)).perform(click());


        onView(withText("Dapatkan Akun EduMail Anda Secara Gratis")).check(matches(isDisplayed()));
        onView(withText("Got It, I'm Ready!")).check(matches(isDisplayed()));


    }

    @Test
    public void testIntroductionPrevButton(){
        onView(withId(btn_prev)).perform(click());

        onView(withText("Kemudahan Dalam Satu Genggaman Ke Berbagai Platform")).check(matches(isDisplayed()));

    }

}
