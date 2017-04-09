package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import db.AppointmentManager;

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
		AppointmentManager manager = new AppointmentManager();
		try {
			manager.addAppointment(Converter.toAppointmentDB(a));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public boolean setAppointment(Client c, Calendar start, Calendar end) {
		for(Appointment a : appointments) {
			if(a.getStart().compareTo(start) == 0 && a.getEnd().compareTo(end) == 0) {
				if(a.isAvailable()) {
					Appointment appointment = new Appointment(start, end);
					appointment.setClient(c);
					appointment.setDoctor(a.getDoctor());
					appointments.add(appointment);
					appointments.remove(a);
					AppointmentManager manager = new AppointmentManager();
					try {
						manager.addAppointment(Converter.toAppointmentDB(appointment));
						manager.deleteAppointment(Converter.toAppointmentDB(a));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return true;
				} else return false;
			}
		}
		return false;
	}
	
	public boolean isValid(Calendar start, Calendar end) {
		for(Appointment a : appointments) {
			if(a.getStart().compareTo(start) == 0 && a.getEnd().compareTo(end) == 0)
				return true;
		}
		return false;
	}
	
	public void deleteAppointment(Calendar start) {
		for(Appointment a : appointments) {
			if(a.getStart().compareTo(start) == 0) {
				AppointmentManager manager = new AppointmentManager();
				try {
					manager.deleteAppointment(Converter.toAppointmentDB(a));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				appointments.remove(a);
			}
		}
	}
	
	public void cancelAppointment(Calendar start) {
		for(Appointment a : appointments) {
			if(a.getStart().compareTo(start) == 0) {
				AppointmentManager manager = new AppointmentManager();
				a.setClient(null);
				appointments.remove(a);
				Appointment appointment = new Appointment(a.getDoctor(), a.getStart(), a.getEnd());
				try {
					manager.deleteAppointment(Converter.toAppointmentDB(a));
					manager.addAppointment(Converter.toAppointmentDB(appointment));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
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
	
	public Iterator<Appointment> get3MonthAppointments(int year, int month) {
		ArrayList<Appointment> list = new ArrayList<>();
		int nextMonth = CalendarCalculator.getNextMonth(month);
		int nextYear = year;
		int prevMonth = CalendarCalculator.getPrevMonth(month);
		int prevYear = year;
		if(nextMonth == 0)
			nextYear++;
		if(prevMonth == 11)
			nextYear--;
		
		for(int i = 0; i < appointments.size(); i++) {
			if((appointments.get(i).isYear(year) && appointments.get(i).isMonth(month)) || (appointments.get(i).isYear(nextYear) && appointments.get(i).isMonth(nextMonth)) || (appointments.get(i).isYear(prevYear) && appointments.get(i).isMonth(prevMonth)))
				list.add(appointments.get(i));
			
		}
		return list.iterator();
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
				if(appointment.isYear(year) && appointment.isMonth(month) && (filter.contains(appointment.getDoctorName()) || filter.contains(appointment.getClientName()))) {
					a.add(appointment);
				}
			}
		}

		return a.iterator();
	}
	
	public boolean isAppointmentValid(Appointment appointment) {
		if(!appointment.isTimeValid()) {
			return false;
		}
		for(Appointment a : appointments) {
			if(a.isOverlapping(appointment))
				return false;
		}
	    return true;
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
