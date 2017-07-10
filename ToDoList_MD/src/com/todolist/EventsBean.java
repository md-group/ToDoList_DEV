package com.todolist;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EventsBean implements Serializable{
	
	
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
	
	public StringProperty eventProperty(){
		return event;
	}
	
	public String getEvent() {
		return eventProperty().get();
	}
	
	public final void setEvent(String event) {
		eventProperty().set(event);
	}
	
	public ObjectProperty<LocalDate> dateProperty(){
		return date;
	}
	
	public LocalDate getDate() {
		return dateProperty().get();
	}
	
	public final void setDate(LocalDate date) {
		dateProperty().set(date);
	}
	
	public StringProperty doneProperty(){
		return done;
	}
	
	public String getDone() {
		return doneProperty().get();
	}
	public final void setDone(String done) {
		doneProperty().set(done);
	}
	
	public StringProperty observationProperty(){
		return observation;
	}
	
	public String getObservation(){
		return observationProperty().get();
	}
	
	public final void setObservation(String observation) {
		observationProperty().set(observation);
	}

	public BooleanProperty selectedProperty() {
		return selected;
	}
	
	public boolean getSelected(){
		return selectedProperty().get();
	}

	public final void setSelected(boolean selected) {
		selectedProperty().set(selected);
	}
	
	private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeObject(getEvent()); // write event as a plain String
        s.writeObject(getDate()); 
        s.writeObject(getDone());
        s.writeObject(getObservation());
        s.writeBoolean(getSelected());
    }

    // custom deserialization:
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.event = new SimpleStringProperty((String)s.readObject());
        this.date = new SimpleObjectProperty<>((LocalDate)s.readObject());
        this.done = new SimpleStringProperty((String)s.readObject());
        this.observation = new SimpleStringProperty((String)s.readObject());
        this.selected = new SimpleBooleanProperty(s.readBoolean());
    }
	
}
