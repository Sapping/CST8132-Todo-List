/* File Name: Task.java
* Course Name: CST8132
* Lab Section: 312
* Student Name: Lucas Melin
* 
* Starter code provided in Assignment3 PDF
* Carolyn MacIsaac (2016) personal communication
* 
*/
package task;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * The class Task models a task.
 * <p>
 * The task has a title, priority (high, medium, low) and a 
 * boolean for whether or not the task is complete.
 * 
 * @author Lucas Melin
 * @version 1.2 April 22, 2016
 */
public class Task {
	
	/** The title. */
	private String title;
	
	/** The priority. */
	private String priority;
	
	/** The is complete. */
	private BooleanProperty isComplete = new SimpleBooleanProperty();
	
	/**
	 *  Default Task constructor.
	 *
	 * @since 1.0
	 */
	public Task(){
		title = "No title";
		priority = "No priority";
	}
	
	/**
	 *  Task constructor.
	 *
	 * @param title the title
	 * @param priority the priority
	 * @throws ValidationException the validation exception
	 * @since 1.0
	 */
	public Task(String title, String priority) throws ValidationException{
		setTitle(title);
		setPriority(priority);
		//this.title = title;
		//this.priority = priority;		
	}
	
	public Task(String title, String priority, boolean isComplete) throws ValidationException{
		setTitle(title);
		setPriority(priority);
		this.isComplete = new SimpleBooleanProperty(isComplete);
	}
	
	/**
	 * Gets the title.
	 *
	 * @return title the title
	 * @since 1.0
	 */
	public String getTitle(){
		return title;
	}
	
	
	/**
	 * 	Setter for the Title field.
	 * <p>
	 *  Verifies that the incoming title is not null,
	 *  not an empty string and not longer than 25 characters.
	 *
	 * @param title the new title
	 * @throws ValidationException the validation exception
	 * @since 1.0
	 */
	public void setTitle(String title) throws ValidationException{
		if (title != null) { // Check that title is not null
			if (title.contains("\t")) { // Check if title contains a tab character
				throw new ValidationException("There was a problem adding a task:"
						+ "\nTitle cannot contain tab characters" + "\nPlease try again.");
			} else if ((title.trim().isEmpty())) { // Check if title is empty
				throw new ValidationException(
						"There was a problem adding a task:" + "\nTitle cannot be empty" + "\nPlease try again.");
			} else if (title.length() > 50) {
				throw new ValidationException("There was a problem adding a task:"
						+ "\nTitle cannot exceed 50 characters" + "\nPlease try again.");
			} else {
				this.title = title.trim();
			} 
		}
		else {
			throw new ValidationException(
					"There was a problem adding a task:" + "\nTitle cannot be empty" + "\nPlease try again.");
		}
	}
	
	/**
	 * Getter for the priority field.
	 *
	 * @return priority
	 * @since 1.0
	 */
	public String getPriority(){
		return priority;
	}
	
	/**
	 * Throws ValidationException
	 * <p>
	 * Verifies that the string is not null and not empty.
	 * Makes string lowercase .
	 * Verifies that it's either high, medium or low.
	 * Set the priority into the priority field.
	 *
	 * @param priority the new priority
	 * @throws ValidationException the validation exception
	 * @since 1.0
	 */
	public void setPriority(String priority) throws ValidationException{
		if ((priority == null)||(priority.isEmpty())){ // Check if title is null or empty
			throw new ValidationException("There was a problem adding a task:"
					+ "\nTitle cannot be empty"
					+ "\nPlease try again.");
		}
		// Check that the priority is either low, medium or high.
		else if ((priority.trim().equalsIgnoreCase("low"))||(priority.trim().equalsIgnoreCase("medium"))||(priority.trim().equalsIgnoreCase("high"))) {
			this.priority = priority.trim(); // If all the checks pass, set the string	
		}
		else if ((priority.trim().isEmpty() == true)){ // If there was no priority given throw a ValidationExceptions
			throw new ValidationException("There was a problem adding a task:"
					+ "\nPriority cannot be empty"
					+ "\nPlease try again.");
		}
		else { // If the priority wasn't valid, throw a ValidationException
			throw new ValidationException("There was a problem adding a task:"
					+ "\nPriority must be high, medium, or low"
					+ "\nPlease try again.");
		}
	}
	
	/**
	 * Gets the is-complete boolean.
	 *
	 * @return isComplete if is-complete
	 */
	public boolean getIsComplete(){
		return isComplete.get();
	}
	
	/**
	 * Sets the checks if is complete.
	 *
	 * @param isComplete the new is-complete boolean
	 */
	public void setIsComplete(boolean isComplete){
		this.isComplete.set(isComplete);
	}
	
	public BooleanProperty completeProperty(){
		return isComplete;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 * Overrides toString method.
	 * <p>
	 * Returns title, priority and whether or not the task is complete
	 * in a string.
	 * 
	 * @return string Combined task details
	 */
	@Override
	public String toString(){
		StringBuilder taskDetails = new StringBuilder();
		taskDetails.append(getTitle());
		taskDetails.append(" (");
		taskDetails.append(getPriority());
		taskDetails.append(") is ");
		if (getIsComplete() == false){
			taskDetails.append("not ");		
		}
		taskDetails.append("complete");
		return taskDetails.toString();
	}
	
	/**
	 *  Returns title, priority and whether or not the task is complete
	 *  in a tab-separated string.
	 *  
	 *  @return string Combined string details
	 */
	public String createTabRecord(){
		StringBuilder tabbedRecord = new StringBuilder();
		tabbedRecord.append(getTitle());
		tabbedRecord.append("\t");
		tabbedRecord.append(getPriority());
		tabbedRecord.append("\t");
		tabbedRecord.append(getIsComplete());
		return tabbedRecord.toString();
	}
	
}