package com.guilhempelissier.mareu.view.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.guilhempelissier.mareu.R;
import com.guilhempelissier.mareu.view.adapter.MeetingListAdapter;
import com.guilhempelissier.mareu.viewmodel.FormattedMeeting;
import com.guilhempelissier.mareu.viewmodel.MeetingListFilter;
import com.guilhempelissier.mareu.viewmodel.MeetingListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NewMeetingDialog.NewMeetingDialogListener, FilterDialog.FilterDialogListener {
	private RecyclerView recyclerView;
	private MeetingListViewModel meetingListViewModel;
	private MeetingListAdapter meetingListAdapter;

	private FloatingActionButton newMeetingButton;

	private LiveData<List<FormattedMeeting>> meetings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = findViewById(R.id.main_toolbar);
		setSupportActionBar(toolbar);

		newMeetingButton = findViewById(R.id.meeting_list_addMeetingBtn);

		newMeetingButton.setOnClickListener(view -> showNewMeetingDialog());

		meetingListViewModel = ViewModelProviders.of(this).get(MeetingListViewModel.class);
		meetings = meetingListViewModel.getMeetingListObservable();

		recyclerView = findViewById(R.id.meeting_list_recyclerview);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		meetingListAdapter = new MeetingListAdapter(meetings.getValue());
		recyclerView.setAdapter(meetingListAdapter);

		meetings.observe(this, formattedMeetings -> meetingListAdapter.setData(formattedMeetings));

		meetingListAdapter.setOnItemDeleteListener(id -> meetingListViewModel.deleteMeetingById(id));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.filter_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		if (item.getItemId() == R.id.action_filter) {
			showFilterDialog();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void showNewMeetingDialog() {
		NewMeetingDialog dialog = new NewMeetingDialog();
		dialog.show(getSupportFragmentManager(), "NewMeetingDialog");
	}

	public void showFilterDialog() {
		MeetingListFilter currentFilter = meetingListViewModel.getMeetingListFilter().getValue();
		if (currentFilter != null) {
			FilterDialog dialog = new FilterDialog(currentFilter);
			dialog.show(getSupportFragmentManager(), "FilterDialog");
		}
	}

	@Override
	public void onDialogPositiveClick(String topic, String place, long time, List<String> participants) {
		meetingListViewModel.addNewMeeting(topic, place, time, participants);
	}

	@Override
	public void onDialogPositiveClick(long startDateFilter, long stopDateFilter, List<String> allowedPlacesFilter) {
		meetingListViewModel.setStartHourFilter(startDateFilter);
		meetingListViewModel.setEndHourFilter(stopDateFilter);
		meetingListViewModel.setAllowedPlaces(allowedPlacesFilter);
	}
}
