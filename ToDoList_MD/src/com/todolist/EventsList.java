package com.todolist;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EventsList {

	private final StringProperty eventsList;

	public EventsList(String eventsList) {
		this.eventsList = new SimpleStringProperty(eventsList);
	}
	
	public String getEventsList() {
		return eventsList.get();
	}

	public void setEventsList(String eventsList) {
		this.eventsList.set(eventsList);
	}
	
	public StringProperty eventsListProperty() {
		return eventsList;
	}

	@Override
	public String toString() {
		return getEventsList();
	}
}
