package model;

import java.io.Serializable;

public class Task implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String title;
	private Date dueDate;
	boolean isCompleted;
	private String project;
	
	public Task(String title, Date dueDate, String project) {
		this.title = title;
		this.dueDate = dueDate;
		this.project = project;
		this.isCompleted = false;
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

	@Override
	public String toString() {
		return "Task: " + title + 
				"\nDue date: " + dueDate + 
				"\nStatus: " + (isCompleted ? "done!" : "in progress")+ 
				"\nProject: " + project;
	}	
	
	

}
