package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class CalendarModel {
	// private ArrayList<Appointment> appointments;
	private ArrayList<Doctor> doctors;

	public CalendarModel() {
		doctors = new ArrayList<>();
	}

	public void addDoctor(Doctor d) {
		doctors.add(d);
	}
	
	

	public ArrayList<Appointment> getAppointments(int doctorId) {
		ArrayList<Appointment> a = new ArrayList<>();
		for (Doctor doctor : doctors) {
			if (doctor.getID() == doctorId) {
				Iterator<Appointment> iterator = doctor.getAppointments();
				while (iterator.hasNext()) {
					Appointment appointment = iterator.next();
					a.add(appointment);
				}
				return a;
			}
		}

		return a;
	}

	public ArrayList<Appointment> getAllAppointments() {
		ArrayList<Appointment> a = new ArrayList<>();
		for (Doctor doctor : doctors) {
			Iterator<Appointment> iterator = doctor.getAppointments();
			while (iterator.hasNext()) {
				Appointment appointment = iterator.next();
				a.add(appointment);
			}
		}

		sortAppointments(a);
		return a;
	}

	public void sortAppointments(ArrayList<Appointment> appointments) {
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
