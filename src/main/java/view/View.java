package view;

import model.Date;
import model.ListOfTasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View  {
	
	private static final int OPTION_1 = 1;
	private static final int OPTION_2 = 2;
	private static final int OPTION_3 = 3;
	private static final int OPTION_4 = 4;
	private final ListOfTasks list;
	private UsersChoiceListener usersChoiceListener;
	private UsersChoiceEvent event;
	private final String welcome;
	private final int tasksToDo;
	private final int tasksCompleted;
	private final Scanner scanner;
	private boolean isRunning;
	private static final List<String> mainOptions = 
							new ArrayList<>(List.of(">> Pick an option:", 
													">> (1) ShowTaskList(by date or project)", 
													">> (2) Add New Task",
													">> (3) Edit Task(update, mark as done, remove)",
													">> (4) Save and Quit\n"));

	/**
	 * Constructor to create a View objects based on the list
	 * from the {@link ListOfTasks} class.
	 * It initializes the list, sets up the number of tasks to do and tasks completed,
	 * the welcome message, runs the {@link Scanner} and sets isRunning to true
	 * @param list - object from {@link ListOfTasks}
	 */
	public View(ListOfTasks list) {
		
		this.list = list;
		this.tasksToDo = list.getSize();
		this.tasksCompleted = list.numberTasksCompleted();
				
		
		this.welcome = 	 ">> Welcome to ToDoLy!\n"
				       	+">> You have " + tasksToDo + " task"+ (tasksToDo == 1 ? "" : "s") 
				       	+" to do and " + tasksCompleted + " tasks are done!\n";
		
		this.scanner = new Scanner(System.in);
		this.isRunning = true;
	}

	/**
	 * Displays the welcome message
	 */
	public void displayWelcome() {
		System.out.println(welcome);
	}

	/**
	 * Displays the main menu and goes directly to
	 * mainMenuOptions method
	 */
	public void displayMainMenu() {
		mainOptions.forEach(System.out::println);

		mainMenuOptions();
	}

	/**
	 * Receives the option chosen by the user from the main menu
	 * and creates a {@link UsersChoiceEvent} object with the information about the
	 * choice made by the user.
	 * It redirects to the following methods based on the choices below:
	 * 1 - chooseSorting
	 * 2 - createAddEvent
	 * 3 - createEditEvent
	 * 4 - creates {@link UsersChoiceEvent} object, closes the scanner,
	 * displays the goodbye message and sets the isRunning false which stops the program
	 * from running.
	 *
	 * Finally, in cases 1-3, it invokes userChoiceMade method
	 */
	private void mainMenuOptions(){
		int usersChoice = chooseMainOptions();

		switch(usersChoice){
			case OPTION_1:
				this.event = new UsersChoiceEvent(chooseSorting());
				break;
			case OPTION_2:
				this.event = createAddEvent();
				break;
			case OPTION_3:
				this.event = createEditEvent();
				break;
			case OPTION_4:
				this.event = new UsersChoiceEvent("4");
				this.scanner.close();
				System.out.println("See you next time!");
				this.isRunning = false;
		}
		userChoiceMade();
	}

	/**
	 * Creates the edit event by redirecting to chooseTaskId method, then to chooseEditAction method.
	 * In case an option 'update' has been chosen, it also redirects to editTaskTitle method.
	 * @return UsersChoiceEvent object with parameters action, taskId and title
	 */
	private UsersChoiceEvent createEditEvent() {
		String taskId = chooseTaskId();
		String action = chooseEditAction();
		String title = null;
		if(action.equals(Options.UPDATE.toString().toLowerCase())) {
			title = editTaskTitle();
		}
		
		return new UsersChoiceEvent(action, taskId, title);
	}

	/**
	 * Asks the user for the ID of the task to be updated/marked as done/removed
	 * and validates the user's input
	 * @return String with the chosen ID
	 */
	private String chooseTaskId() {
	
		String userInput = null;
		
		System.out.println("Please enter the id of the task that you would like to edit.");
		System.out.println("Tip: you can copy paste the id that you need and press enter!");
		
		do {
			userInput = this.scanner.nextLine();
			
			if(list.isIdValid(userInput)) {
				break;
			} else {
				System.out.println("Task ID not found. Please try again!");
			}
			
		} while(true);
		
		return userInput;
	}

	/**
	 * Asks the user for the action to be performed when the option 3 was chosen from the main menu.
	 * It also validates the input.
	 * @return String "done", "update" or "remove"
	 */
	private String chooseEditAction() {
		String userInput = null;
		String optionDone = Options.DONE.toString().toLowerCase();
		String optionUpdate = Options.UPDATE.toString().toLowerCase();		
		String optionRemove = Options.REMOVE.toString().toLowerCase();
		
		System.out.println("What changes would you like to make?");
		System.out.println("Type 'update' to update the title,"
							+ "'done' to mark the task as done "
							+ "or 'remove' to delete the task");
		
		do {	
			userInput = scanner.next().toLowerCase();
			
			if(userInput.equals(optionDone) || userInput.equals(optionUpdate) || userInput.equals(optionRemove)){
				break;
			} else {
				System.out.println("Invalid command, please choose from the following commands:\n" 
									+ optionUpdate + ", " + optionDone + ", " + optionRemove);
			}		
		} while (true);
		
		
		return userInput;
	}

	/**
	 * Asks the user for the new title of the task and validates the input.
	 * @return String new title of the task
	 */
	private String editTaskTitle() {
		System.out.println("Please enter your new title:");
		String newTitle = null;
		scanner.nextLine();
		do {
			newTitle = scanner.nextLine();
			
			if(newTitle.length()>0) {
				break;
			} else {
				System.out.println("Oops! You have not entered the title. Please try again!");
			}
		} while (true);
		
		
		return newTitle;
		
	}

	/**
	 * Creates an event of task addition.
	 * Gets the inputs from inputTaskTitle, inputTaskDueDate and inputTaskProject methods
	 * @return UsersChoiceEvent object
	 */
	private UsersChoiceEvent createAddEvent() {
	
		String title = inputTaskTitle();
		Date dueDate = inputTaskDueDate();
		String project = inputTaskProject();
		
		return new UsersChoiceEvent (title, dueDate, project, "2");
	}

	/**
	 * Asks the user for the name of the project and validates the input.
	 * @return String
	 */
	private String inputTaskProject() {
		System.out.println("Enter the name of the project: ");
		String projectName = null;
		
		do {
			projectName = this.scanner.nextLine();
		} while(projectName == null);
		
		return projectName;
	}
	/**
	 * Asks the user for the name of the project and validates the input.
	 * Then it transforms the string given by the user to the {@link Date} object
	 * @return {@link Date}
	 */
	private Date inputTaskDueDate() {
		System.out.println("Enter the due date in the following format - YYYY-MM-DD");
		String userInput = null;
		
		do {
		
			userInput = this.scanner.nextLine();
			
			if(Date.validateDate(userInput)) {
				break;
			} else {
				System.out.println("Please enter the date in the format YYYY-MM-DD");
			}
			
		} while(true);
		
		Date date = new Date(userInput);
		return date;
	}

	/**
	 * Asks the user for the title of the task and validates the input
	 * @return String - title of the task
	 */
	private String inputTaskTitle() {
		System.out.println("Enter the title of the task: ");
		String title = null;
		do {
			title = scanner.nextLine();
			
			if(title.length()>0) {
				break;
			} else {
				System.out.println("Oops! You have not entered the title. Please try again!");
			}
		} while (true);
		return title;
	}

	/**
	 * Ask the user for choosing one of the options from the main menu
	 * @return int - main option
	 */
	private int chooseMainOptions() {
		int mainOption = 0;
		
		do{
			
			while(!scanner.hasNextInt()) {
				System.out.println("This is not a number. \nPlease enter a number between 1 and 4");
				scanner.next();
			}
			mainOption = scanner.nextInt();
			
			if(mainOption > 0 && mainOption < 5) {
				break;
			} else {
				System.out.println("Please choose between options 1, 2, 3 and 4");
			}
		} while(true);
				
		scanner.nextLine();
		return mainOption;
	}

	/**
	 * Asks the user to choose the sorting method from by date and by project
	 * @return String - sorting method chosen by the user (date/project)
	 */
	private String chooseSorting() {
		String userInput = "";
		String optionDate = Options.DATE.toString().toLowerCase();
		String optionProject = Options.PROJECT.toString().toLowerCase();
		
		System.out.println("To show tasks by date, use command: " + optionDate);
		System.out.println("If you want to sort by project, use command: " + optionProject);
		
		do {
			
			userInput = scanner.next().toLowerCase();
			
			if(userInput.equals(optionDate) || userInput.equals(optionProject)){
				break;
			} else {
				System.out.println("Invalid command, please choose either " + optionDate + " or " + optionProject);
			}
				
		} while (true);
		
		
		return userInput;
	}

	/**
	 * Fires the {@link UsersChoiceEvent}
	 */
	public void userChoiceMade() {
		
		fireUsersChoiceEvent(this.event);
		
	}

	/**
	 * Checks whether there is some active {@link UsersChoiceEvent} object and launches
	 * a choiceMade method on the current one if not
	 * @param event of class {@link UsersChoiceEvent}
	 */
	public void fireUsersChoiceEvent(UsersChoiceEvent event) {
		if(usersChoiceListener != null) {
			usersChoiceListener.choiceMade(event);
		}
	}

	/**
	 * Sets the {@link UsersChoiceListener} object
	 * @param userChoiceListener of class {@link UsersChoiceListener}
	 */
	public void setUserChoiceListener(UsersChoiceListener userChoiceListener) {
		this.usersChoiceListener = userChoiceListener;
	}

	/**
	 * Returns the value that determines if the program is running or not.
	 * If isRunning is true, then the program still runs, if false it stops running.
	 * @return boolean isRunning
	 */
	public boolean isRunning() {
		return isRunning;
	}
	

}
