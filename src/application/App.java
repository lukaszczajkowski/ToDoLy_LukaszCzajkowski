package application;

import controller.Controller;
import model.ListOfTasks;
import view.View;

public class App {

	public static void main(String[] args) {
		
		run();
		
	}
	
	public static void run() {
		ListOfTasks list = new ListOfTasks();
		View view = new View(list);
		Controller controller = new Controller(view, list);
		view.displayMainMenu();
	}

}
