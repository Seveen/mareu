package com.guilhempelissier.mareu.viewmodel;

public class FormattedMeeting {
	private String title;
	private String description;
	private String id;

	public FormattedMeeting(String title, String description, String id) {
		this.title = title;
		this.description = description;
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
