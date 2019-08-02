package com.guilhempelissier.mareu.service;

import com.guilhempelissier.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyMeetingGenerator {

	public static List<Meeting> DUMMY_MEETING = Arrays.asList(
			new Meeting(1, "Salle 1", "Qualité de la cantine", 46800000, Arrays.asList(
					"joel@lamzone.fr", "alex@lamzone.fr", "paul@lamzone.fr"
			)),
			new Meeting(2, "Salle 2", "Coin fumeur", 50400000, Arrays.asList(
					"luc@lamzone.fr", "noe@lamzone.fr", "viviane@lamzone.fr"
			)),
			new Meeting(3, "Salle 3", "Metrics du quarter", 57600000, Arrays.asList(
					"maxime@lamzone.fr", "amandine@lamzone.fr", "paul@lamzone.fr"
			))
	);

	static List<Meeting> generateMeetings() {
		return new ArrayList<>(DUMMY_MEETING);
	}
}
