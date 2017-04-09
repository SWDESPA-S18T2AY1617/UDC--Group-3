package view.secretary;

import controller.SecretaryController;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.border.Border;


public class PanelHeadline extends JPanel{
	
	private SecretaryController vc;
	private JFrame frmMain;
			   
    private JLabel lblviewName,
				   lblviewDate,
				   lblProdTool;
	

    private JButton btnCalendar,
		 		    btnAgenda,
		 		    btnWeekly,
		 		    btnDaily;
    private JCheckBox chckDoc1,
    				  chckDoc2,
    				  chckDoc3;

	public PanelHeadline(SecretaryController vc){
		this.vc = vc;
		this.setSize(900, 75);
		this.setLayout(null);
		this.setBackground(new Color(70, 70, 70));
		this.setBorder(BorderFactory.createTitledBorder(""));

		this.initParts();
		this.addsetParts();
		this.addActionListeners();

	}
	public void initParts(){

		lblProdTool = new JLabel("Secretary View");
		btnCalendar = new JButton("Calendar Form");
		btnAgenda = new JButton("Agenda Form");
		btnWeekly = new JButton("Weekly Form");
		btnDaily = new JButton("Daily Form");

		lblProdTool.setFont(new Font("Charlemagne STD", Font.BOLD, 20));
		lblProdTool.setForeground(new Color(32, 194, 14));
		
		btnCalendar.setFont(new Font("Charlemagne STD", Font.BOLD, 18));
		btnCalendar.setForeground(new Color(32, 194, 14));
		btnCalendar.setBackground(new Color(31, 31, 31));
		
		btnAgenda.setFont(new Font("Charlemagne STD", Font.BOLD, 18));
		btnAgenda.setForeground(new Color(30,144,255));
		btnAgenda.setBackground(new Color(31, 31, 31));
		
		btnWeekly.setFont(new Font("Charlemagne STD", Font.BOLD, 18));
		btnWeekly.setForeground(new Color(220,20,60));
		btnWeekly.setBackground(new Color(31, 31, 31));

		btnDaily.setFont(new Font("Charlemagne STD", Font.BOLD, 18));
		btnDaily.setForeground(new Color(32, 194, 14));
		btnDaily.setBackground(new Color(31, 31, 31));
	}

	public void addsetParts(){
		this.add(lblProdTool);
		this.add(btnCalendar);
		this.add(btnAgenda);
		this.add(btnWeekly);
		this.add(btnDaily);

		lblProdTool.setBounds(20, 30, 330, 40);
		btnCalendar.setBounds(400, 10, 250, 30);
		btnAgenda.setBounds(400, 40, 250, 30);	
		btnWeekly.setBounds(650, 10, 200, 30);	
		btnDaily.setBounds(650, 40, 200, 30);	
	}
	public void addActionListeners(){

		btnCalendar.addActionListener(new btnCalendarAction());
		btnAgenda.addActionListener(new btnAgendaAction());
		btnWeekly.addActionListener(new btnWeeklyAction());
		btnDaily.addActionListener(new btnDailyAction());
	}
	class btnCalendarAction implements ActionListener{
		public void actionPerformed (ActionEvent e){
			btnCalendar.setForeground(new Color(32, 194, 14));
			btnAgenda.setForeground(new Color(30,144,255));
			vc.setCalendarPanel();
		}
	}
	class btnAgendaAction implements ActionListener{
		public void actionPerformed (ActionEvent e){
			btnCalendar.setForeground(new Color(30,144,255));
			btnAgenda.setForeground(new Color(32, 194, 14));
			vc.setAgendaPanel();
		}
	}
	class btnWeeklyAction implements ActionListener{
		public void actionPerformed (ActionEvent e){
			btnWeekly.setForeground(new Color(32, 194, 14));
			btnDaily.setForeground(new Color(220,20,60));
			vc.setWeeklyFormat();
		}
	}
	class btnDailyAction implements ActionListener{
		public void actionPerformed (ActionEvent e){
			btnWeekly.setForeground(new Color(220,20,60));
			btnDaily.setForeground(new Color(32, 194, 14));
			
			vc.setDailyFormat();
		}
	}

}
