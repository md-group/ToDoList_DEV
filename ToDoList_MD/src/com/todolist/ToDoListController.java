package com.todolist;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class ToDoListController implements Initializable {

	@FXML
	private Button bttnAddEvent;

	@FXML
	private DatePicker pickerDate;

	@FXML
	private ComboBox<String> eventsSelector;

	@FXML
	private Button bttnDone;

	@FXML
	private Button bttnRemove;

	@FXML
	private TableView<EventsBean> eventsTable;

	@FXML
	private TableColumn<?, ?> eventCol;

	@FXML
	private TableColumn<?, ?> dateCol;

	@FXML
	private TableColumn<?, ?> doneCol;

	@FXML
	private TableColumn<EventsBean, String> observationCol;

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
	
	private ObservableList<EventsBean> dataList = FXCollections.observableArrayList(new EventsBean(""));

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("The pane loaded");

		List<String> myList;
		try {
			myList = Files.lines(Paths.get("src/com/todolist/EventsList.txt")).collect(Collectors.toList());
			eventsSelector.setItems(FXCollections.observableArrayList(myList));
		} catch (IOException e) {
			System.out.println("Don t find file");
		}
		 
		observationCol.setCellValueFactory(new PropertyValueFactory<>("observation"));
		
		observationCol.setCellFactory(TextFieldTableCell.<EventsBean>forTableColumn());
		
		observationCol.setOnEditCommit(
			    (CellEditEvent<EventsBean, String> t) -> {
			        ((EventsBean) t.getTableView().getItems().get(
			            t.getTablePosition().getRow())
			            ).setObservation(t.getNewValue());
			});
		
		eventsTable.setItems(dataList);
		eventsTable.setEditable(true);
		observationCol.setSortable(false);
	}
}
