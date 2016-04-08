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
public class ValidationException extends Exception{
	// Default constructor
	public ValidationException(){
		super("There was a problem when validating data");
	}
	public ValidationException(String message){
		super(message);
	}
	public ValidationException(String message, Throwable throwable){
		super(message, throwable);
	}
	public ValidationException(Throwable throwable){
		super(throwable);
	}
}
