package view.client;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.Border;

import controller.ClientController;
import values.Month;

public class PanelMenu extends JPanel {
	
	private JLabel lblTitle;
	private JLabel lblDate;
	private ButtonGroup bg;
	private JButton btnToday;
	private JToggleButton btnDay;
	private JToggleButton btnReservation;
	private JToggleButton btnWeek;

	private ClientController controller;

	public PanelMenu(ClientController controller, int year, int month, int day) {
		this.controller = controller;
		
		this.setLayout(null);
		this.setBackground(Color.decode("#ECC3BF"));
		this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
		
		this.initComp(month, day, year);
		
		this.addPlaceComp();
		
		this.addActionListeners();
	}
	
	private void initComp(int month, int day, int year) {
		bg = new ButtonGroup();
		btnToday = new JButton("Today");
		btnDay = new JToggleButton("Day");
		btnWeek = new JToggleButton("Week");
		btnReservation = new JToggleButton("Reservation");
		lblTitle = new JLabel("Client View");
		lblDate = new JLabel(Month.values()[month].toShortString() + " " + day + ", " + year);
		
		lblTitle.setFont(new Font("Sans Serif", Font.BOLD, 16));
		lblTitle.setForeground(Color.white);
		lblDate.setFont(new Font("Sans Serif", Font.BOLD, 16));
		lblDate.setForeground(Color.white);
		btnToday.setFont(new Font("Sans Serif", Font.BOLD, 16));
		btnToday.setForeground(Color.white);
		btnToday.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#d4afab")));
		btnDay.setFont(new Font("Sans Serif", Font.BOLD, 12));
		btnDay.setForeground(Color.white);
		btnDay.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#d4afab")));
		btnWeek.setFont(new Font("Sans Serif", Font.BOLD, 14));
		btnWeek.setForeground(Color.white);
		btnWeek.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#d4afab")));
		btnReservation.setFont(new Font("Sans Serif", Font.BOLD, 11));
		btnReservation.setForeground(Color.white);
		btnReservation.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#d4afab")));
		
		btnDay.setSelected(true);
		btnToday.setContentAreaFilled(true);
		btnToday.setBackground(Color.decode("#EEE5DE"));
		btnDay.setBackground(Color.decode("#EEE5DE"));
		btnWeek.setBackground(Color.decode("#EEE5DE"));
		btnReservation.setBackground(Color.decode("#EEE5DE"));
		
	}
	
	private void addPlaceComp() {
		this.add(btnToday);
		this.add(btnDay);
		this.add(btnWeek);
		this.add(btnReservation);
		this.add(lblTitle);
		this.add(lblDate);

		bg.add(btnToday);
		bg.add(btnDay);
		bg.add(btnWeek);
		bg.add(btnReservation);
		
		lblTitle.setBounds(35, 5, 200, 50);
		btnToday.setBounds(245, 10, 100, 40);
		lblDate.setBounds(450, 5, 200, 50);
		btnDay.setBounds(581, 10, 100, 40);
		btnWeek.setBounds(680, 10, 100, 40);
		btnReservation.setBounds(779, 10, 100, 40);
	}
	
	public void update(int month, int day, int year) {
		this.setLblDate(month, day, year);
	}
	
	private void setLblDate(int month, int day, int year) {
		lblDate.setText(Month.values()[month].toShortString() + " " + day + ", " + year);
	}

	public void unselectToggleBtns() {
		for (Enumeration<AbstractButton> bg = this.bg.getElements(); bg.hasMoreElements();) {
			AbstractButton button = bg.nextElement();
			
			button.setSelected(false);
		}		
		System.out.println("SELECT FALSE");
		btnDay.setSelected(false);
		btnWeek.setSelected(false);
		btnReservation.setSelected(false);
	}
	
	public void addActionListeners() {
		btnToday.addActionListener(new BtnToday());
		btnDay.addActionListener(new BtnDay());
		btnWeek.addActionListener(new BtnWeek());
		btnReservation.addActionListener(new BtnReservation());
	}
	
	class BtnToday implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			controller.resetDay();
		}
	}
	
	class BtnDay implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			controller.setMainPanel(ClientViewController.PANEL_DAY);
		}
	}
	
	class BtnWeek implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			controller.setMainPanel(ClientViewController.PANEL_WEEK);
		}
	}
	
	class BtnReservation implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			controller.setMainPanel(ClientViewController.PANEL_RESERVATION);
		}
	}
	
}                       