package view.doctor;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.CalendarCalculator;
import values.Month;

public class PanelModifyDelete extends JPanel {

	private JLabel lblNew;
	private JLabel lblTo;
	private JLabel lblTColon;
	private JLabel lblFColon;
	private JButton btnDelete;
	private boolean toDelete;
	
	// Spinners
	private JSpinner spinFromMinutes;
	private JSpinner spinFromHour;
	private JSpinner spinToMinutes;
	private JSpinner spinToHour;

	private JSpinner spinMonth;
	private JSpinner spinDay;
	private JSpinner spinYear;

	public PanelModifyDelete(int year, int month, int day) {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(490, 180));
		this.setOpaque(true);
		
		this.initComp(year, month, day);
		this.addPlaceComp();
		this.addListeners();
	}

	private void initComp(int year, int month, int day) {
		toDelete = false;
		lblNew = new JLabel("Set new appointment schedule: ");
		lblTo = new JLabel("to");
		lblTColon = new JLabel(":");
		lblFColon = new JLabel(":");
		btnDelete = new JButton("Delete");
		lblNew.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		lblTo.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		lblTColon.setFont(new Font("Sans Serif", Font.BOLD, 16));
		lblFColon.setFont(new Font("Sans Serif", Font.BOLD, 16));
		btnDelete.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		
		// Spinners
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
	}
	
	private void addPlaceComp() {
		this.add(lblNew);
		this.add(spinFromHour);
		this.add(spinFromMinutes);
		this.add(spinToHour);
		this.add(spinToMinutes);
		this.add(spinMonth);
		this.add(spinDay);
		this.add(spinYear);
		this.add(lblTo);
		this.add(lblFColon);
		this.add(lblTColon);
		this.add(btnDelete);
		
		lblNew.setBounds(15, 10, 250, 40);
		spinMonth.setBounds(10, 60, 60, 40);
		spinDay.setBounds(70, 60, 40, 40);
		spinYear.setBounds(110, 60, 70, 40);
		
		spinFromHour.setBounds(195, 60, 50, 40);
		lblFColon.setBounds(250, 60, 10, 40);
		spinFromMinutes.setBounds(260, 60, 50, 40);
		
		lblTo.setBounds(322, 70, 20, 20);
		
		spinToHour.setBounds(350, 60, 50, 40);
		lblTColon.setBounds(405, 60, 10, 40);
		spinToMinutes.setBounds(415, 60, 50, 40);
		
		btnDelete.setBounds(365, 120, 100, 40);
	}
	
	private void addListeners() {
		
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Window w = SwingUtilities.getWindowAncestor(btnDelete);
				
			    if (w != null) {
			    	toDelete = true;
			    	w.setVisible(false);
			    }
			}
			
			
		});
		
	}
	
	public boolean toDelete() {
		return toDelete;
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

	public boolean isValidTime() {
		if((getSpinFromHour() > getSpinToHour() || (getSpinFromHour() == getSpinToHour() && getSpinFromMinutes() >= getSpinToMinutes()) ))
			return false;
		else return true;
		
	}
	
	public boolean isWeekend() {
		return getSpinDate().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || getSpinDate().get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
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
