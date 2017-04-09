package view.doctor;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.LineBorder;

import com.sun.javafx.geom.transform.GeneralTransform3D;

import controller.DoctorController;
import model.Appointment;
import model.CalendarCalculator;
//import model.Activity;
//import model.Event;
import values.DayHeader;
import values.Month;

public class PanelWeek extends JPanel {

	private JPanel[] activity;
	private JPanel[] time;
	private JPanel[] panelDay;
	private JPanel[] panelHeader;

	private JButton[] arrBtnRemove;
	
	private JPanel panelHeaderContainer;
	private JPanel panelWeek;
	private JPanel panelTable;

	private JPanel[] timeSlot;
	private JPanel[] activitySlot;
	private JLabel[] seeTime;
	private Calendar[] calWeekScope;

	private JScrollPane scrollAct;

	private GridBagConstraints[] gbc;
	private GridBagConstraints[] gb;
	
	private DoctorController dController;
	public static final String COLOR_TAKEN = "lightgray";
	public static final String COLOR_AVAILABLE = "green";

	public PanelWeek(DoctorController dController, int year, int month, int day) {
		
		this.dController = dController;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.setPreferredSize(new Dimension(670, 830));

		// Initialize panels: panelDays
		this.initComp();
		this.initTimeSlot();
		this.initActivitySlot();
		this.initButtons();
		this.addPlaceComp();
		this.setHeaderValues(year, month, day);
	}

	private void initComp() {

		int trSize = 60;
		int activitySize = 30;

		activity = new JPanel[5];
		time = new JPanel[5];
		panelDay = new JPanel[5];
		panelHeader = new JPanel[6];
		calWeekScope = new GregorianCalendar[5];

		for (int i = 0; i < 5; i++) {
			activity[i] = new JPanel();
			activity[i].setBorder(new LineBorder(new Color(0, 0, 0)));
			activity[i].setBackground(Color.WHITE);

			time[i] = new JPanel();
			time[i].setBorder(new LineBorder(new Color(0, 0, 0)));
			time[i].setBackground(Color.WHITE);

			panelDay[i] = new JPanel();
			panelDay[i].setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

			panelDay[i].add(time[i]);
			panelDay[i].add(activity[i]);

			// Layout for the time "column"
			GridBagLayout activity_time = new GridBagLayout();
			activity_time.columnWidths = new int[] { 90 };
			activity_time.rowHeights = new int[] { trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize,
					trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize,
					trSize, trSize, trSize, trSize };
			activity_time.columnWeights = new double[] { 0.5 };
			activity_time.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
					1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
			time[i].setLayout(activity_time);

			// Layout for the activity "column"
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[] { 110 };
			gridBagLayout.rowHeights = new int[] { activitySize, activitySize, activitySize, activitySize, activitySize,
					activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize,
					activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize,
					activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize,
					activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize,
					activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize,
					activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize,
					activitySize };
			gridBagLayout.columnWeights = new double[] { 1.0 };
			gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
					1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
					1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
			activity[i].setLayout(gridBagLayout);
		}

		for (int i = 0; i < panelHeader.length; i++) {

			GridBagLayout headerGBL = new GridBagLayout();
			if (i == 0)
				headerGBL.columnWidths = new int[] { 95 };
			else
				headerGBL.columnWidths = new int[] { 110 };
			headerGBL.rowHeights = new int[] { 30 };
			headerGBL.columnWeights = new double[] { 1.0 / 5 };
			headerGBL.rowWeights = new double[] { 1.0 };

			panelHeader[i] = new JPanel();
			panelHeader[i].setLayout(headerGBL);
			if (i != 0) {
				panelHeader[i].add(new JLabel(""));
				//panelHeader[i].setBorder(BorderFactory.createEtchedBorder());
			}

		}

		panelWeek = new JPanel();
		panelWeek.setLayout(new BoxLayout(panelWeek, BoxLayout.X_AXIS));
		panelHeaderContainer = new JPanel();
		panelHeaderContainer.setLayout(new BoxLayout(panelHeaderContainer, BoxLayout.X_AXIS));

		panelWeek.add(time[0]);
		panelHeaderContainer.add(panelHeader[0]);
		for (int i = 0; i < activity.length; i++) {
			panelWeek.add(activity[i]);
			panelHeaderContainer.add(panelHeader[i + 1]);
		}

		panelTable = new JPanel();
		panelTable.setLayout(new BoxLayout(panelTable, BoxLayout.Y_AXIS));

		panelTable.setBackground(Color.WHITE);
		panelTable.add(panelHeaderContainer);
		panelTable.add(panelWeek);
		panelTable.setMinimumSize(new Dimension(1500, 600));
		panelTable.setOpaque(true);

		scrollAct = new JScrollPane(panelTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollAct.getViewport().setBackground(Color.WHITE);
		scrollAct.getVerticalScrollBar().setUnitIncrement(20);

	}

	private void initTimeSlot() {
		String[] milTime = new String[24];
		timeSlot = new JPanel[24];
		seeTime = new JLabel[24];
		gb = new GridBagConstraints[24];

		for (int i = 0; i < time.length; i++) {
			for (int j = 0; j < 24; j++) {
				if (j == 0)
					milTime[j] = "0:00";
				else
					milTime[j] = j + ":00";
				Border border;

				timeSlot[j] = new JPanel();

				border = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK);

				timeSlot[j].setBorder(border);
				timeSlot[j].setBackground(Color.WHITE);

				seeTime[j] = new JLabel(milTime[j]);
				seeTime[j].setFont(new Font("Sans Serif", Font.PLAIN, 14));
				timeSlot[j].add(seeTime[j]);

				gb[j] = new GridBagConstraints();
				gb[j].insets = new Insets(0, 0, 0, 0);
				gb[j].fill = GridBagConstraints.BOTH;
				gb[j].gridx = 0;
				gb[j].gridy = j;
				time[i].add(timeSlot[j], gb[j]);
			}
		}
	}

	private void initActivitySlot() {

		activitySlot = new JPanel[48 * 5];
		gbc = new GridBagConstraints[48 * 5];

		
		for (int i = 0; i < activity.length; i++) {
			for (int j = 0; j < 48; j++) {
				Border border = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY);

				activitySlot[48 * i + j] = new JPanel();

				activitySlot[48 * i + j].setBorder(border);
				activitySlot[48 * i + j].setBackground(Color.WHITE);
				// activitySlot[48 * i + j].add(new JLabel("muaha " + i + " " +
				// (48 * i + j)));

				gbc[48 * i + j] = new GridBagConstraints();
				gbc[48 * i + j].insets = new Insets(0, 0, 0, 0);
				gbc[48 * i + j].fill = GridBagConstraints.BOTH;
				gbc[48 * i + j].gridx = 0;
				gbc[48 * i + j].gridy = j;

				activity[i].add(activitySlot[48 * i + j], gbc[48 * i + j]);
			}
		}
	}

	private void initButtons() {
		arrBtnRemove = new JButton[48 * 5];
		
		for (int i = 0; i < activity.length; i++) {
			for (int j = 0; j < 48; j++) {
				final int column = i;
				final int index = j;
				arrBtnRemove[48 * i + j] = new JButton(" ... ");
				arrBtnRemove[48 * i + j].setFont(new Font("Sans Serif", Font.BOLD, 12));
				arrBtnRemove[48 * i + j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));	
				arrBtnRemove[48 * i + j].setForeground(Color.WHITE);
				arrBtnRemove[48 * i + j].setContentAreaFilled(false);
				arrBtnRemove[48 * i + j].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						JButton b = (JButton) e.getSource();
						b.setVisible(true);
					}
					@Override
					public void mouseExited(MouseEvent e) {
						JButton b = (JButton) e.getSource();
						b.setVisible(false);
					}
				});
				arrBtnRemove[48 * i + j].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						PanelModifyDelete pmd = new PanelModifyDelete(2017, 10, 29);
						int choice = JOptionPane.showConfirmDialog(null, pmd, "Modify Activity", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
						
						if(pmd.toDelete()) {
							// TODO Delete selected appointment
							
							// Appointment date
							Calendar date = calWeekScope[column];
							
							// Original starting time
							int timeH = index / 2;
							int timeM = 30 * (index % 2);
							
							System.out.println(date.toString());
							
							// Get appointment from list which matches the above starting time and date
							// -> then delete the appointment
							
						} else {
							if(choice == JOptionPane.OK_OPTION) {
								if(pmd.isValidTime()) {
									// TODO Delete selected appointment, then add new appointment
									
									// Appointment date
									Calendar date = calWeekScope[column];
									// Original starting time
									int timeH = index / 2;
									int timeM = 30 * (index % 2);
									
									// Get appointment from list which matches the above starting time and date
									/* appointment.newDate( */pmd.getSpinDate();
									/* appointment.newStartTime( */pmd.getFromTime();
									/* appointment.newEndTime( */pmd.getToTime();
									
								} else {
									JOptionPane.showMessageDialog(null, "Invalid time input", "Cannot modify appointment", JOptionPane.WARNING_MESSAGE);
								}
							}
						} 
					}
				});
			}
		}
		
	}
	
	private void setHeaderValues(int year, int month, int day) {
		int monday = CalendarCalculator.getFirstMondayOfWeek(year, month, day);

		int currMonth = month;
		int maxMonth = CalendarCalculator.getNoDays(year, currMonth);
		for (int i = 1; i < panelHeader.length; i++) {
			panelHeader[i].removeAll();
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
			JLabel lblHeader = new JLabel(sMonth + " " + (monday + i - 1));
			lblHeader.setFont(new Font("Sans Serif", Font.PLAIN, 14));
			panelHeader[i].add(lblHeader);
			calWeekScope[i - 1] = new GregorianCalendar(year, currMonth, (monday + i - 1));
		}
	}

	public Calendar[] getWeekScope() {
		return calWeekScope;
	}

	private void addPlaceComp() {
		this.add(scrollAct);
	}

	public void update(int month, int day, int year, Iterator<Appointment> activity) {
		this.setPanelValuesNull();
		this.setHeaderValues(year, month, day);
		this.setPanelValues(month, day, year, activity);
	}

	public void setPanelValues(int month, int day, int year, Iterator<Appointment> activity) {
		
		ArrayList<Appointment> activityList = new ArrayList<>();

		if (activity != null) {
			while (activity != null && activity.hasNext()) {
				Appointment a = activity.next();
				activityList.add(a);
				System.out.println(a.toString());
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

	public void setEvent(Appointment act) {

		JLabel evnt = new JLabel(act.toString());
		evnt.setFont(new Font("Sans Serif", Font.BOLD, 14));
		evnt.setForeground(Color.white);

		for(int i = 0; i < calWeekScope.length; i++) {
			if(act.getYear() == calWeekScope[i].get(Calendar.YEAR) && act.getMonth() == calWeekScope[i].get(Calendar.MONTH) && act.getDay() == calWeekScope[i].get(Calendar.DAY_OF_MONTH)) {
				
				int start = act.getStartHour() * 2 + act.getStartMinute() / 30;
				int end = act.getEndHour() * 2 + act.getEndMinute() / 30;

				activitySlot[48 * i + start].add(evnt);
				
				StringBuilder sb = new StringBuilder(act.toString());
				
				if(dController.getDoctor().getName().equalsIgnoreCase(act.getDoctorName())) {
					activitySlot[48 * i + start].add(arrBtnRemove[48 * i + start]);
					arrBtnRemove[48 * i + start].setVisible(false);
				}
				
				final int originalIndex = 48 * i + start;
				
				while (start < end) {
					if(act.isAvailable()) {
						activitySlot[48 * i + start].setBackground(ColorParser.getColor(COLOR_AVAILABLE));
					} else activitySlot[48 * i + start].setBackground(ColorParser.getColor(COLOR_TAKEN));				
					
					activitySlot[48 * i + start].setBorder(BorderFactory.createEmptyBorder());
					if(activitySlot[48 * i + start].getBackground() != ColorParser.getColor(COLOR_TAKEN) && dController.getDoctor().getName().equalsIgnoreCase(act.getDoctorName())) {
						
						activitySlot[48 * i + start].addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								arrBtnRemove[originalIndex].setVisible(true);
							}
							@Override
							public void mouseExited(MouseEvent e) {
								arrBtnRemove[originalIndex].setVisible(false);
							}
						});
					}
					start++;
				}
				
				break;
			}
		}	
	}

	public void showAvailable(boolean show) {
		if(!show) {
			for(JPanel j : activitySlot) {
				if(j.getBackground() == Color.GREEN) {
					j.setOpaque(false);
					j.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
				}
			}
			repaint();
		} else {
			for(JPanel j : activitySlot) {
				if(j.getBackground() == Color.GREEN) {
					j.setOpaque(true);
					j.setBorder(BorderFactory.createEmptyBorder());
				}
			}
			repaint();
		}
	}
	
	public void showUnavailable(boolean show) {
		if(!show) {
			for(JPanel j : activitySlot) {
				if(j.getBackground() == Color.LIGHT_GRAY) {
					j.setOpaque(false);
					j.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
				}
			}
			repaint();
		} else {
			for(JPanel j : activitySlot) {
				if(j.getBackground() == Color.LIGHT_GRAY) {
					j.setOpaque(true);
					j.setBorder(BorderFactory.createEmptyBorder());
				}
			}
			repaint();
		}
	}
	
	public JScrollPane getScrollPane() {
		return scrollAct;
	}

	public JPanel getPanelTable() {
		return panelTable;
	}

	public void setPanelValuesNull() {

		for (int i = 0; i < 48; i++) {
			activitySlot[i].setBackground(Color.WHITE);
			activitySlot[i].removeAll();
			activitySlot[i].revalidate();
			activitySlot[i].repaint();
		}

	}
}
