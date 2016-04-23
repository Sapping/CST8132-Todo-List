/* File Name: ValidationException.java
* Course Name: CST8132
* Lab Section: 312
* Student Name: Lucas Melin
* 
* Starter code provided in Assignment3 PDF
* Carolyn MacIsaac (2016) personal communication
*/

package task;

/**
 * The Class ValidationException creates a new exception, called
* ValidationException, which is then throwable from other
* classes.
* 
* @author Lucas Melin
* @version 1.2 April 22, 2016
 */
public class ValidationException extends Exception{
	
	/**
	 * Instantiates a new validation exception.
	 * <p>
	 * Default constructor.
	 */
	public ValidationException(){
		super("There was a problem when validating data");
	}
	
	/**
	 * Instantiates a new validation exception.
	 *
	 * @param message The message
	 */
	public ValidationException(String message){
		super(message);
	}
	
	/**
	 * Instantiates a new validation exception.
	 *
	 * @param message The message
	 * @param throwable The throwable
	 */
	public ValidationException(String message, Throwable throwable){
		super(message, throwable);
	}
	
	/**
	 * Instantiates a new validation exception.
	 *
	 * @param throwable The throwable
	 */
	public ValidationException(Throwable throwable){
		super(throwable);
	}
}
