package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import task.Task;

public class GuiController implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assert priorityChoiceBox != null : "fx:id=\"priorityChoiceBox\" was not injected: check your FXML file 'TodoGui.fxml'.";
		priorityChoiceBox.getItems().addAll("High","Medium","Low"); // Add all possible elements to priorityChoiceBox
		priorityChoiceBox.getSelectionModel().select(0); // Set default selection to item[0]
	}
	
	@FXML
	private Button addButton;
	@FXML
	private TextField descriptionTextField;
	@FXML
	private 
	ListView<Task> listView;
	@FXML //fx:id="priorityChoiceBox"
	private ChoiceBox<String> priorityChoiceBox;
	

	
	@FXML
	private void addTask(Event e){
		
	}


	

}
