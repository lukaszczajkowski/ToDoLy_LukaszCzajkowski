package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * This class is responsible for managing tasks on the list. Its object stores the todo list
 * and allows to edit, add and remove tasks
 * @author lukaszczajkowski
 */
public class ListOfTasks implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private final List<Task> listOfTasks;

	/**
	 * Constructor - creates a list of tasks after each launching of the program
	 * by loading it from the file using {@link FileManagement} class.
	 */
	public ListOfTasks() {
		this.listOfTasks = FileManagement.readList();
	}

	/**
	 * Creates a new list of tasks based on the given list.
	 * WARNING! This is for test purposes only, it is never used in the program.
	 * @param list
	 */
	//for testing purposes only
	public ListOfTasks(List<Task> list){
		this.listOfTasks = new ArrayList<>();
	}

	/**
	 * Takes a task and adds it to the list.
	 * @param task
	 */
	public void addTask(Task task) {
		listOfTasks.add(task);
	}

	/**
	 * Returns the list of tasks
	 * @return List<Task> list of tasks
	 */
	public List<Task> getListOfTasks(){
		return listOfTasks;
	}

	/**
	 * Returns the size of the list
	 * @return int size of the list
	 */
	public int getSize() {
		return listOfTasks.size();
	}

	/**
	 * Returns the number of the tasks marked as 'done'
	 * @return int - number or completed tasks
	 */
	public int numberTasksCompleted() {
		Optional<Integer> countFromStream = listOfTasks.stream()
		.filter(t -> t.isCompleted())
		.map(t -> 1)
		.reduce((total, count) -> (total + count));

		return countFromStream.isPresent() ? countFromStream.get() : 0;
	}

	/**
	 * Uses {@link DateCompare} to sort the list of tasks by date
	 */
	public void sortByDate() {
		Collections.sort(listOfTasks, new DateCompare());
	}

	/**
	 * Uses {@link ProjectCompare} to sort the list of tasks by project
	 */
	public void sortByProject() {
		Collections.sort(listOfTasks, new ProjectCompare());
	}

	/**
	 * Removes the given tasks from the list
	 * @param task - {@link Task} object to be removed
	 * @return boolean true if removal was successful, false if it was not
	 */
	public boolean removeTask(Task task) {
		if(listOfTasks.contains(task)) {
			listOfTasks.remove(task);
			return true;
		}
		return false;
	}

	/**
	 * Takes an ID and returns the task that the ID is assigned to.
	 * @param id - given ID
	 * @return Task object
	 */
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

	/**
	 * Takes the ID and checks whether it exists in the list of tasks
	 * @param id - given ID
	 * @return true if the ID exists on the list, false if not
	 */
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
