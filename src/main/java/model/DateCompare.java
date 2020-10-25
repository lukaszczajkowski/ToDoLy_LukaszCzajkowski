package model;

import java.util.Comparator;

/**
 * This class is used to compare tasks by date in order to sort them.
 * @author lukaszczajkowski
 */
public class DateCompare implements Comparator<Task> {

	/**
	 * Takes two tasks and compares their dates. It uses a helper method
	 * compareDateFactors.
	 * @param task1
	 * @param task2
	 * @return -1 if task1 < task2, 1 if task1 > task2 and 0 it the tasks are equal
	 */
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

	/**
	 * Takes each factor of the date - year, month, day - and compares them with the same factors
	 * from another object
	 * @param factor1
	 * @param factor2
	 * @return -1 if task1 < task2, 1 if task1 > task2 and 0 it the tasks are equal
	 */
	private static int compareDateFactors(int factor1, int factor2) {
		if(factor1 < factor2) {
			return -1;
		} else {
			return 1;
		}
	}

}
