package model;


import java.util.ArrayList;

public class Doctor {
	
	public static int ctr = 0;
	
	private final int id;
	private String name;
	private ArrayList<Appointment> appointments;
	
	public Doctor(String n)
	{
		ctr++;
		
		this.id = ctr;
		name = n;
		appointments = new ArrayList<Appointment>();
	}
	
	public void addAppointment(Appointment a)
	{
		appointments.add(a);
	}
	
	public void cancelAppointment(int id)
	{
		for(int i = 0 ; i < appointments.size(); i++) {
			//not sure this is already okay or if it should still check if the appointment is free (should that be here or the model?)
			if(appointments.get(i).getID() == id) {
				appointments.remove(i);
			}
		}
	}
	
	public String getName() {
		return name;
	}

	public int getID() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
