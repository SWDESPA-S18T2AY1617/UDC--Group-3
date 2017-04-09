package model;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import db.AppointmentDB;
import db.ClientDB;
import db.ClientManager;
import db.DoctorDB;
import db.DoctorManager;

public class Converter {
	public static Appointment toAppointment(AppointmentDB appointmentDB) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar startTime = CalendarCalculator.getNewCalendar();
		Calendar endTime = CalendarCalculator.getNewCalendar();
		startTime.setTime(sdf.parse(appointmentDB.getStart_time()));
		endTime.setTime(sdf.parse(appointmentDB.getEnd_time()));
		Appointment appointment = ModelFactory.createAppointment(startTime, endTime);
		/*
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
		*/
		return appointment;
	}
	
	public static Client toClient(ClientDB clientDB) {
		Client client = ModelFactory.createClient(clientDB.getClientID(), clientDB.getName());
		return client;
	}
	
	public static Doctor toDoctor(DoctorDB doctorDB) {
		Doctor doctor = ModelFactory.createDoctor(doctorDB.getDoctor_id(), doctorDB.getName());
		return doctor;
	}
}
