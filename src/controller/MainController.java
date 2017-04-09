package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import db.AppointmentDB;
import db.AppointmentManager;
import db.ClientDB;
import db.ClientManager;
import db.DoctorDB;
import db.DoctorManager;
import model.Appointment;
import model.CalendarModel;
import model.Client;
import model.Converter;
import model.Doctor;

public class MainController {
	private CalendarModel model;
	private ArrayList<ViewController> views;
	
	public MainController() {
		// TODO Auto-generated constructor stub
		model = new CalendarModel();
		views = new ArrayList<>();
		
		initiateViews();
		initializeAppointments();
	}
	
	private void initiateViews() {
		// get info from DB for clients and doctors
		initiateSecretary();
		initiateClient();
		initiateDoctor();
	}
	
	private void initiateSecretary() {
		SecretaryController secretaryController = new SecretaryController(this);
		views.add(secretaryController);
	}
	
	private void initiateClient() {
		ClientManager manager = new ClientManager();
		try {
			ArrayList<ClientDB> list = manager.getAllClients();
			for(int i = 0; i < list.size(); i++) {
				Client client = Converter.toClient(list.get(i));
				ClientController clientController = new ClientController(this, client);
				views.add(clientController);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void initiateDoctor() {
		DoctorManager manager = new DoctorManager();
		try {
			ArrayList<DoctorDB> list = manager.getAllDoctors();
			System.out.println(list.size());
			for(int i = 0; i < list.size(); i++) {
				Doctor doctor = Converter.toDoctor(list.get(i));
				model.addDoctor(doctor);
				DoctorController dc = new DoctorController(this, doctor);
				dc.createGUI();
				dc.showGUI();
				views.add(dc);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initializeAppointments() {
		AppointmentManager manager = new AppointmentManager();
		try {
			ArrayList<AppointmentDB> list = manager.getAllAppointments();
			for(int i = 0; i < list.size(); i++) {
				Appointment appointment = Converter.toAppointment(list.get(i));
				int doctorId = list.get(i).getDoctor_id();
				Doctor d = model.getDoctor(doctorId);
				appointment.setDoctor(d);
				if(list.get(i).getClient_id() > 0) {
					for(int j = 0; j < views.size(); j++) {
						if(views.get(j) instanceof ClientController) {
							Client c = ((ClientController) views.get(j)).getClient();
							if(c.getID() == list.get(i).getClient_id()){
								appointment.setClient(c);
							}
						}
					}
				} else if(list.get(i).getClient_id() == -1) {
					
				} else {
					System.out.println("ERROR SETTING CLIENT");
				}
			}
			Appointment.ctr = manager.getCurrAutoInc();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Iterator<Appointment> getAppointments(int year, int month) {
		return model.getAppointments(year, month);
	}
	
	public ArrayList<String> getDoctorNames() {
		return model.getDoctorNames();
	}
	
	//called by client or secretary
	public void cancelAppointment(Calendar start) {
		model.cancelAppointment(start);
	}
	
	//called by doctor
	public void deleteAppointment(Calendar start) {
		model.deleteAppointment(start);
	}
	
	//called by client or secretary
	public boolean setAppointment(Client c, Calendar start, Calendar end) {
		return model.setAppointment(c, start, end);
	}
	
	//called by doctor
	public boolean addAppointment(Appointment appointment) {
		if(model.isAppointmentValid(appointment)) {
			model.addAppointment(appointment);
			return true;
		}
		else {
			return false;
		}
	}
}
