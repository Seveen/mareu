package com.guilhempelissier.mareu.model;

import java.util.List;

public class Meeting {
	private Integer id;
	private String place;
	private String topic;
	private int time;
	private List<String> participants;

	public Meeting(Integer id, String place, String topic, int time, List<String> participants) {
		this.id = id;
		this.place = place;
		this.topic = topic;
		this.time = time;
		this.participants = participants;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public List<String> getParticipants() {
		return participants;
	}

	public void setParticipants(List<String> participants) {
		this.participants = participants;
	}

	public void addParticipant(String participant) {
		this.participants.add(participant);
	}

	public void removeParticipant(String participant) {
		this.participants.remove(participant);
	}
}
