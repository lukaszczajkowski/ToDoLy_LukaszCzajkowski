package controller;

import model.Date;
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
		
		if(choice.equals(date)) {
			list.sortByDate();
			displayList();
		} else if (choice.equals(project)) {
			list.sortByProject();
			displayList();
		} else if (choice.equals(update)) {
			int taskId = event.getId();
			//searchForTask(taskId);
		} else if (choice.equals(add)) {
			addTask();
		}
	}
	
	public void displayList() {
		
		if (this.list.getListOfTasks().size() == 0) {
			System.out.println("There are no tasks to display. You can add a new task by pressing 2");
		} else {
			for(Task task: this.list.getListOfTasks()) {
				System.out.println(task.toString());
				System.out.println();
			}			
		}	
		
	}
	
	public void addTask() {
		String title =  event.getTitle();
		Date dueDate = event.getDueDate();
		isCompleted = event.isCompleted();
		String project = event.getProject();
		taskID = event.getTask();
	}
	
//	public Task searchForTask(int taskId) {
//		while()
//	}



}


/*
	UPDATE,
	DONE,
	REMOVE,
	DATE,
	PROJECT
*/
