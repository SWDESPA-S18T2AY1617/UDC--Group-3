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

//import controller.Controller;
//import model.Activity;
//import model.ActivityFactory;
import model.CalendarCalculator;
import model.CalendarModel;
import values.Month;

public class PanelCreate extends JPanel{
	
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
	private JLabel lblRepeatOn;
	private JLabel lblEndAfter;
	private JLabel lblEndAfterOccur;
	
	private Checkbox[] chckDayArr;
	private JComboBox<String> cbRepeats;		// daily, weekly, monthly
	private ButtonGroup bgMonth;
	private JRadioButton jrbDayMonth;
	private JRadioButton jrbDayWeek;
	private JComboBox<Integer> cbRepeatEvery;	// daily (1 - 5 days), weekly, monthly
	private JComboBox<Integer> cbEndAfter;		// (1 - 10) occurrences
	
	
	//private Controller controller;

//	public PanelCreate(Controller controller, int year, int month, int day) {
	public PanelCreate(ViewController vc, int year, int month, int day) {
		//this.controller = controller;
		
		this.setLayout(null);
		this.setBackground(Color.WHITE);

		this.initComp(year, month, day);

		this.addPlaceComp();
		//this.addListeners();
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
		chckDayArr = new Checkbox[5];
		SpinnerModel model = new SpinnerNumberModel(00, 00, 23, 1);
		
		spinFromHour =  new JSpinner(model);
		model = new SpinnerNumberModel(00, 00, 23, 1);
		spinToHour = new JSpinner(model);
		model = new SpinnerNumberModel(00, 00, 30, 30);
		spinFromMinutes = new JSpinner(model);
		model = new SpinnerNumberModel(00, 00, 30, 30);
		spinToMinutes = new JSpinner(model);
		
		String [] monthArr = Month.getArrShortString();
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
		lblRepeatOn = new JLabel("Repeat on: ");
		lblRepeatOn.setFont(new Font("Sans Serif", Font.BOLD, 16));
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
		for(int i = 0; i < 5; i++) {
			cbRepeatEvery.addItem(i + 1);
		}
		cbEndAfter = new JComboBox<Integer>();
		cbEndAfter.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		for(int i = 0; i < 10; i++) {
			cbEndAfter.addItem(i + 1);
		}
		chckDayArr[0] = new Checkbox("M");
		chckDayArr[1] = new Checkbox("T");
		chckDayArr[2] = new Checkbox("W");
		chckDayArr[3] = new Checkbox("T");
		chckDayArr[4] = new Checkbox("F");
		jrbDayMonth = new JRadioButton("day of the month");
		jrbDayMonth.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		jrbDayMonth.setOpaque(false);
		jrbDayMonth.setSelected(true);
		jrbDayWeek = new JRadioButton("day of the week");
		jrbDayWeek.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		jrbDayWeek.setOpaque(false);
		bgMonth = new ButtonGroup();
		bgMonth.add(jrbDayMonth);
		bgMonth.add(jrbDayWeek);
		
		// edit swing component properties
		jrbOnce.setSelected(true);
		
		jrbOnce.setOpaque(false);
		jrbRecurring.setOpaque(false);

		btnSave.setBackground(new Color(211, 72, 54));
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
	 * Returns the selected month of the spinMonth JSpinner. (January - 0 / December - 11)
	 * 
	 * @return the selected value of the spinMonth JSpinner.
	 */
	public int getIntSpinMonth() {
		return Month.getIndexShortString((String)spinMonth.getValue());
	}
	
	/**
	 * Returns the selected year of the spinYear JSpinner.
	 * 
	 * @return the selected value of the spinYear JSpinner.
	 */
	public int getSpinYear() {
		return (Integer)spinYear.getValue();
	}
	
	/**
	 * Returns the selected day of the spinDay JSpinner.
	 * 
	 * @return the selected value of the spinDay JSpinner.
	 */
	public int getSpinDay() {
		return (Integer)spinDay.getValue();
	}
	
	/**
	 * Returns the selected hour value of the spinToHour JSpinner.
	 * 
	 * @return the selected value of the spinToHour JSpinner.
	 */
	public int getSpinToHour() {
		return (Integer)spinToHour.getValue();
	}
	
	/**
	 * Returns the selected minute value of the spinToMinutes JSpinner.
	 * 
	 * @return the selected value of the spinToMinutes JSpinner.
	 */
	public int getSpinToMinutes() {
		return (Integer)spinToMinutes.getValue();
	}
	
	/**
	 * Returns the selected hour value of the spinFromHour JSpinner.
	 * 
	 * @return the selected value of the spinFromHour JSpinner.
	 */
	public int getSpinFromHour() {
		return (Integer)spinFromHour.getValue();
	}
	
	/**
	 * Returns the selected minute value of the spinFromMinutes JSpinner.
	 * 
	 * @return the selected value of the spinFromMinutes JSpinner.
	 */
	public int getSpinFromMinutes() {
		return (Integer)spinFromMinutes.getValue();
	}
	
	public Calendar getSelectedDate() {
		return null;
	}
	
	/**
	 * Returns the time equivalent of the selected values from the spinFromX JSpinners in its Calendar value. The date set in the Calendar value returned is the selected dates from the data JSpinners.
	 * 
	 * @return Calendar value of the selected spinFromX JSpinners.
	 */
	public Calendar getFromTime() {
		Calendar c = Calendar.getInstance();
		
		c.set(this.getSpinYear(), this.getIntSpinMonth(), this.getSpinDay(), this.getSpinFromHour(), this.getSpinFromMinutes());
		
		return c;
	}
	
	/**
	 * Returns the time equivalent of the selected values from the spintToX JSpinners in its Calendar value. The date set in the Calendar value returned is the selected dates from the data JSpinners.
	 * 
	 * @return Calendar value of the selected spinToX JSpinners.
	 */
	public Calendar getToTime() {
		Calendar c = Calendar.getInstance();
		
		c.set(this.getSpinYear(), this.getIntSpinMonth(), this.getSpinDay(), this.getSpinToHour(), this.getSpinToMinutes());
		
		return c;
	}
	
	/**
	 * Returns the date equivalent of the selected values from the date JSpinners in its Calendar value. No time is set in the returned Calendar value.
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
		return (String)cbRepeats.getSelectedItem();
	}
	
	/**
	 * Returns the frequency of the appointment's repetition.
	 * 
	 * @return the value of the cbRepeatEvery ComboBox
	 */
	public int getRepeatsEvery() {
		return (Integer)cbRepeatEvery.getSelectedItem();
	}
	
	/**
	 * Returns an iterator of the list of selected days of a week of which to recur an appointment. Use only if cbRepeats has "Weekly" selected.
	 * 
	 * @return an iterator containing all selected days of a week of the Checkbox array chckDayArr.
	 */
	public Iterator<String> getWeekRepeatOn() {
		List<String> arrDays = new ArrayList<String>();
		
		for( Checkbox c : chckDayArr ) {
			if(c.getState())
				arrDays.add(c.getLabel());
		}
		
		return arrDays.iterator();
	}
	
	/**
	 * Returns how the appointment is repeated when cbRepeats has "Monthly" selected. Returns "month" if you repeat the appointment the same day of the month every month. Returns "weekly" if you repeat the appointment on the same nth day of the week (example, repeat on the 3rd saturday, on the last monday, etc.) 
	 * 
	 * @return the value of the cbRepeatEvery ComboBox
	 */
	public String getMonthRepeatBy() {
		if(jrbDayMonth.isSelected()) {
			return "month";
		} else {
			return "week";
		}
	}
	
	/**
	 * Returns the value at which an appointment is to be repeated.
	 * 
	 * @return the value of the cbRepeatEvery ComboBox
	 */
	public int getRepeatEndAfter() {
		return (Integer)cbEndAfter.getSelectedItem();
	}
	
	public void resetDay() {
		this.spinDay.setValue(((SpinnerNumberModel)(spinDay.getModel())).getMinimum());
	}
	
	public void clearAll() {
		
		this.spinFromMinutes.setValue(((SpinnerNumberModel)(spinFromMinutes.getModel())).getMinimum());
		this.spinFromHour.setValue(((SpinnerNumberModel)(spinFromHour.getModel())).getMinimum());
		this.spinToMinutes.setValue(((SpinnerNumberModel)(spinToMinutes.getModel())).getMinimum());
		this.spinToHour.setValue(((SpinnerNumberModel)(spinToHour.getModel())).getMinimum());
		this.spinMonth.setValue(((SpinnerListModel)(spinMonth.getModel())).getList().get(0));
		this.spinDay.setValue(((SpinnerNumberModel)(spinDay.getModel())).getMinimum());
		this.spinYear.setValue((Integer)(((SpinnerNumberModel)(spinYear.getModel())).getMinimum()) + 100);
	}
	
	public void addListeners() {
		jrbOnce.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				removeRepeatComp();
				if(jrbOnce.isSelected()) {
					
					
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
				
				if(jrbRecurring.isSelected()) {
					
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
				
				removeRepeatComp();
				
				if(((String)cbRepeats.getSelectedItem()).equalsIgnoreCase("Daily")) {
					System.out.println("daily");
					
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
					
					lblRepeatEveryMOrW.setText("days");
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
					
				} else if(((String)cbRepeats.getSelectedItem()).equalsIgnoreCase("Weekly")) {
					System.out.println("weekly");

					lblRepeatEveryMOrW.setText("weeks");
					lblRepeatOn.setText("Repeat on:");
					add(lblRepeats);
					add(cbRepeats);
					add(lblRepeatEvery);
					add(cbRepeatEvery);
					add(lblRepeatEveryMOrW);
					add(lblRepeatOn);
					for(Checkbox c : chckDayArr)
						add(c);
					add(lblEndAfter);
					add(cbEndAfter);
					add(btnSave);
					add(btnDiscard);
					add(lblEndAfterOccur);
					
					
					lblRepeats.setBounds(70, 115, 90, 30);
					cbRepeats.setBounds(160, 115, 120, 30);
					lblRepeatEvery.setBounds(25, 145, 120, 30);
					cbRepeatEvery.setBounds(160, 145, 120, 30);
					lblRepeatEveryMOrW.setBounds(285, 145, 120, 30);
					lblRepeatOn.setBounds(55, 175, 100, 30);
					for(int i = 0; i < chckDayArr.length; i++) {
						chckDayArr[i].setBounds(160 + (i * 40), 175, 40, 30);
						chckDayArr[i].setBackground(null);
					}
					lblEndAfter.setBounds(55, 205, 120, 30);
					cbEndAfter.setBounds(160, 205, 120, 30);
					lblEndAfterOccur.setBounds(285, 205, 120, 30);


					btnSave.setBounds(260, 255, 90, 40);
					btnDiscard.setBounds(380, 255, 90, 40);
					
					repaint();
				} else if(((String)cbRepeats.getSelectedItem()).equalsIgnoreCase("Monthly")) {
					System.out.println("monthly");
					
					lblRepeatEveryMOrW.setText("months");
					lblRepeatOn.setText("Repeat by:");
					add(lblRepeats);
					add(cbRepeats);
					add(lblRepeatEvery);
					add(cbRepeatEvery);
					add(lblRepeatEveryMOrW);
					add(lblRepeatOn);
					add(lblEndAfter);
					add(cbEndAfter);
					add(btnSave);
					add(btnDiscard);
					add(lblEndAfterOccur);
					add(jrbDayMonth);
					add(jrbDayWeek);
					
					
					lblRepeats.setBounds(70, 115, 90, 30);
					cbRepeats.setBounds(160, 115, 120, 30);
					lblRepeatEvery.setBounds(25, 145, 120, 30);
					cbRepeatEvery.setBounds(160, 145, 120, 30);
					lblRepeatEveryMOrW.setBounds(285, 145, 120, 30);
					lblRepeatOn.setBounds(55, 175, 100, 30);
					jrbDayMonth.setBounds(160, 175, 150, 30);
					jrbDayWeek.setBounds(320, 175, 200, 30);
					lblEndAfter.setBounds(55, 205, 120, 30);
					cbEndAfter.setBounds(160, 205, 120, 30);
					lblEndAfterOccur.setBounds(285, 205, 120, 30);
					btnSave.setBounds(260, 255, 90, 40);
					btnDiscard.setBounds(380, 255, 90, 40);
					
					repaint();
				}
				
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
		remove(lblRepeatOn);
		for(Checkbox c : chckDayArr)
			remove(c);
		remove(lblEndAfter);
		remove(cbEndAfter);
		remove(lblEndAfterOccur);
		remove(jrbDayMonth);
		remove(jrbDayWeek);
	}
	
	/*
	public void addListeners() {
		btnSave.addActionListener(new BtnSave());
		btnDiscard.addActionListener(new BtnDiscard());
		jrbRecurring.addActionListener(new HideFromTime());
		jrbOnce.addActionListener(new ShowFromTime());
		spinMonth.addChangeListener(new RegenerateDay());
		spinYear.addChangeListener(new RegenerateDay());
	}
		
	
	class BtnSave implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println(getSelectedRB());
			if(getSelectedRB().equalsIgnoreCase("Event")) {
				Calendar start = new GregorianCalendar(getSpinYear(), getIntSpinMonth(), getSpinDay());
				start.set(Calendar.SECOND, 0);
				Calendar end = (Calendar) start.clone();
				start.set(Calendar.HOUR, getSpinFromHour());
				start.set(Calendar.MINUTE, getSpinFromMinutes());
				end.set(Calendar.HOUR, getSpinToHour());
				end.set(Calendar.MINUTE, getSpinToMinutes());
				if(!getTitle().equals("")) {
					Activity a = ActivityFactory.createEvent(getTitle(), start, end);
					if(controller.isActivityValid(a)) {
						controller.addActivity(a);
						JOptionPane.showMessageDialog(null, "Event Successfully Created!", "Event Created", JOptionPane.INFORMATION_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(null, "Event Invalid", "Failed", JOptionPane.ERROR_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null, "Fill Up All Fields", "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else if(getSelectedRB().equalsIgnoreCase("Task")) {
				Calendar start = new GregorianCalendar(getSpinYear(), getIntSpinMonth(), getSpinDay());
				start.set(Calendar.SECOND, 0);
				start.set(Calendar.HOUR, getSpinFromHour());
				start.set(Calendar.MINUTE, getSpinFromMinutes());
				if(!getTitle().equals("")) {
					Activity a = ActivityFactory.createToDo(getTitle(), start);
					if(controller.isActivityValid(a)) {
						controller.addActivity(a);
						JOptionPane.showMessageDialog(null, "Task Successfully Created!", "Task Created", JOptionPane.INFORMATION_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(null, "Task Invalid", "Failed", JOptionPane.ERROR_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null, "Fill Up All Fields", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	*/
	class RegenerateDay implements ChangeListener {
		
		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			SpinnerModel model = new SpinnerNumberModel(1, 1, CalendarCalculator.getNoDays(getSpinYear(), getIntSpinMonth()), 1);
			spinDay.setModel(model);		
			resetDay();
			revalidate();
			repaint();
		}
	}
	/*
	class BtnDiscard implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			clearAll();
		}
	}
	
	class HideFromTime implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(jrbRecurring.isSelected()) {
				spinToHour.setVisible(false);
				spinToMinutes.setVisible(false);
				lblTColon.setVisible(false);
				lblTo.setVisible(false);
			}
		}
	}
	
	class ShowFromTime implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(!jrbRecurring.isSelected()) {
				spinToHour.setVisible(true);
				spinToMinutes.setVisible(true);
				lblTColon.setVisible(true);
				lblTo.setVisible(true);
			}
		}
	}*/
}