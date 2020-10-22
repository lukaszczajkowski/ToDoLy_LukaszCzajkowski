package controller;

import model.Date;
import model.FileManagement;
import model.ListOfTasks;
import model.Task;
import view.Options;
import view.UsersChoiceEvent;
import view.UsersChoiceListener;
import view.View;

public class Controller implements UsersChoiceListener{
	
	private View view;
	private ListOfTasks list;
	private UsersChoiceEvent event;

	public Controller(View view, ListOfTasks list) {
		this.view = view;
		this.list = list;
	}

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
	
	public void addTask() {
		String title =  event.getTitle();
		Date dueDate = event.getDueDate();
		String project = event.getProject();
		
		list.addTask(new Task(title, dueDate, project));
	}
	

}
