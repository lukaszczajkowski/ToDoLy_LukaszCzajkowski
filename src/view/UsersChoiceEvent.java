package view;

import model.Date;

public class UsersChoiceEvent {
	
	private String choice;
	private String id;
	private String title;
	private Date dueDate;
	private String project;
	
	public UsersChoiceEvent(String choice) {
		this.choice = choice;
	}
	
	public UsersChoiceEvent(String choice, String id, String title) {
		this.choice = choice;
		this.id = id;
		this.title = title;
	}
	
	public UsersChoiceEvent(String title, Date dueDate, String project, String usersChoice) {
		this.title = title; 
		this.dueDate = dueDate;
		this.project = project;
		this.choice = usersChoice;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}
	

	public String getTitle() {
		return this.title;
	}


	public Date getDueDate() {
		return dueDate;
	}

	public String getProject() {
		return project;
	}
	
	public String getId() {
		return id;
	}
	
	
}
