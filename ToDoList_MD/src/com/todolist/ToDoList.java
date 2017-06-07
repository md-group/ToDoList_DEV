package com.todolist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ToDoList extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		
		Pane pane = FXMLLoader.load(getClass().getResource("ToDoList.fxml"));
				
		Scene scene = new Scene(pane);
		scene.getStylesheets().add(getClass().getResource("ToDoListStyle.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Plan yourself");
		primaryStage.show();
	}
}