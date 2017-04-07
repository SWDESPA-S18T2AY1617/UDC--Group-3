package controller;

import java.util.ArrayList;

import model.CalendarModel;

public class MainController {
	private CalendarModel model;
	private ArrayList<ViewController> views;
	
	public MainController() {
		// TODO Auto-generated constructor stub
		model = new CalendarModel();
		views = new ArrayList<>();
		
		initiateViews();
	}
	
	private void initiateViews() {
		// get info from DB for clients and doctors
	}
}
