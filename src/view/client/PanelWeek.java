package view.client;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.*;

import model.CalendarCalculator;
import values.Month;
import view.doctor.TestAppointment;

public class PanelWeek extends JPanel {

	private JTabbedPane dayTab;
	private PanelSlots[] days;
	private Calendar[] calWeekScope;
	
	public PanelWeek(int year, int month, int day) {
		
		this.setLayout(new GridLayout(1,1));
		this.setPreferredSize(new Dimension(670, 830));
		this.setBackground(Color.decode("#F5E1DF"));
		
		this.initComp();
		this.setTabs(year, month, day);
		this.addPlaceComp();

	}
	
	private void initComp() {
		
		dayTab = new JTabbedPane();
		dayTab.setPreferredSize(new Dimension(670, 830));
		dayTab.setBackground(Color.decode("#dccac8"));
		dayTab.setForeground(Color.WHITE);
		
		days = new PanelSlots[5];
		
		for(int i = 0 ; i < 5; i++) {
			
			days[i] = new PanelSlots();
		}
		
		calWeekScope = new GregorianCalendar[5];
	}
	private void setTabs(int year, int month, int day) {
		int monday = CalendarCalculator.getFirstMondayOfWeek(year, month, day);

		int currMonth = month;
		int maxMonth = CalendarCalculator.getNoDays(year, currMonth);
		for (int i = 1; i < 6; i++) {
			if (monday > day && CalendarCalculator.getDayOfWeek(year, month, day) != Calendar.SUNDAY
					&& month == currMonth) {
				currMonth = CalendarCalculator.getPrevMonth(currMonth);
				if (Month.values()[currMonth] == Month.DECEMBER) {
					year--;
				}
				maxMonth = CalendarCalculator.getNoDays(year, currMonth);
			}
			if (monday + i - 1 > maxMonth || (day == maxMonth && month == currMonth
					&& CalendarCalculator.getDayOfWeek(year, month, day) == Calendar.SUNDAY)) {
				currMonth = CalendarCalculator.getNextMonth(currMonth);
				if (Month.values()[currMonth] == Month.JANUARY) {
					year++;
				}
				maxMonth = CalendarCalculator.getNoDays(year, currMonth);
				monday = (i * -1) + 2;
			}
			String sMonth = Month.values()[currMonth].toShortString();
			String header = sMonth + " " + (monday + i - 1);
			dayTab.add(header, days[i - 1]);
			calWeekScope[i - 1] = new GregorianCalendar(year, currMonth, (monday + i - 1));
		}
	}

	public Calendar[] getWeekScope() {
		return calWeekScope;
	}

	
	private void addPlaceComp() {
		
		this.add(dayTab);
	}	
	
	public void update(int month, int day, int year) {
		
		this.setPanelValuesNull();
	//	this.setPanelValues(month, day, year);
	}
	
	public void setPanelValuesNull() {
		
		for(int i = 0; i < 5; i++) {
			
			for(int j = 0; j < 48; j++) {
				
				days[i].getActivitySlot()[j].setBackground(Color.LIGHT_GRAY);
				days[i].getActivitySlot()[j].removeAll();
				days[i].getActivitySlot()[j].revalidate();
				days[i].getActivitySlot()[j].repaint();
			}
		}
	}
	
	public void setPanelValues(int month, int day, int year) {

	/*	ArrayList<TestAppointment> activityList = new ArrayList<>();

		if (activity != null) {
			while (activity != null && activity.hasNext()) {
				TestAppointment a = activity.next();
				activityList.add(a);
				System.out.println(a.getName());
			}

			for (int i = 0; i < activityList.size(); i++) {
				for(int j = 0; j < calWeekScope.length; j++) {
					if (activityList.get(i).isMonth(calWeekScope[j].get(Calendar.MONTH)) && activityList.get(i).isDay(calWeekScope[j].get(Calendar.DAY_OF_MONTH))
							&& activityList.get(i).isYear(calWeekScope[j].get(Calendar.YEAR))) {
						setEvent(activityList.get(i));
					}
				}
				
			}
		}
	}

	public void setEvent(TestAppointment act) {

		JLabel evnt = new JLabel(act.getName());
		evnt.setFont(new Font("Sans Serif", Font.BOLD, 14));
		evnt.setForeground(Color.white);
		
		for(int i = 0; i < calWeekScope.length; i++) {
			if(act.getYear() == calWeekScope[i].get(Calendar.YEAR) && act.getMonth() == calWeekScope[i].get(Calendar.MONTH) && act.getDay() == calWeekScope[i].get(Calendar.DAY_OF_MONTH)) {
				for (int j = 0; j < 5; j++) {
					int start = act.getStartHour() * 2 + act.getStartMinute() / 30;
					int end = act.getEndHour() * 2 + act.getEndMinute() / 30;

					days[j].getActivitySlot()[start].add(evnt);

					while (start < end) {
						days[j].getActivitySlot()[start].setBackground(act.getColor());
						days[j].getActivitySlot()[start].setBorder(BorderFactory.createEmptyBorder());
						days[j].getActivitySlot()[start].setCursor(new Cursor(Cursor.HAND_CURSOR));
						start++;
						}
					
					}
					
					break;
				}
			}	
		}
	*/	
	}
	
}
