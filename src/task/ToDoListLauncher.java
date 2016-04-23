/* File Name: ToDoListLauncher.java
* Course Name: CST8132
* Lab Section: 312
* Student Name: Lucas Melin
* 
* Code provided in Assignment3 PDF
* Carolyn MacIsaac (2016) personal communication
* 
*/

package task;

/**
 * The class ToDoListLauncher is a driver for the ToDoListManager class.
 * 
 * @author Lucas Melin
 * @version 1.2 April 22, 2016
 */
public class ToDoListLauncher {
	
	/**
	 * Main method as required by the JVM
	 * 
	 * @param  args   Standard command line parameters as a string array.
	 */
	public static void main(String[] args) {
		(new ToDoListManager()).runToDoList();
	}
}