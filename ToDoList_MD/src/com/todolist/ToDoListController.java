package com.todolist;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
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
	private TableColumn<EventsBean, String> doneCol;

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

		removeCol.setCellFactory(CheckBoxTableCell.forTableColumn(removeCol));
		eventCol.setCellValueFactory(new PropertyValueFactory<EventsBean, String>("event"));
		dateCol.setCellValueFactory(new PropertyValueFactory<EventsBean, LocalDate>("date"));
		doneCol.setCellValueFactory(new PropertyValueFactory<EventsBean, String>("done"));
		doneCol.setCellFactory(TextFieldTableCell.<EventsBean>forTableColumn());
		doneCol.setOnEditCommit((CellEditEvent<EventsBean, String> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow()).setDone(t.getNewValue());
		});
		observationCol.setCellValueFactory(new PropertyValueFactory<EventsBean, String>("observation"));
		removeCol.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
		observationCol.setCellFactory(TextFieldTableCell.<EventsBean>forTableColumn());
		observationCol.setOnEditCommit((CellEditEvent<EventsBean, String> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow())
					.setObservation(t.getNewValue());
		});
		observationCol.setSortable(false);
		
		loadPersistence();
		
		eventsTable.setItems(dataList);
		eventsTable.setEditable(true);


		bttnAddEvent.setOnAction((ActionEvent e) -> {
			try {
				text = eventsSelector.getValue().toString();
				dataList.add(new EventsBean(text, isoDate, "", "", false));
			} catch (Exception e1) {
				System.out.println("Nothing selected");
			}
			
			setPersistence();

		});

		bttnRemove.setOnAction((ActionEvent e) -> {
			ObservableList<EventsBean> dataListToRemove = FXCollections.observableArrayList();
			for (EventsBean bean : dataList) {
				if (bean.getSelected()) {
					dataListToRemove.add(bean);
				}
			}
			dataList.removeAll(dataListToRemove);
			
			setPersistence();
			
			// Below code it is for delete a focused row
			// EventsBean selectedItem =
			// eventsTable.getSelectionModel().getSelectedItem();
			// eventsTable.getItems().remove(selectedItem);
		});

		}
	
	public void setPersistence() {
		try {
	         FileOutputStream fileOut = new FileOutputStream("Object.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(new ArrayList<EventsBean>(dataList));
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in Object.ser");
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	}
	
	@SuppressWarnings("unchecked")
	public void loadPersistence() {
		try {
	         FileInputStream fileIn = new FileInputStream("Object.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         dataList.setAll((List<EventsBean>)in.readObject());
	         in.close();
	         fileIn.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c) {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return;
	      }
	}
}



	