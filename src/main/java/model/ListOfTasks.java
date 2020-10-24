package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ListOfTasks implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private final List<Task> listOfTasks;
	
	public ListOfTasks() {
		this.listOfTasks = FileManagement.readList();
	}

	//for testing purposes only
	public ListOfTasks(List<Task> list){
		this.listOfTasks = new ArrayList<>();
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
		Optional<Integer> countFromStream = listOfTasks.stream()
		.filter(t -> t.isCompleted())
		.map(t -> 1)
		.reduce((total, count) -> (total + count));

		return countFromStream.isPresent() ? countFromStream.get() : 0;
	}
	
	public void sortByDate() {
		Collections.sort(listOfTasks, new DateCompare());
	}
	
	public void sortByProject() {
		Collections.sort(listOfTasks, new ProjectCompare());
	}
	
	public boolean removeTask(Task task) {
		if(listOfTasks.contains(task)) {
			listOfTasks.remove(task);
			return true;
		}
		return false;
	}
	
	public Task getTaskById(String id) {
		int index = 0;
		
		Task currentTask = null;
		
		while(index < listOfTasks.size()) {
			currentTask = listOfTasks.get(index);
			
			if(currentTask.getTaskID().equals(id)) {
				break;
			}
			
			index++;
		}
		
		return currentTask;
	}
	
	public boolean isIdValid(String id) {
		int index = 0;
		
		while(index < this.getSize()) {
			String currentId = this.getListOfTasks().get(index).getTaskID();
			if(id.equals(currentId)) {
				return true;
			}
			index++;
		}
		return false;
	}
	
	

}
