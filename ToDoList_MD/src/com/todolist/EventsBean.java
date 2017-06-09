package com.todolist;

import javafx.beans.property.SimpleStringProperty;

public class EventsBean {
	
	private SimpleStringProperty observation;

	public EventsBean(String observation) {
		this.observation = new SimpleStringProperty(observation);
	}
	
	public String getObservation() {
		return observation.get();
	}

	public void setObservation(String observation) {
		this.observation.set(observation);
	}

	
	
	
}
