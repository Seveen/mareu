package com.guilhempelissier.mareu.ui;

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
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
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
	public void mainActivityOpensTest() {
		onView(allOf(isDisplayed(), withId(R.id.)))
	}
}
