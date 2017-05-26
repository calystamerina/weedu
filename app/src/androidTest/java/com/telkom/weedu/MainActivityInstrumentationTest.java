package com.telkom.weedu;

/**
 * Created by Calysta M on 4/19/2017.
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.telkom.weedu.view.main.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    //Swipe Up the screen
    //Swipe Up the screen
    //Swipe Left the screen
    //Swipe Left the screen
    //Swipe Left the screen
    //Swipe Left the screen
    //Make sure the navigation is working well
    @Test
    public void testSwipe(){
        onView(withId(R.id.vp_main)).perform(swipeUp());
        onView(withId(R.id.vp_main)).perform(swipeUp());
        onView(withId(R.id.vp_main)).perform(swipeLeft());
        onView(withId(R.id.vp_main)).perform(swipeLeft());
        onView(withId(R.id.vp_main)).perform(swipeLeft());
    }

    @Test
    public void testClickFragment(){
        onView(withText("Dashboard")).perform(click());
        onView(withText("Sedang Dipelajari")).check(matches(isDisplayed()));

        onView(withText("Help")).perform(click());
        onView(withId(R.id.btn_help_chat)).check(matches(isDisplayed()));

        onView(withText("My Account")).perform(click());
        onView(withText("Edit Account")).check(matches(isDisplayed()));

    }



    @Test
    public void testHelpDeskButton(){
        onView(withId(R.id.vp_main)).perform(swipeUp());
        onView(withId(R.id.vp_main)).perform(swipeUp());
        onView(withId(R.id.btn_help_desk)).perform(click());

        onView(withId(R.id.btn_help_chat)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_help_contact_us)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_help_about)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_help_faq)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_help_tnc)).check(matches(isDisplayed()));
    }

    //Back in into Main Activity Page
    //Swipe Up
    //Swipe Up
    //Swipe Left
    //Make Sure there are text "Sedang Dipelajari" dan "Terakhir Dilihat"
    @Test
    public void testDashboardFragment(){
        onView(withId(R.id.vp_main)).perform(swipeUp());
        onView(withId(R.id.vp_main)).perform(swipeUp());
        onView(withId(R.id.vp_main)).perform(swipeLeft());

        onView(withText("Sedang Dipelajari")).check(matches(isDisplayed()));
    }

    //Back in into Main Activity Page
    //Swipe Up
    //Swipe Up
    //Swipe Left
    //Make Sure there are text "Sedang Dipelajari" dan "Terakhir Dilihat"
    @Test
    public void testHelpFragment(){
        onView(withId(R.id.vp_main)).perform(swipeUp());
        onView(withId(R.id.vp_main)).perform(swipeUp());
        onView(withId(R.id.vp_main)).perform(swipeLeft());
        onView(withId(R.id.vp_main)).perform(swipeLeft());

        onView(withId(R.id.btn_help_chat)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_help_contact_us)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_help_about)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_help_faq)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_help_tnc)).check(matches(isDisplayed()));
    }

    @Test
    public void testMyAccountFragment(){
        onView(withId(R.id.vp_main)).perform(swipeUp());
        onView(withId(R.id.vp_main)).perform(swipeUp());
        onView(withId(R.id.vp_main)).perform(swipeLeft());
        onView(withId(R.id.vp_main)).perform(swipeLeft());
        onView(withId(R.id.vp_main)).perform(swipeLeft());

        onView(withText("Edit Account")).check(matches(isDisplayed()));
        onView(withText("Ganti Password")).check(matches(isDisplayed()));
        onView(withText("Topup Saldo")).check(matches(isDisplayed()));
        onView(withText("Tukar Point")).check(matches(isDisplayed()));
    }

    @Test
    public void testLogoutButton(){
        onView(withId(R.id.vp_main)).perform(swipeUp());
        onView(withId(R.id.vp_main)).perform(swipeUp());
        onView(withId(R.id.vp_main)).perform(swipeLeft());
        onView(withId(R.id.vp_main)).perform(swipeLeft());
        onView(withId(R.id.vp_main)).perform(swipeLeft());
        onView(withId(R.id.vp_main)).perform(swipeUp());
        onView(withId(R.id.vp_main)).perform(swipeUp());
        onView(withId(R.id.btn_logout)).perform(click());

        onView(withId(R.id.btn_login)).check(matches(isDisplayed()));
    }

    @Test
    public void testRecommendationRecylerView(){
//        onView(withId(R.id.vp_main)).perform(swipeUp());
        onView(withId(R.id.rv_recommendations)).perform(actionOnItemAtPosition(1, click()));
        onView(withText("Penulis")).check(matches(isDisplayed()));
        onView(withText("Penerbit")).check(matches(isDisplayed()));
        onView(withText("ISBN")).check(matches(isDisplayed()));
        onView(withText("Bahasa")).check(matches(isDisplayed()));
}


}

