package controller;

import model.ListOfTasks;
import model.Model;
import view.UsersChoiceListener;
import view.UserChoiceAction;
import view.View;

public class Controller implements UsersChoiceListener{
	
	private View view;
	private ListOfTasks list;

	public Controller(View view, ListOfTasks list) {
		this.view = view;
		this.list = list;
	}


	@Override
	public void taskListRequest(UserChoiceAction event) {
		// TODO Auto-generated method stub
		
	}

}
