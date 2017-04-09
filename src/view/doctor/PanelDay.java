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
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;

import controller.DoctorController;
import model.Appointment;
//import model.Activity;
//import model.Event;
import values.DayHeader;

public class PanelDay extends JPanel {

	private JPanel activity;
	private JPanel time;
	private JPanel panelWeek;
	private JPanel panelTable;

	private JButton[] arrBtnRemove;
	
	private JPanel[] timeSlot;
	private JPanel[] activitySlot;
	private JLabel[] seeTime;
	private JScrollPane scrollAct;

	private GridBagConstraints[] gbc;
	private GridBagConstraints[] gb;

	private DoctorController docController;
	
	public static final String COLOR_TAKEN = "lightgray";
	public static final String COLOR_AVAILABLE = "green";
	
	public PanelDay(DoctorController docController) {
		this.docController = docController;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.setPreferredSize(new Dimension(670, 830));

		// Initialize panels: panelDays
		this.initComp();
		this.initTimeSlot();
		this.initActivitySlot();
		this.initButtons();
		this.addPlaceComp();
	}

	private void initComp() {

		int trSize = 60;
		int activitySize = 30;

		activity = new JPanel();
		activity.setBorder(new LineBorder(new Color(0, 0, 0)));
		activity.setBackground(Color.WHITE);

		time = new JPanel();
		time.setBorder(new LineBorder(new Color(0, 0, 0)));
		time.setBackground(Color.WHITE);

		// Layout for the time "column"
		GridBagLayout activity_time = new GridBagLayout();
		activity_time.columnWidths = new int[] { 90 };
		activity_time.rowHeights = new int[] { trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize,
				trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize,
				trSize, trSize };
		activity_time.columnWeights = new double[] { 0.5 };
		activity_time.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		time.setLayout(activity_time);

		// Layout for the activity "column"
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 550 };
		gridBagLayout.rowHeights = new int[] { activitySize, activitySize, activitySize, activitySize, activitySize,
				activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize,
				activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize,
				activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize,
				activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize,
				activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize,
				activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize,
				activitySize };
		gridBagLayout.columnWeights = new double[] { 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		activity.setLayout(gridBagLayout);

		panelTable = new JPanel();
		panelTable.setLayout(new BoxLayout(panelTable, BoxLayout.X_AXIS));
		panelTable.add(time);
		panelTable.add(activity);
		panelTable.setMinimumSize(new Dimension(600, 600));
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
			time.add(timeSlot[j], gb[j]);
		}

	}

	private void initActivitySlot() {

		activitySlot = new JPanel[48];
		gbc = new GridBagConstraints[48];

		for (int j = 0; j < 48; j++) {
			Border border = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY);

			activitySlot[j] = new JPanel();
			
			activitySlot[j].setBorder(border);
			activitySlot[j].setBackground(Color.WHITE);
			// activitySlot[48 * i + j].add(new JLabel("muaha " + i + " " +
			// (48 * i + j)));

			gbc[j] = new GridBagConstraints();
			gbc[j].insets = new Insets(0, 0, 0, 0);
			gbc[j].fill = GridBagConstraints.BOTH;
			gbc[j].gridx = 0;
			gbc[j].gridy = j;
			
			activity.add(activitySlot[j], gbc[j]);
		}

	}
	
	private void initButtons() {
		arrBtnRemove = new JButton[48];
		
		for (int j = 0; j < 48; j++) {
			
			final int index = j;
			arrBtnRemove[j] = new JButton(" ... ");
			arrBtnRemove[j].setFont(new Font("Sans Serif", Font.BOLD, 12));
			arrBtnRemove[j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));	
			arrBtnRemove[j].setForeground(Color.WHITE);
			arrBtnRemove[j].setContentAreaFilled(false);
			arrBtnRemove[j].addMouseListener(new MouseAdapter() {
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
			arrBtnRemove[j].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					PanelModifyDelete pmd = new PanelModifyDelete(2017, 10, 29);
					int choice = JOptionPane.showConfirmDialog(null, pmd, "Modify Activity", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					
					if(pmd.toDelete()) {
						int timeH = index / 2;
						int timeM = 30 * (index % 2);
						
						// Get appointment from list which matches the above starting time and the current date pointer
						// -> then delete the appointment
						
					} else {
						if(choice == JOptionPane.OK_OPTION) {
							if(pmd.isValidTime()) {
								// Original starting time
								int timeH = index / 2;
								int timeM = 30 * (index % 2);
								
								// Get appointment from list which matches the above starting time and the current date pointer
								
								/* appointment.setDate( */pmd.getSpinDate();
								/* appointment.setStartTime( */pmd.getFromTime();
								/* appointment.setEndTime( */pmd.getToTime();
							} else {
								JOptionPane.showMessageDialog(null, "Invalid time input", "Cannot modify appointment", JOptionPane.WARNING_MESSAGE);
							}
						}
					} 
				}
			});
		}
	}

	private void addPlaceComp() {
		this.add(scrollAct);
	}

	public void update(int month, int day, int year, Iterator<Appointment> activity) {
		this.setPanelValuesNull();
		this.setPanelValues(month, day, year, activity);

	}

	public void setPanelValues(int month, int day, int year, Iterator<Appointment> activity) {
		ArrayList<Appointment> activityList = new ArrayList<>();

		if (activity != null) {
			while (activity != null && activity.hasNext()) {
				Appointment a = activity.next();
				activityList.add(a);
			}

			for (int i = 0; i < activityList.size(); i++) {
				if (activityList.get(i).isMonth(month) && activityList.get(i).isDay(day)
						&& activityList.get(i).isYear(year)) {
					setAppointment(activityList.get(i));
				}
			}
		}
	}

	public void setAppointment(Appointment act) {

		JLabel evnt = new JLabel(act.toString());
		evnt.setFont(new Font("Sans Serif", Font.BOLD, 14));
		evnt.setForeground(Color.white);

		int start = act.getStartHour() * 2 + act.getStartMinute() / 30;
		int end = act.getEndHour() * 2 + act.getEndMinute() / 30;

		activitySlot[start].add(evnt);
		
		if(docController.getDoctor().getName().equalsIgnoreCase(act.getDoctorName())) {
			activitySlot[start].add(arrBtnRemove[start]);
			arrBtnRemove[start].setVisible(false);
		}
		
		final int originalIndex = start;
		
		while (start < end) {
			if(act.isAvailable())
				activitySlot[start].setBackground(ColorParser.getColor(COLOR_AVAILABLE));
			else activitySlot[start].setBackground(ColorParser.getColor(COLOR_TAKEN));
			
			activitySlot[start].setBorder(BorderFactory.createEmptyBorder());
			if(activitySlot[start].getBackground() != ColorParser.getColor(COLOR_TAKEN) && docController.getDoctor().getName().equalsIgnoreCase(act.getDoctorName())) {
				activitySlot[start].addMouseListener(new MouseAdapter() {
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
			//activitySlot[i].add(arrBtnRemove[i]);
			//arrBtnRemove[i].setBounds(5, 5, 10, 10);
			//arrBtnRemove[i].setVisible(false);
			activitySlot[i].revalidate();
			activitySlot[i].repaint();
		}

	}
}
