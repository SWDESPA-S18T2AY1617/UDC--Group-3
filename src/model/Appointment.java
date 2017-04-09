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
		doctor = null;
		client = null;
		startTime = start;
		endTime = end;
	}
	
	public Appointment(Doctor doctor, Calendar start)
	{
		ctr++;
		
		this.id = ctr;
		this.doctor = doctor;
		client = null;
		startTime = start;
		endTime = (Calendar) startTime.clone();
		
		endTime.set(Calendar.MINUTE, endTime.get(Calendar.MINUTE) + 30);
	}
	
	public Appointment(Doctor doctor, Calendar start, Calendar end)
	{
		ctr++;
		
		this.id = ctr;
		this.doctor = doctor;
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
	
	public String toStringDrClient() {
		StringBuilder temp = new StringBuilder();
		temp.append("<html>");
		if(client != null)
			temp.append(client.getName() + "\n" + " || ");
		temp.append("Dr. " + doctor.getName() + "</html>");
		
		return temp.toString();
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public int getID() {
		return id;
	}

	public int getMonth() {
		return startTime.get(Calendar.MONTH);
	}

	public int getDay() {
		return startTime.get(Calendar.DAY_OF_MONTH);
	}

	public int getYear() {
		return startTime.get(Calendar.YEAR);
	}
	
	public int getStartHour() {
		return startTime.get(Calendar.HOUR_OF_DAY);
	}
	
	public int getStartMinute() {
		return startTime.get(Calendar.MINUTE);
	}
	
	public int getEndHour() {
		return endTime.get(Calendar.HOUR_OF_DAY);
	}
	
	public int getEndMinute() {
		return endTime.get(Calendar.MINUTE);
	}

	public boolean isYear(int year) {
		return getYear() == year;
	}

	public boolean isMonth(int month) {
		return getMonth() == month;
	}

	public boolean isDay(int day) {
		return getDay() == day;
	}
	
	public boolean isSameDay(Calendar start, Calendar end) {
		return startTime.compareTo(start) == 0 && endTime.compareTo(end) == 0;
	}

	public Calendar getStart() {
		return (Calendar) startTime.clone();
	}
	
	public Calendar getEnd() {
		return (Calendar) endTime.clone();
	}
	
	public boolean isTimeValid() {
		return startTime.before(endTime);
	}
	
	public Calendar getStartTime() {
		return startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public String getDoctorName() {
		return doctor.getName();
	}
	
	public String getClientName() {
		return client.getName();
	}
	
	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}
	
	public boolean isAvailable() {
		return client == null;
	}
	
	public boolean isOverlapping(Appointment activity) {
		return this.startTime.before(activity.getEnd()) && activity.getStart().before(this.endTime);
	}
}
