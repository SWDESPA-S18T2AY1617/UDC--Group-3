package view.client;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.ClientController;
import model.Appointment;
import model.CalendarCalculator;
import model.CalendarModel;
import values.Month;

public class PanelCreate extends JPanel{
	
	private JTextField jtxtTitle;
	private ButtonGroup bg;
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
	
	private ClientController controller;

	public PanelCreate(ClientController controller, int year, int month, int day) {
		this.controller = controller;
		
		this.setLayout(null);
		this.setBackground(Color.WHITE);

		this.initComp(year, month, day);

		this.addPlaceComp();
		this.addListeners();
	}

	private void initComp(int year, int month, int day) {
		// initialize swing components
		jtxtTitle = new JTextField();
		lblTo = new JLabel("to");
		lblTColon = new JLabel(":");
		lblFColon = new JLabel(":");
		btnSave = new JButton("Save");
		btnDiscard = new JButton("Discard");
		bg = new ButtonGroup();

		// initialize spinners
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
		
		// edit swing component properties

		btnSave.setBackground(new Color(211, 72, 54));
		btnSave.setForeground(Color.WHITE);
		btnSave.setBorder(BorderFactory.createEmptyBorder());
		btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnDiscard.setOpaque(false);
		btnDiscard.setContentAreaFilled(false); 
		btnDiscard.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		jtxtTitle.setFont(new Font("Sans Serif", Font.PLAIN, 16));
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
		this.add(jtxtTitle);
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
		
		// set component bounds (and size!)
		spinFromHour.setBounds(200, 110, 50, 40);
		lblFColon.setBounds(255, 110, 10, 40);
		spinFromMinutes.setBounds(265, 110, 50, 40);
		
		spinToHour.setBounds(355, 110, 50, 40);
		lblTColon.setBounds(410, 110, 10, 40);
		spinToMinutes.setBounds(420, 110, 50, 40);
		
		jtxtTitle.setBounds(15, 15, 460, 40);
		spinMonth.setBounds(15, 110, 60, 40);
		spinDay.setBounds(75, 110, 40, 40);
		spinYear.setBounds(115, 110, 70, 40);
		lblTo.setBounds(327, 120, 20, 20);
		btnSave.setBounds(270, 170, 90, 40);
		btnDiscard.setBounds(380, 170, 90, 40);
	}
	
	public String getSelectedRB() {
		for (Enumeration<AbstractButton> buttons = this.bg.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected())
				return button.getText().toLowerCase();
		}

		return "";
	}
	
	public void clearTFTitle() {
		this.jtxtTitle.setText("");
	}
	
	public String getTitle() {
		return jtxtTitle.getText();
	}
	
	public int getIntSpinMonth() {
		return Month.getIndexShortString((String)spinMonth.getValue());
	}
	
	public int getSpinYear() {
		return (Integer)spinYear.getValue();
	}
	
	public int getSpinDay() {
		return (Integer)spinDay.getValue();
	}
	
	public int getSpinToHour() {
		return (Integer)spinToHour.getValue();
	}
	
	public int getSpinToMinutes() {
		return (Integer)spinToMinutes.getValue();
	}
	
	public int getSpinFromHour() {
		return (Integer)spinFromHour.getValue();
	}
	
	public int getSpinFromMinutes() {
		return (Integer)spinFromMinutes.getValue();
	}
	
	public Calendar getSelectedDate() {
		return null;
	}
	
	public Calendar getFromTime() {
		Calendar c = Calendar.getInstance();
		
		c.set(this.getSpinYear(), this.getIntSpinMonth(), this.getSpinDay(), this.getSpinFromHour(), this.getSpinFromMinutes());
		
		return c;
	}
	
	public Calendar getToTime() {
		Calendar c = Calendar.getInstance();
		
		c.set(this.getSpinYear(), this.getIntSpinMonth(), this.getSpinDay(), this.getSpinToHour(), this.getSpinToMinutes());
		
		return c;
	}
	
	public Calendar getSpinDate() {
		Calendar c = Calendar.getInstance();
		
		c.set(this.getSpinYear(), this.getIntSpinMonth(), this.getSpinDay());
		
		return c;
	}
	
	public void resetDay() {
		this.spinDay.setValue(((SpinnerNumberModel)(spinDay.getModel())).getMinimum());
	}
	
	public void clearAll() {
		this.clearTFTitle();
		this.spinFromMinutes.setValue(((SpinnerNumberModel)(spinFromMinutes.getModel())).getMinimum());
		this.spinFromHour.setValue(((SpinnerNumberModel)(spinFromHour.getModel())).getMinimum());
		this.spinToMinutes.setValue(((SpinnerNumberModel)(spinToMinutes.getModel())).getMinimum());
		this.spinToHour.setValue(((SpinnerNumberModel)(spinToHour.getModel())).getMinimum());
		this.spinMonth.setValue(((SpinnerListModel)(spinMonth.getModel())).getList().get(0));
		this.spinDay.setValue(((SpinnerNumberModel)(spinDay.getModel())).getMinimum());
		this.spinYear.setValue((Integer)(((SpinnerNumberModel)(spinYear.getModel())).getMinimum()) + 100);
	}
	
	public void addListeners() {
		btnSave.addActionListener(new BtnSave());
		btnDiscard.addActionListener(new BtnDiscard());
		spinMonth.addChangeListener(new RegenerateDay());
		spinYear.addChangeListener(new RegenerateDay());
	}
		
	class BtnSave implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			Calendar start = new GregorianCalendar(getSpinYear(), getIntSpinMonth(), getSpinDay());
			start.set(Calendar.SECOND, 0);
			Calendar end = (Calendar) start.clone();
			start.set(Calendar.HOUR, getSpinFromHour());
			start.set(Calendar.MINUTE, getSpinFromMinutes());
			end.set(Calendar.HOUR, getSpinToHour());
			end.set(Calendar.MINUTE, getSpinToMinutes());
			if(!getTitle().equals("")) {
				Appointment a = new Appointment(start, end);
			//	if(controller.isActivityValid(a)) {
				//	controller.addActivity(a);
				//	JOptionPane.showMessageDialog(null, "Event Successfully Created!", "Event Created", JOptionPane.INFORMATION_MESSAGE);
			//}
			//else
			//		JOptionPane.showMessageDialog(null, "Event Invalid", "Failed", JOptionPane.ERROR_MESSAGE);
		//	}
		//	else
			//	JOptionPane.showMessageDialog(null, "Fill Up All Fields", "Warning", JOptionPane.WARNING_MESSAGE);
			}	
		}
	}
	
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
	
	class BtnDiscard implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			clearAll();
		}
	}
}