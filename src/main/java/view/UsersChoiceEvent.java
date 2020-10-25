package view;

import model.Date;

/**
 * Objects of these class contain information about the data about the task
 * that user wants to add or update in {@link View} class and are used in the
 * {@link controller.Controller} class
 * @author lukaszczajkowski
 */
public class UsersChoiceEvent {
	
	private String choice;
	private String id;
	private String title;
	private Date dueDate;
	private String project;

	/**
	 * Constructor to create an event based on the user's choice
	 * @param choice - String - user's choice
	 */
	public UsersChoiceEvent(String choice) {
		this.choice = choice;
	}

	/**
	 * Constructor to create an event based on the user's choice,
	 * chose ID of the task and the title of the task
	 * @param choice
	 * @param id
	 * @param title
	 */
	public UsersChoiceEvent(String choice, String id, String title) {
		this.choice = choice;
		this.id = id;
		this.title = title;
	}

	/**
	 * Constructor to create an event based on the user's choice, the title
	 * of the task, the due date of the task and the name of the project of the task
	 * @param title
	 * @param dueDate
	 * @param project
	 * @param usersChoice
	 */
	public UsersChoiceEvent(String title, Date dueDate, String project, String usersChoice) {
		this.title = title; 
		this.dueDate = dueDate;
		this.project = project;
		this.choice = usersChoice;
	}

	/**
	 * Returns the choice from the main menu
	 * 1 - ShowTaskList(by date or project)
	 * 2 - Add New Task
	 * 3 - Edit Task(update, mark as done, remove)
	 * 4 - Save and Quit
	 * @return String choice made by the user in {@link View}
	 */
	public String getChoice() {
		return choice;
	}

	/**
	 * Returns the title of the task updated or created
	 * by the user
	 * @return String title of the task
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Returns the due date of the task created by the user
	 * @return {@link Date} object with the due date the a task
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * Returns the name of the project for the task created by the user
	 * @return String name of the project created by the user
	 */
	public String getProject() {
		return project;
	}

	/**
	 * Returns the unique ID of the task created by the user, generated
	 * in the {@link model.Task} class.
	 * The ID can be used to delete, mark as done or update the task
	 * @return String unique ID of the task
	 */
	public String getId() {
		return id;
	}
	
	
}
