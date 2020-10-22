package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Date;
import model.ListOfTasks;

public class View  {
	
	private static final int OPTION_1 = 1;
	private static final int OPTION_2 = 2;
	private static final int OPTION_3 = 3;
	private static final int OPTION_4 = 4;
	private ListOfTasks list;
	private UsersChoiceListener usersChoiceListener;
	private UsersChoiceEvent event;
	private String welcome;
	private int tasksToDo;
	private int tasksCompleted;
	private Scanner scanner;
	private boolean isRunning;
	private static final List<String> mainOptions = 
							new ArrayList<>(List.of(">> Pick an option:", 
													">> (1) ShowTaskList(by date or project)", 
													">> (2) Add New Task",
													">> (3) Edit Task(update, mark as done, remove)",
													">> (4) Save and Quit\n"));

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
	
	public void displayWelcome() {
		System.out.println(welcome);
	}
	
	public void displayMainMenu() {
		mainOptions.forEach(System.out::println);
		
		
		//put that in a different method
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
	
	private UsersChoiceEvent createEditEvent() {
		String taskId = chooseTaskId();
		String action = chooseEditAction();
		String title = null;
		if(action.equals(Options.UPDATE.toString().toLowerCase())) {
			title = editTaskTitle();
		}
		
		return new UsersChoiceEvent(action, taskId, title);
	}
	
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

	private UsersChoiceEvent createAddEvent() {
	
		String title = inputTaskTitle();
		Date dueDate = inputTaskDueDate();
		String project = inputTaskProject();
		
		return new UsersChoiceEvent (title, dueDate, project, "2");
	}

	private String inputTaskProject() {
		System.out.println("Enter the name of the project: ");
		String projectName = null;
		
		do {
			projectName = this.scanner.nextLine();
		} while(projectName == null);
		
		return projectName;
	}

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
	
	
	public void userChoiceMade() {
		
		fireUsersChoiceEvent(this.event);
		
	}
	
	public void fireUsersChoiceEvent(UsersChoiceEvent event) {
		if(usersChoiceListener != null) {
			usersChoiceListener.choiceMade(event);;
		}
	}
	
	public void setUserChoiceListener(UsersChoiceListener userChoiceListener) {
		this.usersChoiceListener = userChoiceListener;
	}

	public boolean isRunning() {
		return isRunning;
	}
	

}
