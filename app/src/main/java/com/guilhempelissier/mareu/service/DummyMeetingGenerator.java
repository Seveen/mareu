package com.guilhempelissier.mareu.service;

import com.guilhempelissier.mareu.model.Meeting;
import com.guilhempelissier.mareu.model.Participant;
import com.guilhempelissier.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyMeetingGenerator {

	public static List<Meeting> DUMMY_MEETING = Arrays.asList(
			new Meeting(new Room("Salle 1"), "Reunion 1", 46800000, Arrays.asList(
					new Participant("joel@lamzone.fr"),
					new Participant("alex@lamzone.fr"),
					new Participant("paul@lamzone.fr")
			), "1"),
			new Meeting(new Room("Salle 2"), "Reunion 2", 50400000, Arrays.asList(
					new Participant("luc@lamzone.fr"),
					new Participant("noe@lamzone.fr"),
					new Participant("viviane@lamzone.fr")
			), "2"),
			new Meeting(new Room("Salle 3"), "Reunion 3", 57600000, Arrays.asList(
					new Participant("maxime@lamzone.fr"),
					new Participant("amandine@lamzone.fr"),
					new Participant("paul@lamzone.fr")
			), "3")
	);

	static List<Meeting> generateMeetings() {
		return new ArrayList<>(DUMMY_MEETING);
	}
}
