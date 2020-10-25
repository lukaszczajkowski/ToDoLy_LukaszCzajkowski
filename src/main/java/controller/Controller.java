package controller;

import model.Date;
import model.FileManagement;
import model.ListOfTasks;
import model.Task;
import view.Options;
import view.UsersChoiceEvent;
import view.UsersChoiceListener;
import view.View;

/**
 * This class is a connector between the view package
 * and the model package.
 * It listens to {@link View} and {@link ListOfTasks}
 * and controls the data flow between these two classes
 *
 * @author lukaszczajkowski
 */
public class Controller implements UsersChoiceListener{

	private final View view;
	private final ListOfTasks list;
	private UsersChoiceEvent event;

	/**
	 * Constructor to create an object of class {@link Controller}
	 * that would listen to the {@link View} and the {@link ListOfTasks}
	 * @param view
	 * @param list
	 */
	public Controller(View view, ListOfTasks list) {
		this.view = view;
		this.list = list;
	}

	/**
	 * Method takes the {@link UsersChoiceEvent} events created by user in {@link View} class
	 * and based on that chooses a corespondent action
	 * @param event - event chosen by the user in {@link View}
	 */
	@Override
	public void choiceMade(UsersChoiceEvent event) {
		
		this.event = event;
		
		String choice = event.getChoice();
		String date = Options.DATE.toString().toLowerCase();
		String project = Options.PROJECT.toString().toLowerCase();
		String update = Options.UPDATE.toString().toLowerCase();
		String done = Options.DONE.toString().toLowerCase();
		String remove = Options.REMOVE.toString().toLowerCase();
		String add = "2";
		String saveAndQuit = "4";
		
		if(choice.equals(date)) {
			list.sortByDate();
			displayList();
		} else if (choice.equals(project)) {
			list.sortByProject();
			displayList();
		} else if (choice.equals(update) || choice.equals(remove) || choice.equals(done)) {
			String taskId = event.getId();
			String title = event.getTitle();
			editTask(taskId, choice, title);
		} else if (choice.equals(add)) {
			addTask();
		} else if(choice.equals(saveAndQuit)) {
			System.out.println("Saving file...");
			FileManagement.saveList(list.getListOfTasks());
			System.out.println("Saving file completed!");
		}
	}

	/**
	 * Accepts details to edit a task object from the {@link Task} class.
	 * Possible options:
	 * - update - when the user wants to update the title of the task
	 * - done - when the user wants to mark the task as done
	 * - remove - when the user wants to remove the task from the list
 	 * @param taskId - id of the task to be edited given by user in the input
	 * @param userAction - update, done or remove - options from the user's input
	 * @param title - new title of the task when option 'update' has been chosen
	 */
	private void editTask(String taskId, String userAction, String title) {
		
		Task task = list.getTaskById(taskId);
		
		if(userAction.equals(Options.UPDATE.toString().toLowerCase())) {
			task.setTitle(title);
		} else if (userAction.equals(Options.DONE.toString().toLowerCase())) {
			task.setCompleted(true);
		} else if (userAction.equals(Options.REMOVE.toString().toLowerCase())) {
			list.removeTask(task);
		}
		
	}

	/**
	 * Displays the list of tasks from the {@link ListOfTasks} class
	 * or "There are no tasks to display. You can add a new task by pressing 2"
	 * if the list is empty
	 */
	public void displayList() {
		
		if (this.list.getListOfTasks().size() == 0) {
			System.out.println("There are no tasks to display. You can add a new task by pressing 2");
		} else {
			this.list.getListOfTasks().forEach(task -> {
				System.out.println(task);
				System.out.println();
			});			
		}	
		
	}

	/**
	 * Adds a new task to the list based on a even created in {@link View}
	 */
	public void addTask() {
		String title =  event.getTitle();
		Date dueDate = event.getDueDate();
		String project = event.getProject();
		
		list.addTask(new Task(title, dueDate, project));
	}
	

}
