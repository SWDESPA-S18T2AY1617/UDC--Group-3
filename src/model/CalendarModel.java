package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class CalendarModel {
	// private ArrayList<Appointment> appointments;
	private ArrayList<Doctor> doctors;
	private ArrayList<Appointment> appointments;

	public CalendarModel() {
		doctors = new ArrayList<>();
		appointments = new ArrayList<>();
	}

	public void addDoctor(Doctor d) {
		doctors.add(d);
	}
	
	public void addAppointment(Appointment a) {
		appointments.add(a);
		sortAppointments();
	}
	
	public void setAppointment() {
		
	}
	
	public void addAppointments(ArrayList<Appointment> a) {
		appointments.addAll(a);
		sortAppointments();
	}
	
	public Iterator<Appointment> getAppointments(int doctorId) {
		ArrayList<Appointment> a = new ArrayList<>();
		for(Appointment appointment : appointments) {
			if(appointment.getID() == doctorId)
				a.add(appointment);
		}

		return a.iterator();
	}

	public Iterator<Appointment> getAllAppointments() {
		return appointments.iterator();
	}

	public void sortAppointments() {
		Collections.sort(appointments, new Comparator<Appointment>() {
			public int compare(Appointment s1, Appointment s2) {
				int n = s1.getYear() - s2.getYear();
				if (n == 0)
					n = s1.getMonth() - s2.getMonth();
				if (n == 0)
					n = s1.getDay() - s2.getDay();
				if (n == 0)
					n = s1.getStartHour() - s2.getStartHour();
				if (n == 0)
					n = s1.getStartMinute() - s2.getStartMinute();
				return n;
			}
		});
	}

}
