package model;

import java.util.ArrayList;
import java.util.Calendar;

import db.AppointmentDB;
import db.ClientDB;

public class Converter {
	public static Appointment toAppointment(AppointmentDB appointmentDB) {
		Appointment appointment = new Appointment(CalendarCalculator.getNewCalendar(), CalendarCalculator.getNewCalendar());
		
		return appointment;
	}
	
	public static Client toClient(ClientDB clientDB) {
		Client client = new Client(clientDB.getClientID(), clientDB.getName());
		return client;
	}
	
	//Collapse to 30 mins interval start calendar
	public static ArrayList<Calendar> getCollapsedCalendar(Calendar start, Calendar end) {
		ArrayList<Calendar> list = new ArrayList<>();
		Calendar bound = (Calendar) start.clone();
		while(bound.before(end)) {
			bound.set(Calendar.MINUTE, bound.get(Calendar.MINUTE) + 30);
			list.add((Calendar) bound.clone());
		}
		
		return list;
	}
}
