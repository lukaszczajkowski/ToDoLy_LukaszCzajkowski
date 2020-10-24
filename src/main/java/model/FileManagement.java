package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManagement {
	
	
	private static final String FILEPATH = "src/main/resources/list.bin";
	
	public static void saveList(List<Task> listOfTasks) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(FILEPATH)))){
			oos.writeObject(listOfTasks);
			System.out.println("File successfuly saved!");
		} catch (FileNotFoundException e) {
			System.err.println("Could not find a file: " + FILEPATH);
		} catch (IOException e) {
			System.err.println("Could not write to file: " + FILEPATH);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static List<Task> readList() {
		List<Task> setOfTasks = new ArrayList<>();
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILEPATH))){
			setOfTasks = (List<Task>) ois.readObject();
		} catch (FileNotFoundException e) {
			System.err.println("Could not find a file: " + FILEPATH);
		} catch (IOException e) {
			System.err.println("Could not read from file: " + FILEPATH);
		} catch (ClassNotFoundException e) {
			System.err.println("Error while reading from file: " + FILEPATH);
		}
		
		return setOfTasks;
	}

}
