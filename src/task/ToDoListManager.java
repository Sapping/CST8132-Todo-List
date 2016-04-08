/* File Name: ToDoListManager.java
* Course Name: CST8132
* Lab Section: 312
* Student Name: Lucas Melin
* Date: April 1, 2016
* 
* Purpose: This class models a to-do list.
* The user is presented with a menu from which they can choose
* to add a task, toggle the completion state of a task, remove
* a task, and view the information pertaining to all tasks. 
* 
*/
package task;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ToDoListManager {
	private final int ADD_TASK = 1;
	private final int TOGGLE_TASK_COMPLETE = 2;
	private final int REMOVE_TASK = 3;
	private final int VIEW_TASKS = 4;
	private final int EXIT = 5;
	private ArrayList<Task> tasks;
	Scanner keyboard;
	

	public ToDoListManager(){
		keyboard = new Scanner(System.in);
		tasks = new ArrayList<Task>();		
	}
	
	public void runToDoList(){
		int choice = 0;
		do {
			try{//try-catch to handle text entries
				// Print out error message and let loop continue
				showMenu(); // Display the menu
				choice = keyboard.nextInt();
				keyboard.nextLine(); // Clear out line-terminator characters
				switch (choice) {
				case 1:
					addTask(); // Add Task				
					break;
				case 2:
					toggleTaskComplete(); // Toggle task
					break;
				case 3:
					removeTask(); // Remove task
					break;
				case 4:
					viewTasks(); // View tasks			
					break;
				case 5:
					//System.out.print("Case 5");
					break;
				default:
					System.out.print("Unrecognized command");
					break;
				} //end of switch
			}catch (Exception e){
				System.err.println("Please enter integer numbers for menu selection");
				keyboard.nextLine();
			} // end of catch block
		} while (choice!=(5)); // end of loop
		System.out.print("Goodbye.");
		
	}
	
	// Outputs menu on console, only called from runToDoList
	private void showMenu(){
		System.out.print("\n1 to add a task"
				+ "\n2 to toggle a task is completed"
				+ "\n3 to remove a task"
				+ "\n4 to view all tasks"
				+ "\n5 to exit program"
				+ "\n");		
	}
	
	
	/*
	 * Get inputs from the user for task title and task priority
	 * Instantiates a Task using the constructor
	 * Assign the reference to the Task into the ArrayList named tasks
	 * If an exception is thrown, the method ends
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
	
	
	/*
	 * Check that there are tasks before doing anything else
	 * Ask user for index of Task in the ArrayList to toggle
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
	
	/*
	 * Check that there are tasks before doing anything else
	 * Ask user for index of Task in the ArrayList to remove
	 * If an exception is thrown, the method ends
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
	
	
	/*
	 * Check that there are tasks before doing anything else
	 * Loop over the ArrayList calling toString() on each Task
	 * object as well as appending the index number to the output
	 */
	private void viewTasks(){
		if (tasks.isEmpty()==false){
			int index = 0;
			for (Task t: tasks){ // Enhanced for loop to loop through all elements
				System.out.println(t.toString()+" Index : "+index);
				index ++;
			}
		}
		else{
			System.out.print("Nothing to show, no tasks");
		}
	}

}
