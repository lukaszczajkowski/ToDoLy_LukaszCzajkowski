package model;

import java.io.Serializable;
import java.util.UUID;

/**
 * The class is a blueprint of every task. It enables to perform basic operations on tasks
 * such as their edition and retrieving their data
 * @author lukaszczajkowski
 */
public class Task implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String title;
	private Date dueDate;
	boolean isCompleted;
	private String project;
	private final String taskId;

	/**
	 * Constructor - creates the {@link Task} object based on the
	 * given title, date and project. It automatically sets the
	 * isCompleted property to false and assigns a unique and random ID
	 * to the task.
	 * @param title
	 * @param dueDate
	 * @param project
	 */
	public Task(String title, Date dueDate, String project) {
		this.title = title;
		this.dueDate = dueDate;
		this.project = project;
		this.isCompleted = false;
		this.taskId = UUID.randomUUID().toString();
	}

	/**
	 * Retrieves the title of the task.
	 * @return String title of the task
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the tasks
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns the {@link Date} object
	 * @return Date object
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * Returns the isCompleted field
	 * @return boolean true if the task is completed, false if not
	 */
	public boolean isCompleted() {
		return isCompleted;
	}

	/**
	 * Sets the task to completed
	 * @param isCompleted
	 */
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	/**
	 * Retrieves the project of the task
	 * @return String project
	 */
	public String getProject() {
		return project;
	}

	/**
	 * Retrieves the ID of the task
	 * @return String ID
	 */
	public String getTaskID() {
		return taskId;
	}


	/**
	 * Returns a string with the task's properties
	 * @return String
	 */
	@Override
	public String toString() {
		return  "Task ID: " + taskId +
				"\nTask: " + title + 
				"\nDue date: " + dueDate + 
				"\nStatus: " + (isCompleted ? "done!" : "in progress") + 
				"\nProject: " + project;
	}	
	
	

}
