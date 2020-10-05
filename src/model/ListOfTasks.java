package model;

import java.io.Serializable;
import java.util.Collections;
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
	
	public void sortByDate() {
		Collections.sort(listOfTasks, new DateCompare());
	}
	
	public void sortByProject() {
		Collections.sort(listOfTasks, new ProjectCompare());
	}
	
	public void updateTask(Task task) {
		
	}
	
	public void markAsDone(Task task) {
		
	}
	
	public boolean removeTask(Task task) {
		if(listOfTasks.contains(task)) {
			listOfTasks.remove(task);
			return true;
		}
		return false;
	}
	
	public Task getTaskById(int id) {
		
		int index = 0;
		Task task = null;
		
		while(task.getTaskID() != id && index < listOfTasks.size()) {
			
			task = listOfTasks.get(index);
			index++;
		
		}
		
		return task;
		
	}
	
	public boolean isIdValid(int id) {
		return false;
	}

}
