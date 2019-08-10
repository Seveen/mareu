package com.guilhempelissier.mareu.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.guilhempelissier.mareu.di.DI;
import com.guilhempelissier.mareu.model.Meeting;
import com.guilhempelissier.mareu.model.Participant;
import com.guilhempelissier.mareu.model.Room;
import com.guilhempelissier.mareu.service.MeetingApiService;

import java.util.ArrayList;
import java.util.List;


//Dans une vraie app, cette classe fait l'arbitrage entre les données persistées en local et les
//call API quand les données locales ne sont pas fraiches.

public class MeetingRepository {
	private MeetingApiService meetingApiService = DI.getMeetingApiService();
	public LiveData<List<Meeting>> meetingList;

	public MeetingRepository() {
		meetingList = new MutableLiveData<>();
		((MutableLiveData<List<Meeting>>) meetingList).setValue(meetingApiService.getMeetings());
	}

	public LiveData<List<Meeting>> getMeetingList() {
		return meetingList;
	}

	public void addMeeting(Meeting meeting) {

		meetingApiService.addMeeting(meeting);
		((MutableLiveData<List<Meeting>>) meetingList).setValue(meetingApiService.getMeetings());
	}

	public void createNewMeeting(Room place, String topic, long date, List<Participant> participants) {
		addMeeting(new Meeting(place, topic, date, participants));
	}

	public void removeMeeting(Meeting meeting) {
		meetingApiService.removeMeeting(meeting);
		((MutableLiveData<List<Meeting>>) meetingList).setValue(meetingApiService.getMeetings());
	}

	public void deleteMeetingById(String id) {
		List<Meeting> meetingsToRemove = filterMeetingsById(id);

		if (meetingsToRemove.size() != 0) {
			Meeting meetingToRemove = meetingsToRemove.get(0);
			meetingApiService.removeMeeting(meetingToRemove);
			((MutableLiveData<List<Meeting>>) meetingList).setValue(meetingApiService.getMeetings());
		}
	}

	private List<Meeting> filterMeetingsById(String id) {
		List<Meeting> result = new ArrayList<>();
		List<Meeting> meetings = meetingApiService.getMeetings();

		for (Meeting meeting : meetings) {
			if (meeting.getId().equals(id)) {
				result.add(meeting);
			}
		}

		return result;
	}
}
