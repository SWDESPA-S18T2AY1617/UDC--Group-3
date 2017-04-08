package view.doctor;

import java.awt.Color;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Calendar;


@SuppressWarnings("serial")
public class TestAppointment implements Serializable {
	public static int ctr = 0;
	
	protected final int id;
	protected String name;
	protected Color color;
	protected Calendar start;
	protected Calendar end;
	protected boolean taken;
	
	public static final int EVENT = 0;
	public static final int TASK = 1;
	
	public static final String COLOR_TAKEN = "lightgray";
	public static final String COLOR_AVAILABLE = "green";
	
	public TestAppointment(String name, Calendar start, Calendar end) {
		// TODO Auto-generated constructor stub
		this.id = ctr;
		this.name = name;
		this.color = ColorParser.getColor(COLOR_AVAILABLE);
		this.start = start;
		this.end = end;
		ctr++;
	}	

	public void setName(String name) {
		this.name = name;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setStartDate(Calendar start) {
		this.start = start;
	}
	
	public void setEndDate(Calendar end) {
		this.end = end;
	}

	public TestAppointment getActivity() throws CloneNotSupportedException {
		return (TestAppointment) this.clone();
	}
	
	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public int getMonth() {
		return start.get(Calendar.MONTH);
	}

	public int getDay() {
		return start.get(Calendar.DAY_OF_MONTH);
	}

	public int getYear() {
		return start.get(Calendar.YEAR);
	}
	
	public int getStartHour() {
		return start.get(Calendar.HOUR_OF_DAY);
	}
	
	public int getStartMinute() {
		return start.get(Calendar.MINUTE);
	}
	
	public int getEndHour() {
		return end.get(Calendar.HOUR_OF_DAY);
	}
	
	public int getEndMinute() {
		return end.get(Calendar.MINUTE);
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

	public Calendar getStart() {
		return (Calendar) start.clone();
	}
	
	public Calendar getEnd() {
		return (Calendar) end.clone();
	}
	
	public boolean isTimeValid() {
		return start.before(end);
	}
	
	public boolean isOverlapping(TestAppointment activity) {
		return this.start.before(activity.getEnd()) && activity.getStart().before(this.end);
	}
	
	// TEST METHODS
	public void setTaken(boolean taken) {
		if(taken) {
			this.taken = true;
			this.color = ColorParser.getColor(COLOR_TAKEN);
		} else {
			this.taken = false;
			this.color = ColorParser.getColor(COLOR_AVAILABLE);
		}		
	}
	
	public boolean getTaken() {
		return taken;
	}
	
	/*
	public String[] toStringArr() {
		DecimalFormat d = new DecimalFormat("00");
		
		String[] sArr = new String[4];

		sArr[0] = name;
		if(this instanceof Event)
			sArr[1] = this.getStartHour() + ":" + d.format(this.getStartMinute()) + " - " + this.getEndHour() + ":" + d.format(this.getEndMinute());
		else
			sArr[1] = this.getStartHour() + ":" + d.format(this.getStartMinute());
		
		sArr[2] = ColorParser.getColor(color);
		sArr[3] = "" + id;
		
		return sArr;
	}
	*/
}
