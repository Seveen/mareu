package com.guilhempelissier.mareu.ui.utils;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import com.guilhempelissier.mareu.R;

import org.hamcrest.Matcher;

public class DeleteViewAction implements ViewAction {
	@Override
	public Matcher<View> getConstraints() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Click on specific button";
	}

	@Override
	public void perform(UiController uiController, View view) {
		View button = view.findViewById(R.id.meeting_delete_btn);
		// Maybe check for null
		button.performClick();
	}
}