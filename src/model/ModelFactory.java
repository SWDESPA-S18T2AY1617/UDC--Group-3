package model;

import java.util.Calendar;

public class ModelFactory {
	public static Appointment createAppointment(Doctor doctor, Calendar start) {
		return new Appointment(doctor, start);
	}
	
	public static Appointment createAppointment(Calendar start, Calendar end) {
		return new Appointment(start, end);
	}
	
	public static Appointment createAppointment(Doctor doctor, Calendar start, Calendar end) {
		return new Appointment(doctor, start, end);
	}
	
	public static Client createClient(int id, String name) {
		return new Client(id, name);
	}
	
	public static Doctor createDoctor(int id, String name) {
		return new Doctor(id, name);
	}
}
