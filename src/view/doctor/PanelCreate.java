package view.doctor;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.DoctorController;
import model.Appointment;
//import controller.Controller;
//import model.Activity;
//import model.ActivityFactory;
import model.CalendarCalculator;
import model.CalendarModel;
import values.Month;

public class PanelCreate extends JPanel {

	private ButtonGroup bg;
	private JRadioButton jrbOnce;
	private JRadioButton jrbRecurring;
	private JLabel lblTo;
	private JLabel lblTColon;
	private JLabel lblFColon;
	private JButton btnSave;
	private JButton btnDiscard;

	// Spinners
	private JSpinner spinFromMinutes;
	private JSpinner spinFromHour;
	private JSpinner spinToMinutes;
	private JSpinner spinToHour;

	private JSpinner spinMonth;
	private JSpinner spinDay;
	private JSpinner spinYear;

	// Repetition
	private JLabel lblRepeats;
	private JLabel lblRepeatEvery;
	private JLabel lblRepeatEveryMOrW;
	private JLabel lblEndAfter;
	private JLabel lblEndAfterOccur;

	private JComboBox<String> cbRepeats;
	private JComboBox<Integer> cbRepeatEvery;
	private JComboBox<Integer> cbEndAfter;

	private DoctorController docController;

	public PanelCreate(DoctorController docController, int year, int month, int day) {
		this.docController = docController;
		
		this.setLayout(null);
		this.setBackground(Color.WHITE);

		this.initComp(year, month, day);

		this.addPlaceComp();
		// this.addListeners();
		this.addListeners();
	}

	private void initComp(int year, int month, int day) {
		// initialize swing components
		jrbOnce = new JRadioButton("Once only");
		jrbRecurring = new JRadioButton("Repeat...");
		lblTo = new JLabel("to");
		lblTColon = new JLabel(":");
		lblFColon = new JLabel(":");
		btnSave = new JButton("Save");
		btnDiscard = new JButton("Discard");
		bg = new ButtonGroup();

		// initialize spinners
		SpinnerModel model = new SpinnerNumberModel(00, 00, 23, 1);

		spinFromHour = new JSpinner(model);
		model = new SpinnerNumberModel(00, 00, 23, 1);
		spinToHour = new JSpinner(model);
		model = new SpinnerNumberModel(00, 00, 30, 30);
		spinFromMinutes = new JSpinner(model);
		model = new SpinnerNumberModel(00, 00, 30, 30);
		spinToMinutes = new JSpinner(model);

		String[] monthArr = Month.getArrShortString();
		model = new SpinnerListModel(monthArr);
		spinMonth = new JSpinner(model);
		spinMonth.setValue(monthArr[month]);
		model = new SpinnerNumberModel(day, 1, CalendarCalculator.getNoDays(year, month), 1);
		spinDay = new JSpinner(model);
		model = new SpinnerNumberModel(year, year - 100, year + 100, 1);
		spinYear = new JSpinner(model);

		spinFromMinutes.setEditor(new JSpinner.NumberEditor(spinFromMinutes, "00"));
		spinToMinutes.setEditor(new JSpinner.NumberEditor(spinToMinutes, "00"));
		spinYear.setEditor(new JSpinner.NumberEditor(spinYear, "####"));

		// Repetition
		lblRepeats = new JLabel("Repeats:");
		lblRepeats.setFont(new Font("Sans Serif", Font.BOLD, 16));
		lblRepeatEvery = new JLabel("Repeats every: ");
		lblRepeatEvery.setFont(new Font("Sans Serif", Font.BOLD, 16));
		lblRepeatEveryMOrW = new JLabel("days");
		lblRepeatEveryMOrW.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		lblEndAfter = new JLabel("Ends after: ");
		lblEndAfter.setFont(new Font("Sans Serif", Font.BOLD, 16));
		lblEndAfterOccur = new JLabel("occurrences");
		lblEndAfterOccur.setFont(new Font("Sans Serif", Font.PLAIN, 16));

		cbRepeats = new JComboBox<String>();
		cbRepeats.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		cbRepeats.addItem("Daily");
		cbRepeats.addItem("Weekly");
		cbRepeats.addItem("Monthly");

		cbRepeatEvery = new JComboBox<Integer>();
		cbRepeatEvery.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		for (int i = 0; i < 5; i++) {
			cbRepeatEvery.addItem(i + 1);
		}
		cbEndAfter = new JComboBox<Integer>();
		cbEndAfter.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		for (int i = 1; i < 10; i++) {
			cbEndAfter.addItem(i + 1);
		}
		

		// edit swing component properties
		jrbOnce.setSelected(true);

		jrbOnce.setOpaque(false);
		jrbRecurring.setOpaque(false);

		btnSave.setContentAreaFilled(false);
		btnSave.setOpaque(true);
		btnSave.setFocusPainted(false);
		btnSave.setBackground(new Color(77, 148, 179));
		btnSave.setForeground(Color.WHITE);
		btnSave.setBorder(BorderFactory.createEmptyBorder());
		btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));

		btnDiscard.setOpaque(false);
		btnDiscard.setContentAreaFilled(false);
		btnDiscard.setCursor(new Cursor(Cursor.HAND_CURSOR));

		jrbOnce.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		jrbRecurring.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		lblTo.setFont(new Font("Sans Serif", Font.BOLD, 16));
		lblTColon.setFont(new Font("Sans Serif", Font.BOLD, 16));
		lblFColon.setFont(new Font("Sans Serif", Font.BOLD, 16));
		btnSave.setFont(new Font("Sans Serif", Font.BOLD, 16));
		btnDiscard.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		spinToHour.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		spinToMinutes.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		spinFromHour.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		spinFromMinutes.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		spinMonth.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		spinDay.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		spinYear.setFont(new Font("Sans Serif", Font.PLAIN, 16));
	}

	private void addPlaceComp() {
		// add components to panel
		this.add(jrbOnce);
		this.add(jrbRecurring);
		this.add(lblTo);
		this.add(lblFColon);
		this.add(lblTColon);
		this.add(btnSave);
		this.add(btnDiscard);
		this.add(btnSave);
		this.add(btnDiscard);
		this.add(spinFromHour);
		this.add(spinFromMinutes);
		this.add(spinToHour);
		this.add(spinToMinutes);
		this.add(spinMonth);
		this.add(spinDay);
		this.add(spinYear);

		// add buttons to button group
		bg.add(jrbOnce);
		bg.add(jrbRecurring);

		// set component bounds (and size!)
		spinMonth.setBounds(15, 15, 60, 40);
		spinDay.setBounds(75, 15, 40, 40);
		spinYear.setBounds(115, 15, 70, 40);

		spinFromHour.setBounds(200, 15, 50, 40);
		lblFColon.setBounds(255, 15, 10, 40);
		spinFromMinutes.setBounds(265, 15, 50, 40);

		lblTo.setBounds(327, 25, 20, 20);

		spinToHour.setBounds(355, 15, 50, 40);
		lblTColon.setBounds(410, 15, 10, 40);
		spinToMinutes.setBounds(420, 15, 50, 40);

		jrbOnce.setBounds(20, 60, 100, 40);
		jrbRecurring.setBounds(150, 60, 120, 40);

		btnSave.setBounds(260, 115, 90, 40);
		btnDiscard.setBounds(380, 115, 90, 40);
	}

	public String getSelectedRB() {
		for (Enumeration<AbstractButton> buttons = this.bg.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected())
				return button.getText().toLowerCase();
		}

		return "";
	}

	/**
	 * Returns the selected month of the spinMonth JSpinner. (January - 0 /
	 * December - 11)
	 * 
	 * @return the selected value of the spinMonth JSpinner.
	 */
	public int getIntSpinMonth() {
		return Month.getIndexShortString((String) spinMonth.getValue());
	}

	/**
	 * Returns the selected year of the spinYear JSpinner.
	 * 
	 * @return the selected value of the spinYear JSpinner.
	 */
	public int getSpinYear() {
		return (Integer) spinYear.getValue();
	}

	/**
	 * Returns the selected day of the spinDay JSpinner.
	 * 
	 * @return the selected value of the spinDay JSpinner.
	 */
	public int getSpinDay() {
		return (Integer) spinDay.getValue();
	}

	/**
	 * Returns the selected hour value of the spinToHour JSpinner.
	 * 
	 * @return the selected value of the spinToHour JSpinner.
	 */
	public int getSpinToHour() {
		return (Integer) spinToHour.getValue();
	}

	/**
	 * Returns the selected minute value of the spinToMinutes JSpinner.
	 * 
	 * @return the selected value of the spinToMinutes JSpinner.
	 */
	public int getSpinToMinutes() {
		return (Integer) spinToMinutes.getValue();
	}

	/**
	 * Returns the selected hour value of the spinFromHour JSpinner.
	 * 
	 * @return the selected value of the spinFromHour JSpinner.
	 */
	public int getSpinFromHour() {
		return (Integer) spinFromHour.getValue();
	}

	/**
	 * Returns the selected minute value of the spinFromMinutes JSpinner.
	 * 
	 * @return the selected value of the spinFromMinutes JSpinner.
	 */
	public int getSpinFromMinutes() {
		return (Integer) spinFromMinutes.getValue();
	}

	public Calendar getSelectedDate() {
		return null;
	}

	/**
	 * Returns the time equivalent of the selected values from the spinFromX
	 * JSpinners in its Calendar value. The date set in the Calendar value
	 * returned is the selected dates from the data JSpinners.
	 * 
	 * @return Calendar value of the selected spinFromX JSpinners.
	 */
	public Calendar getFromTime() {
		Calendar c = Calendar.getInstance();

		c.set(this.getSpinYear(), this.getIntSpinMonth(), this.getSpinDay(), this.getSpinFromHour(),
				this.getSpinFromMinutes());

		return c;
	}

	/**
	 * Returns the time equivalent of the selected values from the spintToX
	 * JSpinners in its Calendar value. The date set in the Calendar value
	 * returned is the selected dates from the data JSpinners.
	 * 
	 * @return Calendar value of the selected spinToX JSpinners.
	 */
	public Calendar getToTime() {
		Calendar c = Calendar.getInstance();

		c.set(this.getSpinYear(), this.getIntSpinMonth(), this.getSpinDay(), this.getSpinToHour(),
				this.getSpinToMinutes());

		return c;
	}

	/**
	 * Returns the date equivalent of the selected values from the date
	 * JSpinners in its Calendar value. No time is set in the returned Calendar
	 * value.
	 * 
	 * @return Calendar value of the selected date JSpinners.
	 */
	public Calendar getSpinDate() {
		Calendar c = Calendar.getInstance();

		c.set(this.getSpinYear(), this.getIntSpinMonth(), this.getSpinDay());

		return c;
	}

	/**
	 * Returns the selected item of the cbRepeats ComboBox.
	 * 
	 * @return the value of the cbRepeats ComboBox (Daily, Weekly or Monthly)
	 */
	public String getRepeats() {
		return (String) cbRepeats.getSelectedItem();
	}

	/**
	 * Returns the frequency of the appointment's repetition.
	 * 
	 * @return the value of the cbRepeatEvery ComboBox
	 */
	public int getRepeatsEvery() {
		return (Integer) cbRepeatEvery.getSelectedItem();
	}


	/**
	 * Returns the value at which an appointment is to be repeated.
	 * 
	 * @return the value of the cbRepeatEvery ComboBox
	 */
	public int getRepeatEndAfter() {
		return (Integer) cbEndAfter.getSelectedItem();
	}
	
	public boolean isValidTime() {
		if((getSpinFromHour() > getSpinToHour() || (getSpinFromHour() == getSpinToHour() && getSpinFromMinutes() >= getSpinToMinutes()) ))
			return false;
		else return true;
		
	}
	
	public boolean isWeekend() {
		return getSpinDate().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || getSpinDate().get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
	}

	public void resetDay() {
		this.spinDay.setValue(((SpinnerNumberModel) (spinDay.getModel())).getMinimum());
	}

	public void clearAll() {

		this.spinFromMinutes.setValue(((SpinnerNumberModel) (spinFromMinutes.getModel())).getMinimum());
		this.spinFromHour.setValue(((SpinnerNumberModel) (spinFromHour.getModel())).getMinimum());
		this.spinToMinutes.setValue(((SpinnerNumberModel) (spinToMinutes.getModel())).getMinimum());
		this.spinToHour.setValue(((SpinnerNumberModel) (spinToHour.getModel())).getMinimum());
		this.spinMonth.setValue(((SpinnerListModel) (spinMonth.getModel())).getList().get(0));
		this.spinDay.setValue(((SpinnerNumberModel) (spinDay.getModel())).getMinimum());
		this.spinYear.setValue((Integer) (((SpinnerNumberModel) (spinYear.getModel())).getMinimum()) + 100);
	}

	public void addListeners() {
		jrbOnce.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removeRepeatComp();
				if (jrbOnce.isSelected()) {

					add(btnSave);
					add(btnDiscard);

					btnSave.setBounds(260, 115, 90, 40);
					btnDiscard.setBounds(380, 115, 90, 40);

					repaint();
				}
			}

		});

		jrbRecurring.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removeRepeatComp();

				if (jrbRecurring.isSelected()) {

					add(lblRepeats);
					add(cbRepeats);
					add(lblRepeatEvery);
					add(cbRepeatEvery);
					add(lblRepeatEveryMOrW);
					add(lblEndAfter);
					add(cbEndAfter);
					add(lblEndAfterOccur);
					add(btnSave);
					add(btnDiscard);

					lblRepeats.setBounds(70, 115, 90, 30);
					cbRepeats.setBounds(160, 115, 120, 30);
					lblRepeatEvery.setBounds(25, 145, 120, 30);
					cbRepeatEvery.setBounds(160, 145, 120, 30);
					lblRepeatEveryMOrW.setBounds(285, 145, 120, 30);
					lblEndAfter.setBounds(55, 175, 120, 30);
					cbEndAfter.setBounds(160, 175, 120, 30);
					lblEndAfterOccur.setBounds(285, 175, 120, 30);
					btnSave.setBounds(260, 225, 90, 40);
					btnDiscard.setBounds(380, 225, 90, 40);

					repaint();
				}

			}
		});

		cbRepeats.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub


				if (((String) cbRepeats.getSelectedItem()).equalsIgnoreCase("Daily")) {
					System.out.println("daily");
					lblRepeatEveryMOrW.setText("days");
					
					repaint();

				} else if (((String) cbRepeats.getSelectedItem()).equalsIgnoreCase("Weekly")) {
					System.out.println("weekly");

					lblRepeatEveryMOrW.setText("weeks");
					
				} else if (((String) cbRepeats.getSelectedItem()).equalsIgnoreCase("Monthly")) {
					System.out.println("monthly");

					lblRepeatEveryMOrW.setText("months");
					

				}

			}

		});

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(isValidTime()) {
					if (jrbOnce.isSelected()) {
						if(!isWeekend() && isValidTime()) {
							Calendar start = getFromTime();
							Calendar end = getToTime();
						
							start.set(Calendar.MONTH, getIntSpinMonth());
							start.set(Calendar.DAY_OF_MONTH, getSpinDay());
							start.set(Calendar.YEAR, getSpinYear());
							
							end.set(Calendar.MONTH, getIntSpinMonth());
							end.set(Calendar.DAY_OF_MONTH, getSpinDay());
							end.set(Calendar.YEAR, getSpinYear());
							
							Appointment a = new Appointment(start, end);
							
							System.out.println("Create appointment " + start.get(Calendar.MONTH) + " " + start.get(Calendar.DAY_OF_MONTH) + 
									" " + start.get(Calendar.YEAR) + "\n from " + start.get(Calendar.HOUR) + ":" + start.get(Calendar.MINUTE) + " to " +
									end.get(Calendar.HOUR) + ":" + end.get(Calendar.MINUTE) + "\n");
							
							if(!docController.addAppointment(a))
								JOptionPane.showMessageDialog(null, "Cannot add appointment because of overlap.", "Cannot add appointment", JOptionPane.WARNING_MESSAGE);
							else JOptionPane.showMessageDialog(null, "Successfully added appointment!");
						}
						if(isWeekend())
							JOptionPane.showMessageDialog(null, "Weekend appointments are not applicable.");
						if(!isValidTime())
							JOptionPane.showMessageDialog(null, "Time values invalid.");
					} else if (jrbRecurring.isSelected()) {
						System.out.println("Repeat appointment " + getRepeatEndAfter() + " times. " + getRepeats() + ". "
								+ "Every " + getRepeatsEvery() + " units.");

						// GET TIMES
						Calendar start = getFromTime();
						Calendar end = getToTime();

						// Check if there are conflicts
						// if there is none, create appointment
						
						ArrayList<GregorianCalendar> listGC = new ArrayList<GregorianCalendar>();
						// GET DATES
						if (getRepeats().equalsIgnoreCase("daily")) {
							listGC = new ArrayList<GregorianCalendar>();

							// Start setting repetitions
							int currYear = getSpinYear();
							int currDay = getSpinDay();
							int currMonth = getIntSpinMonth();
							int maxMonth = CalendarCalculator.getNoDays(currYear, currMonth);
							listGC.add(new GregorianCalendar(currYear, currMonth, currDay));

							for (int i = 1; i < getRepeatEndAfter(); i++) {

								// advance day according to repeatsEvery value
								for (int j = 0; j < getRepeatsEvery(); j++) {
									currDay++;
									if (currDay > maxMonth) {
										currMonth = CalendarCalculator.getNextMonth(currMonth);
										if (Month.values()[currMonth] == Month.JANUARY) {
											currYear++;
										}
										maxMonth = CalendarCalculator.getNoDays(currYear, currMonth);
										currDay = 1;
									}
								}

								// Check if there are conflicts
								// if there is none, create appointment
								listGC.add(new GregorianCalendar(currYear, currMonth, currDay));
							}

							for (GregorianCalendar gc : listGC) {
								System.out.println("dates: " + gc.toString());
							}
						} else if (getRepeats().equalsIgnoreCase("weekly")) {
							listGC = new ArrayList<GregorianCalendar>();

							// Start setting repetitions
							int currYear = getSpinYear();
							int currDay = getSpinDay();
							int currMonth = getIntSpinMonth();

							GregorianCalendar g = new GregorianCalendar(currYear, currMonth, currDay);
							listGC.add(g);
							
							for (int i = 1; i < getRepeatEndAfter(); i++) {

								g = new GregorianCalendar(currYear, currMonth, currDay);
								g.add(Calendar.DAY_OF_YEAR, 7 * getRepeatsEvery());
								listGC.add(g);
								
								currMonth = g.get(Calendar.MONTH);
								currDay = g.get(Calendar.DAY_OF_MONTH);
								currYear = g.get(Calendar.YEAR);
							}
							
							SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy");
							for (GregorianCalendar gc : listGC) {
								System.out.println("week pointer " + gc.get(Calendar.WEEK_OF_YEAR) + " date: " + sdf.format(gc.getTime()));
							}
						} else if (getRepeats().equalsIgnoreCase("monthly")) {
							listGC = new ArrayList<GregorianCalendar>();

							// Start setting repetitions
							int currYear = getSpinYear();
							int currDay = getSpinDay();
							int currMonth = getIntSpinMonth();
							
							GregorianCalendar g = new GregorianCalendar(currYear, currMonth, currDay);
							listGC.add(g);
							
							for (int i = 1; i < getRepeatEndAfter(); i++) {

								g = new GregorianCalendar(currYear, currMonth, currDay);
								g.add(Calendar.MONTH, 1 * getRepeatsEvery());
								listGC.add(g);
								
								currMonth = g.get(Calendar.MONTH);
								currDay = g.get(Calendar.DAY_OF_MONTH);
								currYear = g.get(Calendar.YEAR);
							}
							
							SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy");
							for (GregorianCalendar gc : listGC) {
								System.out.println("week pointer " + gc.get(Calendar.WEEK_OF_YEAR) + " date: " + sdf.format(gc.getTime()));
							}
						}
						int successAdd = 0;
						
						for(int i = 0; i < listGC.size(); i++) {
							GregorianCalendar appStart = (GregorianCalendar) start.clone();
							appStart.set(Calendar.MONTH, listGC.get(i).get(Calendar.MONTH));
							appStart.set(Calendar.DAY_OF_MONTH, listGC.get(i).get(Calendar.DAY_OF_MONTH));
							appStart.set(Calendar.YEAR, listGC.get(i).get(Calendar.YEAR));
							GregorianCalendar appEnd = (GregorianCalendar) end.clone();
							appEnd.set(Calendar.MONTH, listGC.get(i).get(Calendar.MONTH));
							appEnd.set(Calendar.DAY_OF_MONTH, listGC.get(i).get(Calendar.DAY_OF_MONTH));
							appEnd.set(Calendar.YEAR, listGC.get(i).get(Calendar.YEAR));
							
							System.out.println("Create appointment " + appStart.get(Calendar.MONTH) + " " + appStart.get(Calendar.DAY_OF_MONTH) + 
									" " + appStart.get(Calendar.YEAR) + "\n from " + appStart.get(Calendar.HOUR) + ":" + appStart.get(Calendar.MINUTE) + " to " +
									appEnd.get(Calendar.HOUR) + ":" + appEnd.get(Calendar.MINUTE) + "\n");
							
							if(isWeekend())
								JOptionPane.showMessageDialog(null, "Weekend appointments are not applicable.");
							else {
								Appointment a = new Appointment(appStart, appEnd);
								if(!docController.addAppointment(a))
									JOptionPane.showMessageDialog(null, "Cannot add appointment because of overlap.", "Cannot add appointment", JOptionPane.WARNING_MESSAGE);
								else successAdd++;
							}
							
						}
						JOptionPane.showMessageDialog(null, "Successfully added " + successAdd + " appointment(s)!");
					}
				} else JOptionPane.showMessageDialog(null, "Time values invalid.");
			}

		});
		spinMonth.addChangeListener(new RegenerateDay());
		spinYear.addChangeListener(new RegenerateDay());
	}

	private void removeRepeatComp() {
		remove(btnSave);
		remove(btnDiscard);
		remove(lblRepeats);
		remove(cbRepeats);
		remove(lblRepeatEvery);
		remove(cbRepeatEvery);
		remove(lblRepeatEveryMOrW);
		remove(lblEndAfter);
		remove(cbEndAfter);
		remove(lblEndAfterOccur);
	}
	
	class RegenerateDay implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			SpinnerModel model = new SpinnerNumberModel(1, 1,
					CalendarCalculator.getNoDays(getSpinYear(), getIntSpinMonth()), 1);
			spinDay.setModel(model);
			resetDay();
			revalidate();
			repaint();
		}
	}
}