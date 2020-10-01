package model;

import java.util.Comparator;

public class ProjectCompare implements Comparator<Task>{

	@Override
	public int compare(Task task1, Task task2) {
		return task1.getProject().compareTo(task2.getProject());
	}

}
