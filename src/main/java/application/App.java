package application;

import controller.Controller;
import model.ListOfTasks;
import view.View;

/**
 * This is a main class - it is purpose is to launch the program
 * @author lukaszczajkowski
 */

public class App {

	/**
	 * Main method - entry point of the program
	 * @param args
	 */
	public static void main(String[] args) {

		run();

	}

	/**
	 * The method that creates instaces of {@link ListOfTasks}, {@link View}, and {@link Controller} classes
	 * and gets the program running in loop until the user breaks the loop
	 */
	public static void run() {
		
		ListOfTasks list = new ListOfTasks();
		View view = new View(list);
		Controller controller = new Controller(view, list);
		
		view.setUserChoiceListener(controller);

		view.displayWelcome();
		
		while (view.isRunning()) {

			view.displayMainMenu();

		}
	}

}
