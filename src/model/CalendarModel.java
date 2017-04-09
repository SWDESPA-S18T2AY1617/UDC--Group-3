package model;

import java.util.ArrayList;
import java.util.Calendar;
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
	
	public Doctor getDoctor(int doctorId) {
		for(Doctor d : doctors) {
			if(d.getID() == doctorId)
				return d;
		}
		return null;
	}
	
	public Doctor getDoctorIndex(int i) {
		return doctors.get(i);
	}
	
	public ArrayList<String> getDoctorNames() {
		ArrayList<String> list = new ArrayList<>();
		for(Doctor d : doctors) {
			list.add(d.getName());
		}
		return list;
	}
	
	public boolean setAppointment(Client c, ArrayList<Calendar> list) {
		if(isAllValid(list)) {
			for(int i = 0; i < list.size(); i++) {
				for(int j = 0; j < appointments.size(); j++) {
					if(appointments.get(j).getStart().compareTo(list.get(i)) == 0) {
						appointments.get(j).setClient(c);
						j = appointments.size();
					}
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean isAllValid(ArrayList<Calendar> list) {
		for(int i = 0; i < list.size(); i++) {
			boolean b = false;
			for(int j = 0; j < appointments.size(); j++) {
				if(appointments.get(j).getStart().compareTo(list.get(i)) == 0) {
					if(!appointments.get(i).isAvailable())
						return false;
					b = true;
				}
			}
			if(b == false) 
				return false;
		}
		return true;
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
	
	public Iterator<Appointment> getAppointments(int year, int month) {
		ArrayList<Appointment> list = new ArrayList<>();
		for(int i = 0; i < appointments.size(); i++) {
			if(appointments.get(i).isYear(year) && appointments.get(i).isMonth(month))
				list.add(appointments.get(i));
		}
		return list.iterator();
	}
	
	public Iterator<Appointment> getAppointments(int year, int month, ArrayList<String> filter) {
		ArrayList<Appointment> a = new ArrayList<>();
		if(filter.contains("All")) {
			for(Appointment appointment : appointments) {
				if(appointment.isYear(year) && appointment.isMonth(month))
					a.add(appointment);
			}
		}
		else {
			for(Appointment appointment : appointments) {
				if(appointment.isYear(year) && appointment.isMonth(month) && (filter.contains("Doctor " + appointment.getDoctorID()) || filter.contains("Client " + appointment.getClientID()))) {
					if(filter.contains("Available") && appointment.isAvailable());
						a.add(appointment);
					if(filter.contains("Unavailable") && !appointment.isAvailable());
						a.add(appointment);
				}
			}
		}

		return a.iterator();
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
