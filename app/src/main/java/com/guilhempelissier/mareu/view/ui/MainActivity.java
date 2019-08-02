package com.guilhempelissier.mareu.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.guilhempelissier.mareu.R;
import com.guilhempelissier.mareu.view.adapter.MeetingListAdapter;
import com.guilhempelissier.mareu.viewmodel.FormattedMeeting;
import com.guilhempelissier.mareu.viewmodel.MeetingListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
	private RecyclerView recyclerView;
	private MeetingListViewModel meetingListViewModel;
	private MeetingListAdapter meetingListAdapter;

	private LiveData<List<FormattedMeeting>> meetings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		meetingListViewModel = ViewModelProviders.of(this).get(MeetingListViewModel.class);
		meetings = meetingListViewModel.getMeetingListObservable();

		recyclerView = findViewById(R.id.meeting_list_recyclerview);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		meetingListAdapter = new MeetingListAdapter(meetings.getValue());
		recyclerView.setAdapter(meetingListAdapter);

		meetings.observe(this, new Observer<List<FormattedMeeting>>() {
			@Override
			public void onChanged(List<FormattedMeeting> formattedMeetings) {
				meetingListAdapter.setData(formattedMeetings);
			}
		});

		meetingListAdapter.setOnItemDeleteListener(new MeetingListAdapter.OnDeleteClickListener() {
			@Override
			public void onDelete(int id) {
				meetingListViewModel.deleteMeetingById(id);
			}
		});
	}
}
