package view.secretary;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import controller.SecretaryController;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.Border;

public class PanelBookingView extends JPanel{
	private SecretaryController vc;

	private JTextArea bookDetails;
	private JRadioButton radioDoc1,
						 radioDoc2,
						 radioDoc3;
	private ButtonGroup radioGroup;
	private JComboBox addDay,
					  addMonth,
					  addYear;
	private JSpinner timeStartHour,
    				 timeStartMinute,
    				 timeEndHour,
    				 timeEndMinute;
    private JButton btnDiscard,
		 		    btnSave;
	private JLabel lblStartTime,
				   lblEndTime,
				   lblDashTime,
				   lblDate,
				   lblDesc;
	private int currYear;

	public PanelBookingView(SecretaryController secretaryController){
		this.vc = secretaryController;
			
		this.setSize(550, 500);
		this.setLayout(null);
		this.setBackground(new Color(31, 31, 31));
		this.setBorder(BorderFactory.createTitledBorder(""));

		this.initParts();
		this.addsetParts();
		this.addActionListeners();
	}
	public void initParts(){
		
		lblDesc = new JLabel("Event Description:");
		bookDetails = new JTextArea("");
		radioDoc1 = new JRadioButton("Doc 1");
		radioDoc2 = new JRadioButton("Doc 2");
		radioDoc3 = new JRadioButton("Doc 3");
		radioGroup = new ButtonGroup();

		lblStartTime = new JLabel("Time Start: ");
		lblEndTime = new JLabel("Time End: ");
		lblDashTime = new JLabel(" ----> ");
		lblDate = new JLabel("Date: ");

		String[] months =  {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
		addMonth = new JComboBox(months);

		addDay = new JComboBox();
		for (int i = 1; i <= 31; i++)
			addDay.addItem(String.valueOf(i));

		addYear = new JComboBox();
		for (int i = 2017; i <= 2017+100; i++)
			addYear.addItem(String.valueOf(i));

		SpinnerModel starthourModel = new SpinnerNumberModel(0, 0, 24, 1);
		SpinnerModel endhourModel = new SpinnerNumberModel(0, 0, 24, 1);
		SpinnerModel startminuteModel = new SpinnerNumberModel(0, 0, 30, 30);
		SpinnerModel endminuteModel = new SpinnerNumberModel(0, 0, 30, 30);

		timeStartHour = new JSpinner(starthourModel);
		timeStartMinute = new JSpinner(startminuteModel);
		timeEndHour = new JSpinner(endhourModel);
		timeEndMinute = new JSpinner(endminuteModel);

		btnDiscard = new JButton("Discard");
		btnSave = new JButton("Save");

		lblDesc.setFont(new Font("Charlemagne STD", Font.BOLD, 15));
		lblDesc.setForeground(new Color(120, 120, 120));

		bookDetails.setFont(new Font("Charlemagne STD", Font.BOLD, 15));
		bookDetails.setBackground(new Color(120, 120, 120));
		bookDetails.setForeground(new Color(240, 240, 240));
		bookDetails.setLineWrap(true);
		bookDetails.setWrapStyleWord(true);

		radioDoc1.setFont(new Font("Charlemagne STD", Font.BOLD, 15));
		radioDoc1.setForeground(new Color(120, 120, 120));
		radioDoc1.setOpaque(false);
		radioDoc1.setContentAreaFilled(false);
		radioDoc1.setActionCommand("Doctor1");
		radioDoc1.setSelected(true);

		radioDoc2.setFont(new Font("Charlemagne STD", Font.BOLD, 15));
		radioDoc2.setForeground(new Color(120, 120, 120));
		radioDoc2.setOpaque(false);
		radioDoc2.setContentAreaFilled(false);
		radioDoc2.setActionCommand("Doctor2");

		radioDoc3.setFont(new Font("Charlemagne STD", Font.BOLD, 15));
		radioDoc3.setForeground(new Color(120, 120, 120));
		radioDoc3.setOpaque(false);
		radioDoc3.setContentAreaFilled(false);
		radioDoc3.setActionCommand("Doctor3");
		
		radioGroup.add(radioDoc1);
		radioGroup.add(radioDoc2);
		radioGroup.add(radioDoc3);

		lblStartTime.setForeground(new Color(120, 120, 120));
		lblStartTime.setFont(new Font("Charlemagne STD", Font.BOLD, 15));

		lblEndTime.setForeground(new Color(120, 120, 120));
		lblEndTime.setFont(new Font("Charlemagne STD", Font.BOLD, 15));

		lblDashTime.setForeground(new Color(120, 120, 120));
		lblDashTime.setFont(new Font("Charlemagne STD", Font.BOLD, 15));

		lblDate.setForeground(new Color(120, 120, 120));
		lblDate.setFont(new Font("Charlemagne STD", Font.BOLD, 15));

		btnDiscard.setFont(new Font("Charlemagne STD", Font.BOLD, 15));
		btnDiscard.setForeground(new Color(120, 120, 120));

		btnSave.setFont(new Font("Charlemagne STD", Font.BOLD, 15));
		btnSave.setForeground(new Color(120, 120, 120));

	}
	public void addsetParts(){

		this.add(lblDesc);
		this.add(bookDetails);
		this.add(radioDoc1);
		this.add(radioDoc2);
		this.add(radioDoc3);

		this.add(lblStartTime);
		this.add(lblEndTime);
		this.add(lblDashTime);
		this.add(lblDate);

		this.add(addMonth);
		this.add(addDay);
		this.add(addYear);

		this.add(timeStartHour);
		this.add(timeStartMinute);
		this.add(timeEndHour);
		this.add(timeEndMinute);

		this.add(btnDiscard);
		this.add(btnSave);


		radioDoc1.setBounds(50, 30, 100, 40);
		radioDoc2.setBounds(225, 30, 100, 40);
		radioDoc3.setBounds(400, 30, 100, 40);

		lblStartTime.setBounds(250, 90, 150, 40);
		lblEndTime.setBounds(410, 90, 100, 40);
		lblDashTime.setBounds(360, 120, 100, 40);
		lblDate.setBounds(100, 90, 100, 40);

		addMonth.setBounds(50, 120, 50, 30);
		addDay.setBounds(100, 120, 50, 30);
		addYear.setBounds(150, 120, 70, 30);

		timeStartHour.setBounds(250, 120, 50, 30);
		timeStartMinute.setBounds(300, 120, 50, 30);
		timeEndHour.setBounds(410, 120, 50, 30);
		timeEndMinute.setBounds(460, 120, 50, 30);

		lblDesc.setBounds(50, 170, 180, 40);
		bookDetails.setBounds(50, 210, 420, 200);

		btnSave.setBounds(220, 420, 100, 45);
		btnDiscard.setBounds(320, 420, 150, 45);
	}
	public void addActionListeners(){
		btnDiscard.addActionListener(new btnDiscard_Action());
		btnSave.addActionListener(new btnSave_Action());
	}
	public void setYearCombo(int currYear){
		this.currYear = currYear;
		
	}
	class btnDiscard_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			bookDetails.setText("");
			vc.setCalendarPanel();
		}
	}
	class btnSave_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
		//	vc.setAppointment();

			bookDetails.setText("");
			vc.setCalendarPanel();
		}
	}
	public String getRadioButtonChoice(){
		return radioGroup.getSelection().getActionCommand();
	}
	public String getBookingDetails(){
		return bookDetails.getText().trim();
	}
	
	public String getMonthInput(){
		return addMonth.getSelectedItem().toString();
	}
	
	public String getDayInput(){
		return addDay.getSelectedItem().toString();
	}
	
	public String getYearInput(){
		return addYear.getSelectedItem().toString();
	}
	
	public String getStartH(){
		return timeStartHour.getValue().toString();
	}
	
	public String getStartM(){
		return timeStartMinute.getValue().toString();
	}
	
	public String getEndH(){
		return timeEndHour.getValue().toString();
	}
	
	public String getEndM(){
		return timeEndMinute.getValue().toString();
	}
	
}


