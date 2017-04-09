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
	
	public String toString()
	{
		StringBuilder temp = new StringBuilder();
		String startHour = String.format("%02d", startTime.get(Calendar.HOUR_OF_DAY));
		String startMin = String.format("%02d", startTime.get(Calendar.MINUTE));
		String endHour = String.format("%02d", endTime.get(Calendar.HOUR_OF_DAY));
		String endMin = String.format("%02d", endTime.get(Calendar.MINUTE));

		if(client != null)
			temp.append(client.getName() + "\n");
		temp.append(startHour + ":" + startMin + " - " + endHour + ":" + endMin + "\n");
		temp.append("Dr. " + doctor.getName());
		
		return temp.toString();
	}

	public int getID() {
		return id;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public int getStartHour() {
		return startTime.get(Calendar.HOUR_OF_DAY);
	}

	public int getStartMinute() {
		return startTime.get(Calendar.MINUTE);
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public int getEndHour() {
		return endTime.get(Calendar.HOUR_OF_DAY);
	}

	public int getEndMinute() {
		return endTime.get(Calendar.MINUTE);
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}
	
}
