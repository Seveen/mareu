package com.guilhempelissier.mareu.ui;

import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.EspressoKey;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.guilhempelissier.mareu.R;
import com.guilhempelissier.mareu.view.ui.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
	private MainActivity mActivity;

	@Rule
	public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

	@Before
	public void setUp() {
		mActivity = mActivityTestRule.getActivity();
		assertThat(mActivity, notNullValue());
	}

	@Test
	public void mainActivityListShouldNotBeEmptyTest() {
		onView(allOf(isDisplayed(), withId(R.id.meeting_list_recyclerview)))
				.check(matches(hasMinimumChildCount(1)));
	}

	@Test
	public void addingAValueToTheFilterShouldChangeList() {
		onView(allOf(isDisplayed(), withId(R.id.action_filter)))
				.perform(click());

		onView(allOf(isDisplayed(), withId(R.id.filterMeetingChipsTextView)))
				.perform(typeText("Salle 2\n"));

		Espresso.closeSoftKeyboard();

		onView(allOf(isDisplayed(), withText("Appliquer")))
				.perform(click());

		onView(allOf(isDisplayed(), withId(R.id.meeting_list_recyclerview)))
				.check(matches(hasChildCount(1)));
	}


}
