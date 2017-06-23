package com.todolist;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
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
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class ToDoListController implements Initializable {

	String text = "";
	LocalDate isoDate;

	@FXML
	private Button bttnAddEvent;

	@FXML
	private DatePicker pickerDate;

	@FXML
	private ComboBox<String> eventsSelector;

	@FXML
	private Button bttnRemove;

	@FXML
	private TableView<EventsBean> eventsTable;

	@FXML
	private TableColumn<EventsBean, String> eventCol;

	@FXML
	private TableColumn<EventsBean, LocalDate> dateCol;

	@FXML
	private TableColumn<EventsBean, Boolean> doneCol;

	@FXML
	private TableColumn<EventsBean, String> observationCol;

	@FXML
	private TableColumn<EventsBean, Boolean> removeCol;

	@FXML
	void pickerDateAction(ActionEvent event) {
		isoDate = pickerDate.getValue();
	}

	ObservableList<EventsBean> dataList = FXCollections.observableArrayList();

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

		doneCol.setCellFactory(CheckBoxTableCell.forTableColumn(doneCol));
		removeCol.setCellFactory(CheckBoxTableCell.forTableColumn(removeCol));
		eventCol.setCellValueFactory(new PropertyValueFactory<EventsBean, String>("event"));
		dateCol.setCellValueFactory(new PropertyValueFactory<EventsBean, LocalDate>("date"));
		observationCol.setCellValueFactory(new PropertyValueFactory<EventsBean, String>("observation"));
		removeCol.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
		observationCol.setCellFactory(TextFieldTableCell.<EventsBean>forTableColumn());
		observationCol.setOnEditCommit((CellEditEvent<EventsBean, String> t) -> {
			((EventsBean) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setObservation(t.getNewValue());
		});
		observationCol.setSortable(false);

		eventsTable.setItems(dataList);
		eventsTable.setEditable(true);

		bttnAddEvent.setOnAction((ActionEvent e) -> {
			try {
				text = eventsSelector.getValue().toString();
				dataList.add(new EventsBean(text, isoDate, "", false));
			} catch (Exception e1) {
				System.out.println("Nothing selected");
			}
		});

		bttnRemove.setOnAction((ActionEvent e) -> {
			ObservableList<EventsBean> dataListToRemove = FXCollections.observableArrayList();
			for (EventsBean bean : dataList) {
				if (bean.getSelected()) {
					dataListToRemove.add(bean);
				}
			}
			dataList.removeAll(dataListToRemove);

			// Below code it is for delete a focused row
			// EventsBean selectedItem =
			// eventsTable.getSelectionModel().getSelectedItem();
			// eventsTable.getItems().remove(selectedItem);
		});

	}
}
