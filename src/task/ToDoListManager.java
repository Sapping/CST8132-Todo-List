/* File Name: ToDoListManager.java
* Course Name: CST8132
* Lab Section: 312
* Student Name: Lucas Melin
*/


package task;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Class ToDoListManager models a to-do list.
* <p>
* The user is presented with a menu from which they can choose
* to:
* <li> Add task
* <li> Remove task
* <li> View tasks
* <li> Save tasks
* <li> Load tasks
* <li> Sort by title
* <li> Sort by priority
* <li> Sort by completion
* 
* @author Lucas Melin
* @version 1.2 April 22, 2016
*/
public class ToDoListManager {
	
	/** The add task. */
	private static final int ADD_TASK = 1;
	
	/** The toggle task complete. */
	private static final int TOGGLE_TASK_COMPLETE = 2;
	
	/** The remove task. */
	private static final int REMOVE_TASK = 3;
	
	/** The view tasks. */
	private static final int VIEW_TASKS = 4;
	
	/** The save tasks. */
	private static final int SAVE_TASKS = 5;
	
	/** The load tasks. */
	private static final int LOAD_TASKS = 6;
	
	/** The sort by title. */
	private static final int SORT_BY_TITLE = 7;

	/** The sort by priority. */
	private static final int SORT_BY_PRIORITY = 8;
	
	/** The sort by is-complete. */
	private static final int SORT_BY_ISCOMPLETE = 9;
	
	/** The exit. */
	private static final int EXIT = 10;
	
	/** The tasks. */
	private ArrayList<Task> tasks;
	
	/** The keyboard. */
	Scanner keyboard;
	

	/**
	 * Instantiates the scanner and the ArrayList that holds the tasks.
	 */
	public ToDoListManager(){
		keyboard = new Scanner(System.in);
		tasks = new ArrayList<Task>();		
	}
	
	/**
	 * Runs the toDoList selection.
	 * <p>
	 * Invokes menu and then receives user input.
	 * Based on the user input, methods are called on the tasks ArrayList such as:
	 * 
	 * <li> Add task
	 * <li> Remove task
	 * <li> View tasks
	 * <li> Save tasks
	 * <li> Load tasks
	 * <li> Sort by title
	 * <li> Sort by priority
	 * <li> Sort by completion
	 */
	public void runToDoList(){
		int choice = 0;
		do {
			try{//try-catch to handle text entries
				// Print out error message and let loop continue
				showMenu(); // Display the menu
				choice = keyboard.nextInt();
				keyboard.nextLine(); // Clear out line-terminator characters
				switch (choice) {
				case ADD_TASK:
					addTask(); // Add Task				
					break;
				case TOGGLE_TASK_COMPLETE:
					toggleTaskComplete(); // Toggle task
					break;
				case REMOVE_TASK:
					removeTask(); // Remove task
					break;
				case VIEW_TASKS:
					viewTasks(); // View tasks			
					break;
				case SAVE_TASKS:
					saveTasks();
					break;
				case LOAD_TASKS:
					loadTasks();
					break;
				case SORT_BY_TITLE:
					sortByTitle();
					break;
				case SORT_BY_PRIORITY:
					sortByPriority();
					break;
				case SORT_BY_ISCOMPLETE:
					sortByIsComplete();
					break;
				case EXIT:
					//System.out.print("Case 5");
					break;
				default:
					System.out.print("Unrecognized command");
					break;
				} //end of switch
			}catch (InputMismatchException e){
				System.err.println("Please enter integer numbers for menu selection");
				keyboard.nextLine();
			} // end of catch block
		} while (choice!=(EXIT)); // end of loop
		System.out.print("Goodbye.");
		
	}
	
	/**
	 * Outputs menu on console, only called from runToDoList.
	 */
	private void showMenu(){
		System.out.print("\n1 to add a task"
				+ "\n2 to toggle a task is completed"
				+ "\n3 to remove a task"
				+ "\n4 to view all tasks"
				+ "\n5 to save tasks to file"
				+ "\n6 to load tasks from file"
				+ "\n7 to sort tsks by title"
				+ "\n8 to sort tasks by priority"
				+ "\n9 to sort tasks by is-complete"
				+ "\n10 to exit program"
				+ "\n");		
	}
	
	
	/**
	 * Adds the task.
	 * <p>
	 * Get inputs from the user for task title and task priority.
	 * Instantiates a Task using the constructor.
	 * Assign the reference to the Task into the ArrayList named tasks.
	 * If an exception is thrown, the method ends.
	 */
	private void addTask(){
		// Get inputs from the user for a task title and priority
		System.out.println("Please enter a task title"
				+ "\n(Title cannot be empty, max 25 characters)");
		String taskTitle = keyboard.nextLine();
		System.out.println("Please enter a task priority"
				+ "\n(high, medium, low)");
		String taskPriority = keyboard.nextLine();
		// Instantiate a task using the constructor
		try{
			Task task1 = new Task(taskTitle, taskPriority); // Create new task
			tasks.add(task1); // Add task to the end of the list
		}catch (ValidationException v){
			System.err.println(v.getMessage());// Print out ValidationException error message		
		}				
	}
	
	
	/**
	 * Toggles task completion boolean.
	 * <p>
	 * Checks that there are tasks.
	 * Asks user for index of Task in the ArrayList to toggle.
	 * After obtaining the Task from the ArrayList flip the isCompleted boolean.
	 */
	private void toggleTaskComplete(){
		if (tasks.isEmpty()==false){ // Make sure there are tasks
			System.out.println("What is the index of the task you'd like to toggle?");
			try{
				int userIndex = keyboard.nextInt(); // Get index of task
				Task currentTask = tasks.get(userIndex); // Get the task
				currentTask.setIsComplete(!currentTask.getIsComplete()); // Invert the boolean
			}catch (InputMismatchException e){
				System.err.println(e.getMessage());				
			}
			catch(IndexOutOfBoundsException e){
				System.err.println(e.getMessage());
			}
		}
		else{
			System.out.print("Nothing to toggle, no tasks");
		}	
	}
	
	/**
	 * Removes the task.
	 * <p>
	 * Check that there are tasks before doing anything else.
	 * Ask user for index of Task in the ArrayList to remove.
	 * If an exception is thrown, the method ends.
	 */
	private void removeTask(){
		if (tasks.isEmpty()==false){ // Make sure there are tasks
			System.out.println("What is the index of the task you'd like to remove?");
			try{
				int userIndex = keyboard.nextInt(); // Get index of task
				tasks.remove(userIndex); // Remove the task
				System.out.println("Task removed");
			}catch (InputMismatchException e){
				System.err.println(e.getMessage());				
			}
			catch(IndexOutOfBoundsException e){
				System.err.println(e.getMessage());
			}
		}
		else{
			System.out.print("Nothing to remove, no tasks");
		}	
	}
	
	
	/**
	 * Displays information about all tasks.
	 * <p>
	 * Formats tasks using the toString method, then displays them
	 * on-screen along with their index location in the array.
	 * 
	 */
	private void viewTasks(){
		if (tasks.isEmpty()==false){
			int index = 0;
			for (Task t: tasks){ // Enhanced for loop to loop through all elements
				System.out.println(" Index : "+index+t.toString());
				index ++;
			}
		}
		else{
			System.out.print("Nothing to show, no tasks");
		}
	}
	
	/**
	 * Saves tasks found in the ArrayList to a file.
	 * <p>
	 * Each task is separated by a newline,
	 * and the tasks are formatted using the createTabRecord method, which separates the values
	 * of the task with tab characters. This method also overwrites any previous files of the
	 * same name.
	 */
	private void saveTasks(){
		if (tasks.isEmpty()==true){ // Make sure tasks ArrayList isn't empty
			System.out.print("There are no  tasks to save");
		}
		else{
			FileWriter taskList = null;
			int numberOfTasks = 0;
			try {
				taskList = new FileWriter("tasks.txt");
				for(Task t:tasks){
					taskList.append(t.createTabRecord());
					taskList.append('\n');
					numberOfTasks ++;
				}
				System.out.print("Saved "+numberOfTasks+" to file "+System.getProperty("user.dir")+"\tasks.txt");
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
		
	/**
	 * Loads tasks from a text file into an ArrayList.
	 * <p>
	 * Splits each line of the text file into a String array, splitting on the tab character
	 * Creates a new task based on the data split from the line, then appends the task to the
	 * ArrayList.
	 */
	private void loadTasks(){
		BufferedReader taskList = null;		
		try {// Open file with FileReader
			taskList = new BufferedReader (new FileReader("tasks.txt"));
			
			String taskLine;
			int numberOfTasks = 0;
			//Load words into ArrayList<String>
			while ((taskLine = taskList.readLine()) != null){
			    String[] oneTask = taskLine.split("\t");
			    Task task1 = new Task(oneTask[0], oneTask[1], Boolean.valueOf(oneTask[2])); // Create new task
				tasks.add(task1); // Add task to the end of the list 
				numberOfTasks ++;
			}
			System.out.print("Loaded "+numberOfTasks+" to file "+System.getProperty("user.dir")+"\tasks.txt");
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
	
	/**
	 * Sorts the task list using the TaskTitleComparator comparator.
	 * <p>
	 * Checks to make sure that the array is not empty before attempting to compare elements.
	 */
	private void sortByTitle(){
		if (tasks.isEmpty()==false){
			Collections.sort(tasks, new TaskTitleComparator());
			System.out.println("Sorting by Title is complete");
		}
		else{
			System.out.print("Nothing to sort, no tasks");
		}
	}
	
	/**
	 * Sorts the task list using the TaskPriorityComparator comparator.
	 * <p>
	 * Checks to make sure that the array is not empty before attempting to compare elements.
	 */
	private void sortByPriority(){
		if (tasks.isEmpty()==false){
			Collections.sort(tasks, new TaskPriorityComparator());
			System.out.println("Sorting by Priority is complete");
		}
		else{
			System.out.print("Nothing to sort, no tasks");
		}
		
	}
	
	/**
	 * Sorts the task list using the TaskIsCompleteComparator comparator.
	 * <p>
	 * Checks to make sure that the array is not empty before attempting to compare elements.
	 */
	private void sortByIsComplete(){
		if (tasks.isEmpty()==false){
			Collections.sort(tasks, new TaskIsCompleteComparator());
			System.out.println("Sorting by Is-Complete is complete");
		}
		else{
			System.out.print("Nothing to sort, no tasks");
		}
		
	}

}
