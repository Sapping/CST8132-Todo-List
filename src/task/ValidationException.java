/* File Name: ValidationException.java
* Course Name: CST8132
* Lab Section: 312
* Student Name: Lucas Melin
* Date: April 1, 2016
* 
* Purpose: This class creates a new exception, called
* ValidationException, which is then throwable from other
* classes.
* 
* Starter code provided in Assignment3 PDF
* Carolyn MacIsaac (2016) personal communication
*/
package task;
// TODO: Auto-generated Javadoc

/**
 * The Class ValidationException.
 */
public class ValidationException extends Exception{ // should not use generic exception
	
	/**
	 * Instantiates a new validation exception.
	 */
	// Default constructor
	public ValidationException(){
		super("There was a problem when validating data");
	}
	
	/**
	 * Instantiates a new validation exception.
	 *
	 * @param message the message
	 */
	public ValidationException(String message){
		super(message);
	}
	
	/**
	 * Instantiates a new validation exception.
	 *
	 * @param message the message
	 * @param throwable the throwable
	 */
	public ValidationException(String message, Throwable throwable){
		super(message, throwable);
	}
	
	/**
	 * Instantiates a new validation exception.
	 *
	 * @param throwable the throwable
	 */
	public ValidationException(Throwable throwable){
		super(throwable);
	}
}
