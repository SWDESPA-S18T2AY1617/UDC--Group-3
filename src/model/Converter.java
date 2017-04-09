package model;

import java.io.IOException;

import db.AppointmentDB;
import db.ClientDB;
import db.ClientManager;
import db.DoctorDB;
import db.DoctorManager;

public class Converter {
	public static Appointment toAppointment(AppointmentDB appointmentDB) throws IOException {
		Appointment appointment = new Appointment(CalendarCalculator.getNewCalendar(), CalendarCalculator.getNewCalendar());
		
		if(appointmentDB.getType().equals("Client")){
			ClientManager CM = new ClientManager();
			Client client = toClient(CM.getClient(appointmentDB.getClient_id()));
			appointment.setClient(client);
		}
		else if(appointmentDB.getType().equals("Doctor")){
			DoctorManager DM = new DoctorManager();
			Doctor doctor = toDoctor(DM.getDoctor(appointmentDB.getDoctor_id()));
			appointment.setDoctor(doctor);		
		}
		
		return appointment;
	}
	
	public static Client toClient(ClientDB clientDB) {
		Client client = new Client(clientDB.getName());
		return client;
	}
	
	public static Doctor toDoctor(DoctorDB doctorDB) {
		Doctor doctor = new Doctor(doctorDB.getName());
		return doctor;
	}
}
