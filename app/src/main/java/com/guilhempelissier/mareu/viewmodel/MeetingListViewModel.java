package com.guilhempelissier.mareu.viewmodel;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.guilhempelissier.mareu.di.DI;
import com.guilhempelissier.mareu.model.Meeting;
import com.guilhempelissier.mareu.repository.MeetingRepository;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MeetingListViewModel extends ViewModel {
	private MeetingRepository repository;
	private LiveData<List<Meeting>> meetingList;

	public MeetingListViewModel() {
		repository = DI.getMeetingRepository();
		meetingList = repository.getMeetingList();
	}

	public LiveData<List<FormattedMeeting>> getMeetingListObservable() {
		return Transformations.map(meetingList, new Function<List<Meeting>, List<FormattedMeeting>>() {
			@Override
			public List<FormattedMeeting> apply(List<Meeting> input) {
				List <FormattedMeeting> formattedMeetings = new ArrayList<>();
				for (Meeting meeting: input) {
					formattedMeetings.add(formatMeeting(meeting));
				}
				return formattedMeetings;
			}
		});
	}

	public void deleteMeetingById(int id) {
		repository.deleteMeetingById(id);
	}

	private FormattedMeeting formatMeeting(Meeting meeting) {
		DateFormat df = DateFormat.getTimeInstance(DateFormat.SHORT);
		Date meetingDate = new Date(meeting.getTime());

		String title = (meeting.getTopic() + " - " +
				df.format(meetingDate) + " - " +
				meeting.getPlace());

		String description = "";
		for (String participant : meeting.getParticipants()) {
			description = (new StringBuilder()).append(description).append(participant + ", ").toString();
		}
		description = description.substring(0, description.length() - 2);

		return new FormattedMeeting(title, description, meeting.getId());
	}
}
