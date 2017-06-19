package com.todolist;

import java.time.LocalDate;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class EventsBean {
	
	private SimpleStringProperty observation;
	private SimpleStringProperty event;
	private SimpleObjectProperty<LocalDate> date;

	public EventsBean(String event, LocalDate date, String observation) {
		this.event = new SimpleStringProperty(event);
		this.date = new SimpleObjectProperty<LocalDate>(date);
		this.observation = new SimpleStringProperty(observation);
	}
	public String getEvent() {
		return event.get();
	}
	
	public LocalDate getDate() {
		return date.get();
	}
	
	public String getObservation() {
		return observation.get();
	}
	
	public void setObservation(String observation) {
		this.observation.set(observation);
	}

}
