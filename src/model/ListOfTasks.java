package model;

import java.io.Serializable;
import java.util.List;

public class ListOfTasks implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<Task> listOfTasks;
	
	public ListOfTasks() {
		this.listOfTasks = FileManagement.readList();
	}
	
	public void addTask(Task task) {
		listOfTasks.add(task);
	}
	
	public List<Task> getListOfTasks(){
		return listOfTasks;
	}
	
	public int getSize() {
		return listOfTasks.size();
	}
	
	
	public int numberTasksCompleted() {
		int numTasksCompleted = 0;
		for(var task : listOfTasks) {
			if(task.isCompleted) {
				numTasksCompleted++;
			}
		}
		
		return numTasksCompleted;
	}

}
