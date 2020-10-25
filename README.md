# ToDoLy_LukaszCzajkowski
ToDoLy is an application that allows the user to add, edit and remove tasks in the todo list via a command-line interface.
It enables the user to view, sort, remove and update the tasks.

## Usage

WARNING: Do not delete the list.bin file located in the /src/main/resources/ directory! This file is an integral part of the program and stores your todo list!
Go to src/main/java/application/ directory and open App.java file. Then run the application.
The main menu will appear:

```java
>> Welcome to ToDoLy!
>> You have 0 tasks to do and 0 tasks are done!

>> Pick an option:
>> (1) ShowTaskList(by date or project)
>> (2) Add New Task
>> (3) Edit Task(update, mark as done, remove)
>> (4) Save and Quit
```
The list of your tasks will be loaded and the number of tasks to do and done will appear. You will be asked to pick from 4 options. 

### Option 1

If you choose option (1) and press enter, you will be able to view your todo list. The following screen will appear:

```java
To show tasks by date, use command: date
If you want to sort by project, use command: project
```

Simply type in 'date' or 'project' and press enter to display your list of tasks sorted by date or project.
If you type in something else, you will be asked to enter a valid command:

```java
Invalid command, please choose either date or project
```

This will keep on appearing until you enter a right command - 'date' or project'.

If your list is empty, you will see the message:
```java
There are no tasks to display. You can add a new task by pressing 2
```

### Option 2
When you enter 2, you will be able to add a new task. 
You will see the screen below:
```java
Enter the title of the task: 
```
Enter the title of your task and press enter.

Then the following message will appear:
´´´java
Enter the due date in the following format - YYYY-MM-DD
´´´
Type in the due date of your task - keep in mind that the date has to be entered in the YYYY-MM-DD format.
The application will make sure that you have entered a correct date and will ask you to enter the date again if that is not the case.
If you enter your date correctly, you will see the screen:

```java
Enter the name of the project: 
```
Enter the name of your project to which the task belongs and press enter. 
After that, your task will be saved on the list and you will return to the main menu.

### Option 3
If you choose 3 and press enter, the following message will appear:
```java
Please enter the id of the task that you would like to edit.
Tip: you can copy paste the id that you need and press enter!
```
Then you need to enter or paste the ID of the task that you want to edit.
The ID is the first item that appears when you display your list of tasks:

```java
Task ID: 872de4c0-61f1-41f7-a08f-d01a5add1e7c
```

After entering or pasting your task's ID and pressing enter, you will see the message:

```java
What changes would you like to make?
Type 'update' to update the title,'done' to mark the task as done or 'remove' to delete the task
```
Make sure to enter 'update', 'done' or 'remove' to proceed - the program will validate your input and ask you to enter a correct command in case of any mistakes.
Then you press enter.

If you choose 'update' you will see the message:

```java
Please enter your new title:
```
You need to enter the new title of the chosen task and press enter. You will then come back to the main menu.

If you choose 'done' the application marks the indicated task as done. 

If you choose 'remove' the application removes the task from your list

### Option 4
By choosing option 4, you are saving your list to the file and exiting the program.

If the list is successfully saved to the file, you should see the message:
```java
See you next time!
Saving file...
File successfully saved!
Saving file completed!
```

## Troubleshooting

If you see one of the messages below, make sure that the file list.bin is located in its directory /src/main/resources/

```java
Could not find a file: src/main/resources/list.bin
Could not write to file: src/main/resources/list.bin
Could not read from file: src/main/resources/list.bin
Error while reading from file: src/main/resources/list.bin
```

## Class diagram
![Alt text](diagram.png?raw=true "ToDoLy class diagram")
