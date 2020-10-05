package view;

import model.Date;
import model.Task;

public class UsersChoiceEvent {
	
	private String choice;
	private int id;
	private Task task;
	private String title;
	private Date dueDate;
	boolean isCompleted;
	private String project;
	private int taskID;
	
	public UsersChoiceEvent(String choice) {
		this.choice = choice;
	}
	
	public UsersChoiceEvent(String choice, int id) {
		this.choice = choice;
		this.id = id;
	}
	
	public UsersChoiceEvent(Task task, String title, Date dueDate, String project) {
		this.task = task;
		this.title = title; 
		this.dueDate = dueDate;
		this.isCompleted = false;
		this.project = project;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}
	
	public int getId() {
		return this.id;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	
	
	
}
