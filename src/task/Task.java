/* File Name: Task.java
* Course Name: CST8132
* Lab Section: 312
* Student Name: Lucas Melin
* Date: April 1, 2016
* 
* Purpose: This class models a task, which has a title,
* priority (high, medium, low) and a boolean for whether
* or not the task is complete.
* 
* Starter code provided in Assignment3 PDF
* Carolyn MacIsaac (2016) personal communication
* 
* @version
* @since 1.0
*/
package task;
// TODO: Auto-generated Javadoc

/**
 * The Class Task.
 */
public class Task {
	
	/** The title. */
	private String title;
	
	/** The priority. */
	private String priority;
	
	/** The is complete. */
	private boolean isComplete;
	
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
	 * Complete Task constructor.
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
	
	/**
	 * Getter for the title field.
	 *
	 * @return title
	 * @since 1.0
	 */
	public String getTitle(){
		return title;
	}
	
	
	/**
	 * 	Setter for the Title field.
	 *  Verifies that the incoming title is not null,
	 *  not an empty string and not longer than 25 characters.
	 *
	 * @param title the new title
	 * @throws ValidationException the validation exception
	 * @since 1.0
	 */
	public void setTitle(String title) throws ValidationException{
		if ((title == null)||(title.trim().isEmpty())){ // Check if title is null or empty
			throw new ValidationException("There was a problem adding a task:"
					+ "\nTitle cannot be empty"
					+ "\nPlease try again.");
		}
		else if (title.length() > 25) {
			throw new ValidationException("There was a problem adding a task:"
					+ "\nTitle cannot exceed 25 characters"
					+ "\nPlease try again.");			
		}
		else{
			this.title = title.trim();
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
	 * Verifies that the string is not null, not empty (length)
	 * Make string lowercase (toLowerCase)
	 * Verify that it's either high, medium or low (equals)
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
		else { // If the priority wasn't valid, throw a ValidationException
			throw new ValidationException("There was a problem adding a task:"
					+ "\nPriority must be high, medium, or low"
					+ "\nPlease try again.");
		}
	}
	
	/**
	 * Gets the checks if is complete.
	 *
	 * @return the checks if is complete
	 */
	public boolean getIsComplete(){
		return isComplete;
	}
	
	/**
	 * Sets the checks if is complete.
	 *
	 * @param isComplete the new checks if is complete
	 */
	public void setIsComplete(boolean isComplete){
		this.isComplete = isComplete;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 * Overrides toString method.
	 * Returns title, priority and whether or not the task is complete
	 * in a string.
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
}