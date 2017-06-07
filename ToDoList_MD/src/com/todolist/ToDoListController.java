package com.todolist;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ToDoListController implements Initializable{


    @FXML
    private Button BttnAddEvent;

    @FXML
    private DatePicker PickerDate;

    @FXML
    private ComboBox<String> EventsSelector;

    @FXML
    private Button BttnDone;

    @FXML
    private Button BttnRemove;

    @FXML
    private TableView<?> EventsTable;

    @FXML
    private TableColumn<?, ?> EventCol;

    @FXML
    private TableColumn<?, ?> DateCol;

    @FXML
    private TableColumn<?, ?> DoneCol;

    @FXML
    private TableColumn<?, ?> ObservationCol;

    @FXML
    void BttnAddEventAction(ActionEvent event) {

    }

    @FXML
    void BttnDoneAction(ActionEvent event) {

    }

    @FXML
    void BttnRemoveAction(ActionEvent event) {

    }

    @FXML
    void EventsSelectorAction(ActionEvent event) {

    }

    @FXML
    void PickerDateAction(ActionEvent event) {

    }

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("The pane loaded");
		
	}

}
