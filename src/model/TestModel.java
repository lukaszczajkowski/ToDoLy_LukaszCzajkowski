package model;

import java.util.Collections;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Date date1 = new Date("1990-04-21");
		Date date2 = new Date("1988-11-09");
		Date date3 = new Date("1988-11-08");
		
		Task task1 = new Task("Anna", date1, "Birthday");
		Task task2 = new Task("Lukasz", date2, "Death");
		Task task3 = new Task("Other", date3, "Birthday");
		
		
		ListOfTasks sof = new ListOfTasks();
		sof.addTask(task1);
		sof.addTask(task2);
		sof.addTask(task3);
		
		System.out.println("List by projects: ");
		Collections.sort(sof.getListOfTasks(), new ProjectCompare());
		sof.getListOfTasks().forEach(System.out::println);
		
		System.out.println();
		
		System.out.println("List by date: ");
		
		Collections.sort(sof.getListOfTasks(), new DateCompare());
		sof.getListOfTasks().forEach(System.out::println);
		
		FileManagement.saveList(sof.getListOfTasks());
		
		System.out.println();
		
		
		List<Task> readList = FileManagement.readList();
		System.out.println("After reading:");
		
		readList.forEach(System.out::println);
	}

}
