package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import task.Task;
import task.ValidationException;

public class GuiController implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assert priorityChoiceBox != null : "fx:id=\"priorityChoiceBox\" was not injected: check your FXML file 'TodoGui.fxml'.";
		priorityChoiceBox.getItems().addAll("High","Medium","Low"); // Add all possible elements to priorityChoiceBox
		priorityChoiceBox.getSelectionModel().select(0); // Set default selection to item[0]
		tableView.setItems(tasks);
		
		taskCol.setCellValueFactory(new PropertyValueFactory<Task, String>("title"));
		priorityCol.setCellValueFactory(new PropertyValueFactory<Task, String>("priority"));
		
		doneCol.setCellValueFactory(
				new Callback<CellDataFeatures<Task, Boolean>, ObservableValue<Boolean>>(){
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<Task, Boolean> param){
						return param.getValue().getIsComplete();
					}
				});
		doneCol.setCellFactory( CheckBoxTableCell.forTableColumn(doneCol));
		tableView.getColumns().addAll(doneCol, taskCol, priorityCol);
	}
	
	@FXML
	private Button addButton;
	@FXML
	private Label title;
	@FXML
	private TextField descriptionTextField;
	@FXML
	private TableView<Task> tableView = new TableView<Task>();
	private ObservableList<Task> tasks = FXCollections.observableArrayList();
	@FXML
	TableColumn<Task, Boolean> doneCol = new TableColumn<Task, Boolean>("Done");
	@FXML
	TableColumn<Task, String> taskCol = new TableColumn<Task, String>("Task Title");
	@FXML
	TableColumn<Task, String> priorityCol = new TableColumn<Task, String>("Priority");
	@FXML //fx:id="priorityChoiceBox"
	private ChoiceBox<String> priorityChoiceBox;
	@FXML
	private Menu fileMenu;
	@FXML
	private Menu helpMenu;
	@FXML
	private Menu editMenu;
	@FXML
	private MenuItem saveMenu;
	@FXML
	private MenuItem loadMenu;
	
	

	
	@FXML
	private void addTask() throws ValidationException{
		tasks.add(new Task(descriptionTextField.getText(), priorityChoiceBox.getValue()));		
	}
	
	@FXML
	public void saveTasks(){
		if (tasks.isEmpty()==true){ // Make sure tasks ArrayList isn't empty
			System.out.print("There are no  tasks to save");
		}
		else{
			FileWriter taskList = null;
			try {
				FileChooser chooser = new FileChooser();
				chooser.setTitle("Save Task List");
		        chooser.setInitialDirectory(
		            new File(System.getProperty("user.home"))
		        );
		        chooser.getExtensionFilters().addAll(
		        		new FileChooser.ExtensionFilter("TXT (*.txt)", "*.txt")); // Only TXT files can be saved
		        
				File file = chooser.showSaveDialog(new Stage());
				taskList = new FileWriter(file);
				for(Task t:tasks){
					taskList.append(t.createTabRecord());
					taskList.append('\n');
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				if (taskList != null){ // Only try to close the stream if taskList was created
					try {
						taskList.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}	
	}
	
	@FXML
	public void loadTasks(){
		tasks.clear(); // Clear tasks array before loading
		BufferedReader taskList = null;		
		try {// Open file with FileReader
			FileChooser chooser = new FileChooser(); // Instantiate FileChooser from JavaFX
			chooser.setTitle("Load Task List");
	        chooser.setInitialDirectory(
	            new File(System.getProperty("user.home"))
	        );
	        chooser.getExtensionFilters().addAll(
	        		new FileChooser.ExtensionFilter("TXT (*.txt)", "*.txt")); // Only TXT files can be loaded
	        
			File file = chooser.showOpenDialog(new Stage()); // Set the file from the FileChooser
			taskList = new BufferedReader (new FileReader(file));
			String taskLine;
			//Load words into ArrayList<String>
			while ((taskLine = taskList.readLine()) != null){
				String[] oneTask = taskLine.split("\t");
				Task task1 = new Task(oneTask[0], oneTask[1], Boolean.valueOf(oneTask[2])); // Create new task
				tasks.add(task1); // Add task to the end of the list 
			}
		} catch (FileNotFoundException e) { // If file doesn't exits
			System.err.println("File not found tasks.txt (The system cannot find the file specified)");
		} catch (ValidationException e){ // If task doesn't validate
			e.printStackTrace();
		} catch (IOException e) { // If cannot read from file
			e.printStackTrace();
		}
		finally {
			if (taskList != null){ // Only try to close the stream if taskList was created
				try {
					taskList.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}


	

