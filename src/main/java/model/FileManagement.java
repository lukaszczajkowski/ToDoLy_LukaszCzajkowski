package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *This class is responsible for loading and saving the file with the user's todo list.
 * @author lukaszczajkowski
 */
public class FileManagement {
	
	
	private static final String FILEPATH = "src/main/resources/list.bin";

	/**
	 * Takes the list of tasks and saves them to the list.bin file.
	 * It returns an appropriate message if the file was saved or not.
	 * @param listOfTasks
	 */
	public static void saveList(List<Task> listOfTasks) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(FILEPATH)))){
			oos.writeObject(listOfTasks);
			System.out.println("File successfully saved!");
		} catch (FileNotFoundException e) {
			System.err.println("Could not find a file: " + FILEPATH);
		} catch (IOException e) {
			System.err.println("Could not write to file: " + FILEPATH);
		}
		
	}

	/**
	 * Loads the list of task from the list.bin file.
	 * Returns appropriate messages if the loading succeeded or failed.
	 * @return List - list of tasks
	 */
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
