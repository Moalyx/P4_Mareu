package com.tuto.mareu;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.tuto.mareu.UI.MainActivity;
import com.tuto.mareu.di.DI;
import com.tuto.mareu.model.Meeting;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.tuto.mareu.test.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static int ITEMS_COUNT = 5;

    private MainActivity activity;
    private List<Meeting> meetings;

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() {
        activity = activityRule.getActivity();
        assertThat(activity, notNullValue());
        meetings = DI.getNeighbourApiService().getMeetings();
    }

    @Test
    public void meetingsListNotEmpty() {
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.meeting_recyclerview))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void datePickerTest() {
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.faButton)).perform(click());
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.datePickerButton)).perform(scrollTo(), click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2021, 7, 1));
        //onView(withText("OK")).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.datePickerButton)).check(matches(withText("1/7/2021")));
    }

    @Test
    public void timePickerTest() {
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.faButton)).perform(click());
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.timePickerButton)).perform(scrollTo(), click());
        onView(isAssignableFrom(TimePicker.class)).perform(PickerActions.setTime(7, 25));
        onView(withText("OK")).perform(click());
        //onView(withId(android.R.id.button1)).perform(click());
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.timePickerButton)).check(matches(withText("7H25")));
    }

    @Test
    public void roomSpinnerTest() {
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.faButton)).perform(click());
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.roomSpinner)).perform(scrollTo(), click());
        onView(ViewMatchers.withText("Salle 10")).perform(click());
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.roomSpinner)).check(matches(withSpinnerText(containsString("Salle 10"))));
    }

    @Test
    public void selectedParticipantsTest() {
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.faButton)).perform(click());
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.participantSpinner)).perform(click());
        onView(ViewMatchers.withText("Shin@gmail.com")).perform(click());
        onView(withText("OK")).perform(click());
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.participantSpinner)).check(matches(withText(containsString("Shin@gmail.com"))));
    }

    @Test
    public void selectedColorTest() {
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.faButton)).perform(click());
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.colorSpinner)).perform(click());
        onView(ViewMatchers.withParentIndex(1)).perform(click());
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.colorSpinner)).equals(com.tuto.mareu.R.drawable.ic_circle_green);
    }

    @Test
    public void selectedSubjectTest() {
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.faButton)).perform(click());
        onView(ViewMatchers.withId(R.id.subject_typing)).perform(scrollTo(), click());
        onView(ViewMatchers.withId(R.id.subject_typing)).perform(replaceText("meeting test"));
        onView(ViewMatchers.withId(R.id.subject_typing)).check(matches(withText(containsString("meeting test"))));
    }

    @Test
    public void addActivityIsDisplayed() {
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.faButton)).perform(click());
        onView(ViewMatchers.withId(R.id.activity_add_reu));
    }

    @Test
    public void CreateNewMeetingTest(){
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.faButton)).perform(click());
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.datePickerButton)).perform(scrollTo(), click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2021, 7, 1));
        onView(withId(android.R.id.button1)).perform(click());
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.timePickerButton)).perform(scrollTo(), click());
        onView(isAssignableFrom(TimePicker.class)).perform(PickerActions.setTime(7, 25));
        onView(withText("OK")).perform(click());
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.roomSpinner)).perform(scrollTo(), click());
        onView(ViewMatchers.withText("Salle 10")).perform(click());
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.participantSpinner)).perform(click());
        onView(ViewMatchers.withText("Shin@gmail.com")).perform(click());
        onView(withText("OK")).perform(click());
        onView(ViewMatchers.withId(com.tuto.mareu.R.id.colorSpinner)).perform(click());
        onView(ViewMatchers.withParentIndex(1)).perform(click());
        onView(ViewMatchers.withId(R.id.subject_typing)).perform(scrollTo(), click());
        onView(ViewMatchers.withId(R.id.subject_typing)).perform(replaceText("meeting test"));
        onView(withId(R.id.saveButton)).perform(scrollTo(),click());
        onView(withId(R.id.meeting_recyclerview))
                .check(withItemCount(ITEMS_COUNT+1));
    }

    @Test
    public void filterRoomTest(){
        onView(ViewMatchers.withId(R.id.filter_button)).perform(click());
        onView(Matchers.allOf(withId(R.id.title), withText("Filtrer par salle"))).perform(click());
        onView(Matchers.allOf(withId(R.id.title), withText("Salle 1"))).perform(click());
        onView(allOf(withId(R.id.meeting_recyclerview), isDisplayed()))
                .check(withItemCount(1));
    }

    @Test
    public void filterDateTest(){
        onView(ViewMatchers.withId(R.id.filter_button)).perform(click());
        onView(Matchers.allOf(withId(R.id.title), withText("Filtrer par date"))).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2021, 7, 11));
        onView(withText("OK")).perform(click());
        onView(allOf(withId(R.id.meeting_recyclerview), isDisplayed()))
                .check(withItemCount(2));
    }

    @Test
    public void filterResetTest(){
        onView(ViewMatchers.withId(R.id.filter_button)).perform(click());
        onView(Matchers.allOf(withId(R.id.title), withText("Filtrer par date"))).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2021, 7, 11));
        onView(withText("OK")).perform(click());
        onView(allOf(withId(R.id.meeting_recyclerview), isDisplayed()))
                .check(withItemCount(2));
        onView(ViewMatchers.withId(R.id.filter_button)).perform(click());
        onView(Matchers.allOf(withId(R.id.title), withText("Reset"))).perform(click());
        int newSize = meetings.size();
        onView(allOf(withId(R.id.meeting_recyclerview), isDisplayed()))
                .check(withItemCount(newSize));
    }
}
