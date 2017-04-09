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
	
	//called by client or secretary
	public void cancelAppointment() {
		
	}
	
	//called by doctor
	public void deleteAppointment() {
		
	}
	
	//called by client or secretary
	public void setAppointment() {
		
	}
	
	//called by doctor
	public void addAppointment() {
		
	}
	
	private void initiateViews() {
		// get info from DB for clients and doctors
	}
}
