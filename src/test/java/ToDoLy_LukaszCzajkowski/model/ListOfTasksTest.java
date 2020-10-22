package test.java.ToDoLy_LukaszCzajkowski.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Task;
import model.Date;
import model.ListOfTasks;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

public class ListOfTasksTest {
    ListOfTasks list;

    @BeforeEach
    void setUp(){
        List<Task> newList = new ArrayList<>();
        this.list = new ListOfTasks(newList);
    }

    @AfterEach
    void tearDown(){
        this.list = null;
    }

    @Test
    void addTaskTest(){
        String title = "title";
        Date date = new Date("2020-10-30");
        String project = "project";
        Task task = new Task(title, date, project);
        
        list.addTask(task);
        list.addTask(task);

        int expectedSize = 2;
        int actualSize = list.getSize();

        assertEquals("returned value should be 2", expectedSize, actualSize);

        String expectedTitle = "title";
        String actualTitle = list.getListOfTasks().get(0).getTitle();

        assertEquals("returned value should be title", expectedTitle, actualTitle);
    }

    @Test
    void numberTasksCompletedTest(){

        String title = "title";
        Date date = new Date("2020-10-30");
        String project = "project";
        Task task = new Task(title, date, project);
        list.addTask(task);
        list.getListOfTasks().get(0).setCompleted(false);

        int expected = 0;
        int actual = list.numberTasksCompleted();

        System.out.println("Actual = " + actual);

        assertEquals("returned value should be 0", expected, actual);

        list.getListOfTasks().get(0).setCompleted(true);

        expected = 1;

        actual = list.numberTasksCompleted();

        assertEquals("returned value should be 1", expected, actual);
    }
    
    @Test
    void removeTaskTest(){
        String title = "title";
        Date date = new Date("2020-10-30");
        String project = "project";
        Task task = new Task(title, date, project);
        
        list.addTask(task);
        list.removeTask(task);

        int expected = 0;
        int actual = list.getSize();

        assertEquals("returned value should be 0", expected, actual);
    }

    @Test
    void sortByDateTest(){
        String title = "title";
        Date date = new Date("2020-10-30");
        String project = "project";
        Task task = new Task(title, date, project);

        String titleTwo = "title";
        Date dateTwo = new Date("2020-10-29");
        String projectTwo = "project";
        Task taskTwo = new Task(titleTwo, dateTwo, projectTwo);

        list.addTask(task);
        list.addTask(taskTwo);

        list.sortByDate();
        
        String actual = list.getListOfTasks().get(0).getDueDate().toString();
        String expected = "2020-10-29";

        assertEquals(expected, actual);
    }

    @Test
    void sortByProjectTest(){
        String title = "title";
        Date date = new Date("2020-10-30");
        String project = "project";
        Task task = new Task(title, date, project);

        String titleTwo = "title";
        Date dateTwo = new Date("2020-10-29");
        String projectTwo = "project";
        Task taskTwo = new Task(titleTwo, dateTwo, projectTwo);

        String titleThree = "title3";
        Date dateThree = new Date("2020-10-29");
        String projectThree = "project3";
        Task taskThree = new Task(titleThree, dateThree, projectThree);

        list.addTask(task);
        list.addTask(taskThree);
        list.addTask(taskTwo);

        list.sortByProject();

        String[] actual ={  list.getListOfTasks().get(0).toString(), 
                            list.getListOfTasks().get(1).toString(),
                            list.getListOfTasks().get(2).toString()
                        };

        String[] expected = {task.toString(), taskTwo.toString(), taskThree.toString()};

        assertArrayEquals(expected, actual);
    }

    @Test
    void getSizeTest(){
        int actual = list.getSize();
        int expected = 0;
        assertEquals(expected, actual);

        String title = "title";
        Date date = new Date("2020-10-30");
        String project = "project";
        Task task = new Task(title, date, project);

        list.addTask(task);

        actual = list.getSize();
        expected = 1;

        assertEquals(expected, actual);
    }

}
