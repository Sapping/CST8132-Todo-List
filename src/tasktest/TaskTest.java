/* File: TaskTest.java
 * Author: Stanley Pieda
 * Date: 2015
 * Description: JUnit test for use in Assignment 03
 * Note:
 * Exception handling for tests taken after book Jeff Langr, Andy Hunt and Dave Thomas (2015):
 * Jeff LAngr, Andy Hunt, Dave Thomas. (2015). Pragmatic Unit Testing in Java 8 with JUnit.
 * The Pragmatic Bookshelf, Dallas, Texas and Raleigh, North Carolina.
 * Paper copy: pages 31 to 34.
 * Available via Safari Books online, Algonquin College Library as well:
 * See Chapter 3: Digging Deeper into JUnit Assertions: Three Schools for Expecting Exceptions.
 * Algonquin College Safari Books link:
 * http://proquest.safaribooksonline.com.eztest.ocls.ca/book/programming/java/9781680500769/chapter-3-digging-deeper-into-junit-assertions/f_0030_html?uicode=algonquin
 */
package tasktest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import task.Task;
import task.ValidationException;

public class TaskTest {

	@Test
	public void testTaskNoArgumentsContructorTitle() {
		Task task = new Task();
		String expected = "No title";
		String actual = task.getTitle();
		String message = "Default constructor did not set title correctly";
		assertEquals(message, expected, actual);
	}

	@Test
	public void testTaskNoArgumentsContructorPriority() {
		Task task = new Task();
		String expected = "No priority";
		String actual = task.getPriority();
		String message = "Default constructor did not set priority correctly";
		assertEquals(message, expected, actual); 
	}

	@Test
	public void testTaskNoArgumentsContructorIsComplete() {
		Task task = new Task();
		boolean expected = false;
		boolean actual = task.getIsComplete();
		String message = "Default constructor did not set isComplete correctly";
		assertEquals(message, expected, actual);
	}

	@Test
	public void testTaskTwoParameterConstructorTitle() throws ValidationException{
		String expected = "test title";
		String validPriority = "low";
		Task task = new Task(expected, validPriority);
		String actual = task.getTitle();
		String message = "2 parameter constructor did not set title correctly";
		assertEquals(message, expected, actual);
	}

	@Test
	public void testTaskTwoParameterConstructorPriority() throws ValidationException{
		String expected = "low";
		String validTitle = "a";
		Task task = new Task(validTitle, expected);
		String actual = task.getPriority();
		String message = "2 parameter constructor did not set priority correctly";
		assertEquals(message, expected, actual);
	}

	@Test
	public void testTaskTwoParameterConstructorIsComplete() throws ValidationException{
		boolean expected = false;
		String validTitle = "a";
		String validPriority = "low";
		Task task = new Task(validTitle, validPriority);
		boolean actual = task.getIsComplete();
		String message = "2 parameter constructor did not keep isComplete with default false";
		assertEquals(message, expected, actual);
	}

	@Test
	public void testTaskGetSetTitle() throws ValidationException{
		Task task = new Task();
		String expected = "test title";
		task.setTitle(expected);
		String actual = task.getTitle();
		String message = "get or set title did not work correctly";
		assertEquals(message, expected, actual);
	}

	@Test
	public void testTaskGetSetPriority() throws ValidationException{
		Task task = new Task();
		String expected = "low";
		task.setPriority(expected);
		String actual = task.getPriority();
		String message = "get or set priority did not work correctly";
		assertEquals(message, expected, actual);
	}

	@Test
	public void testTaskGetSetIsCompleted() {
		boolean expected = true;
		Task task = new Task();
		task.setIsComplete(expected);
		boolean actual = task.getIsComplete();
		String message = "get or set isComplete did not work correctly";
		assertEquals(message, expected, actual);
	}

	@Test
	public void testTaskToStringIsCompleteTrue() throws ValidationException{
		Task task = new Task();
		String title = "title";
		String priority = "low";
		String expected = String.format("%s (%s) is complete", title, priority);
		String message = "toString did not return correct string with isComplete true";

		task.setTitle(title);
		task.setPriority(priority);
		task.setIsComplete(true);
		String actual = task.toString();

		assertEquals(message, expected, actual);
	}

	@Test
	public void testTaskToStringIsCompleteFalse() throws ValidationException{
		Task task = new Task();
		String title = "title";
		String priority = "low";
		String expected = String.format("%s (%s) is not complete", title, priority);
		String message = "toString did not return correct string with isComplete false";

		task.setTitle(title);
		task.setPriority(priority);
		task.setIsComplete(false);
		String actual = task.toString();

		assertEquals(message, expected, actual);
	}
	/* these tests fail if the exception in the annotation is not thrown
	 * Jeff Langr, Andy Hunt and Dave Thomas (2015)
	 */
	@Test(expected=ValidationException.class)
	public void testTaskSetTitleThrowsValidationExceptionForNullString() 
			throws ValidationException{
		String badTitle = null;
		Task task = new Task();
		task.setTitle(badTitle);
	}

	@Test(expected=ValidationException.class)
	public void testTaskSetTitleThrowsValidationExceptionForEmptyString() 
			throws ValidationException{
		String badTitle = "";
		Task task = new Task();
		task.setTitle(badTitle);
	}

	@Test(expected=ValidationException.class)
	public void testTaskSetTitleThrowsValidationExceptionForWhiteSpace() 
			throws ValidationException{
		String badTitle = "          ";
		Task task = new Task();
		task.setTitle(badTitle);
	}

	@Test(expected=ValidationException.class)
	public void testTaskSetTitleThrowsValidationExceptionForStringExceeds25Characters() 
			throws ValidationException{
		String badTitle = "12345678901234567890123456";
		Task task = new Task();
		task.setTitle(badTitle);
	}

	@Test(expected=ValidationException.class)
	public void testTaskSetPriorityThrowsValidationExceptionForNullString() 
			throws ValidationException{
		String badPriority = null;
		Task task = new Task();
		task.setPriority(badPriority);
	}

	@Test(expected=ValidationException.class)
	public void testTaskSetPriorityThrowsValidationExceptionForEmptyString() 
			throws ValidationException{
		String badPriority = "";
		Task task = new Task();
		task.setPriority(badPriority);
	}

	@Test(expected=ValidationException.class)
	public void testTaskSetPriorityThrowsValidationExceptionForWhiteSpace() 
			throws ValidationException{
		String badPriority = "          ";
		Task task = new Task();
		task.setPriority(badPriority);
	}

	@Test(expected=ValidationException.class)
	public void testTaskSetPriorityThrowsValidationExceptionForNotUsingHighMediumLow() 
			throws ValidationException{
		String badPriority = "bad-data";
		Task task = new Task();
		task.setPriority(badPriority);
	}
}
