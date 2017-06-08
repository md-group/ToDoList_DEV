package com.todolist;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.util.StringConverter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ToDoListController implements Initializable{


    @FXML
    private Button bttnAddEvent;

    @FXML
    private DatePicker pickerDate;

    @FXML
    private ComboBox<EventsList> eventsSelector;
    private ObservableList<EventsList> myEventsSelector = FXCollections.observableArrayList();

    @FXML
    private Button bttnDone;

    @FXML
    private Button bttnRemove;

    @FXML
    private TableView<?> eventsTable;

    @FXML
    private TableColumn<EventsList, String> eventCol;

    @FXML
    private TableColumn<?, ?> dateCol;

    @FXML
    private TableColumn<?, ?> doneCol;

    @FXML
    private TableColumn<?, ?> observationCol;

    @FXML
    private TableColumn<?, ?> removeCol;

    @FXML
    void bttnAddEventAction(ActionEvent event) {

    }

    @FXML
    void bttnDoneAction(ActionEvent event) {

    }

    @FXML
    void bttnRemoveAction(ActionEvent event) {

    }


    @FXML
    void pickerDateAction(ActionEvent event) {

    }
    
    public 
	
	@Override
	void initialize(URL location, ResourceBundle resources) {
		System.out.println("The pane loaded");
		
		// Add some sample data.
		myEventsSelector.add(new EventsList("Office at 8:00 AM"));
		myEventsSelector.add(new EventsList("Meeting with Kevin at 8:30 AM" ));
		myEventsSelector.add(new EventsList("Meeting with Donald at 10:00 AM"));
		myEventsSelector.add(new EventsList("Launch at 13:00 PM"));
		myEventsSelector.add(new EventsList("Meeting at 14:30 PM"));
		
		// Init ComboBox items.
		eventsSelector.setItems(myEventsSelector);
		
		// Define rendering of the list of values in ComboBox drop down. 
		eventsSelector.setCellFactory((comboBox) -> {
		    return new ListCell<EventsList>() {
		        @Override
		        protected void updateItem(EventsList item, boolean empty) {
		            super.updateItem(item, empty);

		            if (item == null || empty) {
		                setText(null);
		            } else {
		                setText(item.getEventsList());
		            }
		        }
		    };
		});

		// Define rendering of selected value shown in ComboBox.
		eventsSelector.setConverter(new StringConverter<EventsList>() {
		    public String toString(EventsList eventsList) {
		        if (eventsList == null) {
		            return null;
		        } else {
		            return eventsList.getEventsList();
		        }
		    }

		    public EventsList fromString(String eventsListString) {
		        return null; // No conversion fromString needed.
		    }
		});
		
		// Handle ComboBox event.
		eventsSelector.setOnAction((event) -> {
		    EventsList selectedEvent = eventsSelector.getSelectionModel().getSelectedItem();
		    System.out.println("ComboBox Action (selected: " + selectedEvent.toString() + ")");
		});
		
	}

}
