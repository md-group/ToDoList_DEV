package com.todolist;

import java.time.LocalDate;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class EventsBean {
	
	
	private SimpleStringProperty event;
	private SimpleObjectProperty<LocalDate> date;
	private SimpleStringProperty observation;
	private SimpleBooleanProperty selected;

	public EventsBean(String event, LocalDate date, String observation, boolean selected) {
		this.event = new SimpleStringProperty(event);
		this.date = new SimpleObjectProperty<LocalDate>(date);
		this.observation = new SimpleStringProperty(observation);
		this.selected = new SimpleBooleanProperty(selected);
	}
	public String getEvent() {
		return event.get();
	}
	
	public LocalDate getDate() {
		return date.get();
	}
	
	public String getObservation(){
		return observation.get();
	}
	
	public void setObservation(String observation) {
		this.observation.set(observation);
	}
	
	public boolean getSelected(){
		return selectedProperty().get();
	}

	public SimpleBooleanProperty selectedProperty() {
		return this.selected;
	}
}
