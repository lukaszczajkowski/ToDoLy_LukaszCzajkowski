package view;

import java.util.Scanner;

import model.ListOfTasks;

public class View  {
	
	private static final int OPTION_1 = 1;
	private static final int OPTION_2 = 2;
	private static final int OPTION_3 = 3;
	private static final int OPTION_4 = 4;
	
	private ListOfTasks list;
	private UserChoiceAction userChoiceAction;
	private UsersChoiceListener userActionListener;
	private String welcome;
	private int tasksToDo;
	private int tasksCompleted;
	private Scanner scanner;
	private boolean isRunning;

	public View(ListOfTasks list) {
		this.list = list;
		this.tasksToDo = list.getSize();
		this.tasksCompleted = list.numberTasksCompleted();
		this.welcome = 	 ">> Welcome to ToDoLy!\n"
				       	+">> You have " + tasksToDo + " task"+ (tasksToDo == 1 ? "" : "s") 
				       	+" to do and " + tasksCompleted + " tasks are done!\n"
				       	+">> Pick an option:\n"
				       	+">> (1) ShowTaskList(by date or project)\n"
				       	+">> (2) Add New Task\n"
				       	+">> (3) Edit Task(update, mark as done, remove)\n"
				       	+">> (4) Save and Quit\n";
		this.scanner = new Scanner(System.in);
		this.isRunning = true;
	}
	
	public void displayMainMenu() {
		System.out.println(welcome);
		int usersChoice = chooseMainOptions();
		StringBuilder sb = new StringBuilder();
		sb.append("You chose option : ");
		String nextAction = "";
		switch(usersChoice){
			case OPTION_1:
				sb.append(1);
				nextAction = chooseSorting();
				break;
			case OPTION_2:
				sb.append(2);
				break;
			case OPTION_3:
				sb.append(3);
				break;
			case OPTION_4:
				this.isRunning = false;
		}
		
		System.out.println(sb.toString());
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
				
		
		return mainOption;
	}
	
	//TODO make it work!!!
	
	private String chooseSorting() {
		String userInput = "";
		String optionDate = Options.DATE.toString().toLowerCase();
		String optionProject = Options.PROJECT.toString().toLowerCase();
		System.out.println("To show tasks by date, use command: " + optionDate);
		System.out.println("If you want to sort by project, use command: " + optionProject);
		do {
			userInput = scanner.nextLine().toLowerCase();
			
			while(!scanner.hasNext()) {
				scanner.next();
			}
			
			if(userInput.equals(optionDate) || userInput.equals(optionProject)){
				break;
			} else {
				System.out.println("Invalid command, please choose either " + optionDate + " or " + optionProject);
			}
			
		} while (true);
		
		
		System.out.println("You chose: " + userInput);
		
		return userInput;
	}
	
	public void chooseTaskAction() {
		
	}
	
	public void setUserActionListener(UsersChoiceListener userActionListener) {
		this.userActionListener = userActionListener;
	}
	
	

}
