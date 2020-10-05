package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.ListOfTasks;
import model.Task;

public class View  {
	
	private static final int OPTION_1 = 1;
	private static final int OPTION_2 = 2;
	private static final int OPTION_3 = 3;
	private static final int OPTION_4 = 4;
	
	private UsersChoiceListener usersChoiceListener;
	private UsersChoiceEvent event;
	private String welcome;
	private String nextAction;
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
		
		int usersChoice = chooseMainOptions();
		
		
		switch(usersChoice){
		
			case OPTION_1:
				this.event = new UsersChoiceEvent(chooseSorting());
				userChoiceMade();
				break;
			case OPTION_2:
				this.event = createAddEvent();
				userChoiceMade();
				break;
			case OPTION_3:
				break;
			case OPTION_4:
				this.scanner.close();
				System.out.println("See you next time!");
				this.isRunning = false;
				
		}
		
	}
	
	private UsersChoiceEvent createAddEvent() {
		
		this.event.setTitle(inputTaskTitle());
		this.event.setDueDate(inputTaskDueDate());
		this.event.setProject(inputTaskProject());
		
		return null;
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
		
		
		System.out.println("You chose: " + userInput);
		
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
