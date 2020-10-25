package model;

import java.util.Comparator;

/**
 * This class is used to compare tasks by project to sort them.
 * @author lukaszczajkowski
 */
public class ProjectCompare implements Comparator<Task>{

	/**
	 * Takes two tasks and compares them by their project names.
	 * @param task1
	 * @param task2
	 * @return int 0 if the tasks object are equal, 1 if task1 > task2, -1 if task1 < task2
	 */
	@Override
	public int compare(Task task1, Task task2) {
		return task1.getProject().compareTo(task2.getProject());
	}

}
