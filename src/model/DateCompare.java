package model;

import java.util.Comparator;

public class DateCompare implements Comparator<Task> {

	@Override
	public int compare(Task task1, Task task2) {
		int task1Year = task1.getDueDate().getYear();
		int task2Year = task2.getDueDate().getYear();

		int task1Month = task1.getDueDate().getMonth();
		int task2Month = task2.getDueDate().getMonth();

		int task1Day = task1.getDueDate().getDay();
		int task2Day = task2.getDueDate().getDay();
		
		if(task1Year != task2Year) {
			return compareDateFactors(task1Year, task2Year);
		} else if(task1Month != task2Month) {
			return compareDateFactors(task1Month, task2Month); 
		} else if(task1Day != task2Day) {
			return compareDateFactors(task1Day, task2Day); 
		} else {
			return 0;
		}
	}
	
	private static int compareDateFactors(int factor1, int factor2) {
		if(factor1 < factor2) {
			return -1;
		} else {
			return 1;
		}
	}

}
