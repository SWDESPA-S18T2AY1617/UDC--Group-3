package model;

import java.util.Calendar;

public class Appointment {
	
	public static int ctr = 0;
	
	private final int id;
	private Client client;
	private Doctor doctor;
	private Calendar startTime;
	private Calendar endTime;
	
	public Appointment(Calendar start, Calendar end)
	{
		ctr++;
		
		this.id = ctr;
		client = null;
		startTime = start;
		endTime = end;
	}
	
	public Appointment(Client c, Calendar start, Calendar end)
	{
		ctr++;
		
		this.id = ctr;
		client = c;
		startTime = start;
		endTime = end;
	}

	public int getID() {
		return id;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}
	
}
