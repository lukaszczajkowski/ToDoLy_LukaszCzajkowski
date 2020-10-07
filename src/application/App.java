package application;

import java.util.Iterator;

import controller.Controller;
import model.ListOfTasks;
import model.Task;
import view.View;

public class App {

	public static void main(String[] args) {

		run();

	}

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
