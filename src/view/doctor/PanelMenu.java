package view.doctor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

//import controller.Controller;
import values.Month;

public class PanelMenu extends JPanel {
	private JLabel lblTitle;
	private JLabel lblDate;

	private ButtonGroup bgView;
	private ButtonGroup bgScope;
	
	private JButton btnToday;
	
	private JToggleButton btnDay;
	private JToggleButton btnWeek;
	
	private JToggleButton btnCal;
	private JToggleButton btnAgenda;

	private Image imgIcon;
	private JLabel lblIcon;
	// private Controller controller;

	private ViewController vc;

	public PanelMenu(ViewController vc, int year, int month, int day) {
		// public PanelMenu(Controller controller, int year, int month, int day)
		// {
		// this.controller = controller;

		this.vc = vc;

		this.setLayout(null);
		this.setPreferredSize(new Dimension(900, 60));
		this.setBackground(new Color(97, 216, 182));
		this.setBorder(BorderFactory.createLineBorder(new Color(97, 216, 182), 2));

		this.initComp(month, day, year);

		this.addPlaceComp();

		// this.addActionListeners();

		this.testAddListeners();
	}

	private void initComp(int month, int day, int year) {
		bgView = new ButtonGroup();
		bgScope = new ButtonGroup();
		
		btnToday = new JButton("Today");
		btnCal = new JToggleButton("Calendar");
		btnDay = new JToggleButton("Day");
		btnWeek = new JToggleButton("Week");
		btnAgenda = new JToggleButton("Agenda");
		lblTitle = new JLabel("Doctor View");
		lblDate = new JLabel(Month.values()[month].toShortString() + " " + day + ", " + year);
		lblIcon = new JLabel();
		try {
			imgIcon = ImageIO.read(getClass().getResource("/image/doctor.png"));
			imgIcon = imgIcon.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
			lblIcon.setIcon(new ImageIcon(imgIcon));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("uhoh");
			e.printStackTrace();
		}
		
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitle.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Sans Serif", Font.BOLD, 16));
		lblDate.setForeground(Color.WHITE);
		btnToday.setFont(new Font("Sans Serif", Font.BOLD, 16));
		btnToday.setForeground(Color.WHITE);
		btnCal.setFont(new Font("Sans Serif", Font.BOLD, 16));
		btnCal.setForeground(Color.WHITE);
		btnAgenda.setFont(new Font("Sans Serif", Font.BOLD, 16));
		btnAgenda.setForeground(Color.WHITE);
		btnDay.setFont(new Font("Sans Serif", Font.BOLD, 16));
		btnDay.setForeground(Color.WHITE);
		btnWeek.setFont(new Font("Sans Serif", Font.BOLD, 16));
		btnWeek.setForeground(Color.WHITE);

		UIManager.put("ToggleButton.select", new Color(60, 168, 138));
		SwingUtilities.updateComponentTreeUI(btnCal);

		btnWeek.setSelected(true);
		
		btnCal.setSelected(true);
		btnToday.setContentAreaFilled(false);
		btnToday.setBorder(BorderFactory.createLineBorder(new Color(70, 206, 168), 1, true));
		btnCal.setBackground(new Color(70, 206, 168));
		// btnCal.setBorder(BorderFactory.createEmptyBorder());
		btnAgenda.setBackground(new Color(70, 206, 168));
		// btnAgenda.setBorder(BorderFactory.createEmptyBorder());
	}

	private void addPlaceComp() {
		this.add(btnToday);
		this.add(btnCal);
		this.add(btnAgenda);
		this.add(lblTitle);
		this.add(lblDate);
		this.add(lblIcon);
		this.add(btnDay);
		this.add(btnWeek);

		bgView.add(btnToday);
		bgView.add(btnCal);
		bgView.add(btnAgenda);
		
		bgScope.add(btnDay);
		bgScope.add(btnWeek);
		
		lblIcon.setBounds(20, 10, 40, 40);
		lblTitle.setBounds(70, 5, 200, 50);
		btnToday.setBounds(220, 10, 100, 40);
		lblDate.setBounds(350, 5, 200, 50);
		
		btnDay.setBounds(480, 10, 100, 40);
		btnWeek.setBounds(578, 10, 100, 40);
		
		btnCal.setBounds(690, 10, 120, 40);
		btnAgenda.setBounds(809, 10, 100, 40);
	}

	public void update(int month, int day, int year) {
		this.setLblDate(month, day, year);
	}

	private void setLblDate(int month, int day, int year) {
		lblDate.setText(Month.values()[month].toShortString() + " " + day + ", " + year);
	}

	public void unselectToggleBtns() {
		for (Enumeration<AbstractButton> bgView = this.bgView.getElements(); bgView.hasMoreElements();) {
			AbstractButton button = bgView.nextElement();

			button.setSelected(false);
		}
		System.out.println("SELECT FALSE");
		btnCal.setSelected(false);
		btnAgenda.setSelected(false);
	}

	public void testAddListeners() {
		btnCal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnWeek.isSelected())
					vc.setMainPanel(vc.PANEL_WEEK);
				else if (btnDay.isSelected())
					vc.setMainPanel(vc.PANEL_DAY);
			}

		});
		btnAgenda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vc.setMainPanel(vc.PANEL_AGENDA);
			}
		});
		btnDay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnCal.isSelected())
					vc.setMainPanel(vc.PANEL_DAY);
				else if (btnAgenda.isSelected())
					vc.setMainPanel(vc.PANEL_AGENDA);
			}
		});
		btnWeek.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnCal.isSelected())
					vc.setMainPanel(vc.PANEL_WEEK);
				else if (btnAgenda.isSelected())
					vc.setMainPanel(vc.PANEL_AGENDA);
			}
		});
	}
	/*
	 * public void addActionListeners() { btnToday.addActionListener(new
	 * BtnToday()); btnCal.addActionListener(new btnCal());
	 * btnAgenda.addActionListener(new BtnAgenda()); }
	 * 
	 * class BtnToday implements ActionListener {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { // TODO
	 * Auto-generated method stub controller.resetDay(); } }
	 * 
	 * class btnCal implements ActionListener {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { // TODO
	 * Auto-generated method stub
	 * controller.setMainPanel(ViewController.PANEL_DAY); } }
	 * 
	 * class BtnAgenda implements ActionListener {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { // TODO
	 * Auto-generated method stub
	 * controller.setMainPanel(ViewController.PANEL_AGENDA); } }
	 */
}