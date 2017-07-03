package com.todolist;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class EventsBean implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient SimpleStringProperty event;
	private transient SimpleObjectProperty<LocalDate> date;
	private transient SimpleStringProperty done;
	private transient SimpleStringProperty observation;
	private transient SimpleBooleanProperty selected;

	public EventsBean(String event, LocalDate date, String done, String observation, boolean selected) {
		this.event = new SimpleStringProperty(event);
		this.date = new SimpleObjectProperty<LocalDate>(date);
		this.done = new SimpleStringProperty(done);
		this.observation = new SimpleStringProperty(observation);
		this.selected = new SimpleBooleanProperty(selected);
	}
	public String getEvent() {
		return event.get();
	}
	
	public LocalDate getDate() {
		return date.get();
	}
	
	public String getDone() {
		return done.get();
	}
	public void setDone(String done) {
		this.done.set(done);
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
	
	private void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();
		s.writeObject(getEvent());
		s.writeObject(getDate());
		s.writeObject(getDone());
		s.writeObject(getObservation());
		s.writeBoolean(getSelected());
	}
	
	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		s.defaultReadObject();
		this.event = new SimpleStringProperty((String)s.readObject());
		this.date = new SimpleObjectProperty<>((LocalDate)s.readObject());
        this.done = new SimpleStringProperty((String)s.readObject());
        this.observation = new SimpleStringProperty((String)s.readObject());
        this.selected = new SimpleBooleanProperty(s.readBoolean());
    }
	
}
