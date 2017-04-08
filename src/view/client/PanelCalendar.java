package view.client;

import values.DayHeader;
import values.Month;
import model.CalendarCalculator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.*;
import javax.swing.table.*;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.*;

public class PanelCalendar extends JPanel {

	private JLabel monthLabel, filterLabel;
	private JButton btnPrev, btnNext;
	private JScrollPane scrollCalendarTable;
	private JTable calendarTable;
	private DefaultTableModel modelCalendarTable;
	private JButton btnCreate;
	private JButton btnFilterDoctor;
	private JScrollPane doctorPane;
	private JList<String> doctorList;
	private DefaultListModel<String> listDoctor;
	
	private Controller controller;

	public PanelCalendar(Controller controller, int currYear, int currMonth, int currDay) {
		this.controller = controller;
		
		// Set panel properties
		this.setSize(640, 670);
		this.setLayout(null);
		this.setBackground(Color.decode("#F5E1DF"));
		
		// Initialize panel components
		this.initComp(currMonth);

		// Add panel components
		this.addPlaceComp();
		
		// Add table cell content
		this.update(currMonth, currYear, currDay);
		
		// Select cell to current day
		this.setSelectedCell(currDay);
		
		// Add listeners
		this.addActionListeners();
	}

	public void initComp(int currMonth) {
		
		monthLabel = new JLabel(Month.values()[currMonth].toString());
		filterLabel = new JLabel("Filter");
		btnPrev = new JButton("<");
		btnNext = new JButton(">");
		btnCreate = new JButton("Add Appointment");
		listDoctor = new DefaultListModel<>();
		btnFilterDoctor = new JButton("Filter");
		doctorList = new JList<>(listDoctor);
		doctorPane = new JScrollPane(doctorList);
		
		filterLabel.setFont(new Font("Sans Serif", Font.BOLD, 16));
		
		btnCreate.setForeground(Color.WHITE);
		btnCreate.setFont(new Font("Sans Serif", Font.BOLD, 16));
		btnCreate.setBackground(Color.decode("#BC9C98"));
		btnCreate.setOpaque(true);
		btnCreate.setBorder(BorderFactory.createEmptyBorder());
		btnCreate.setCursor(new Cursor(Cursor.HAND_CURSOR));

		btnNext.setForeground(Color.GRAY);
		btnNext.setOpaque(false);
		btnNext.setBorder(BorderFactory.createEmptyBorder());
		btnNext.setContentAreaFilled(false); 
		btnNext.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnPrev.setForeground(Color.GRAY);
		btnPrev.setOpaque(false);
		btnPrev.setBorder(BorderFactory.createEmptyBorder());
		btnPrev.setContentAreaFilled(false); 
		btnPrev.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnFilterDoctor.setForeground(Color.WHITE);
		btnFilterDoctor.setFont(new Font("Sans Serif", Font.BOLD, 12));
		btnFilterDoctor.setBackground(Color.decode("#BC9C98"));
		btnFilterDoctor.setOpaque(true);
		btnFilterDoctor.setBorder(BorderFactory.createEmptyBorder());
		btnFilterDoctor.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		doctorPane.setBorder(BorderFactory.createEmptyBorder());
		
		doctorList.setBackground(Color.decode("#fcf6f5"));
		doctorList.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode("#d4afab")));
		doctorList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		// Set all cells to uneditable
		modelCalendarTable = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		
		calendarTable = new JTable(modelCalendarTable);
		scrollCalendarTable = new JScrollPane(calendarTable);

		// Add name of day to column header
		for (int i = 0; i < 7; i++) {
			modelCalendarTable.addColumn(DayHeader.values()[i].toString());
		}
		
		scrollCalendarTable.setBorder(BorderFactory.createEmptyBorder());
		calendarTable.setShowGrid(false);
		calendarTable.getParent().setBackground(calendarTable.getBackground()); 
																				
		calendarTable.getTableHeader().setResizingAllowed(false);
		calendarTable.getTableHeader().setReorderingAllowed(false);

		calendarTable.setColumnSelectionAllowed(true);
		calendarTable.setRowSelectionAllowed(true);
		calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		calendarTable.setRowHeight(30);
		modelCalendarTable.setColumnCount(7);
		modelCalendarTable.setRowCount(6);
		
	}

	private void addPlaceComp() {
		// Add panel components
		this.add(monthLabel);
		this.add(scrollCalendarTable);
		this.add(btnCreate);
		this.add(btnNext);
		this.add(btnPrev);
		this.add(btnFilterDoctor);
		this.add(filterLabel);
		this.add(doctorPane);

		// Place components on layout (includes setting of size)
		monthLabel.setBounds(15, 70, 100, 20);
		btnPrev.setBounds(150, 65, 25, 25);
		btnNext.setBounds(180, 65, 25, 25);
		btnCreate.setBounds(15, 15, 190, 40);
		scrollCalendarTable.setBounds(15, 100, 190, 205);
		filterLabel.setBounds(15, 310, 100, 40);
		doctorPane.setBounds(15, 345, 190, 130);
		btnFilterDoctor.setBounds(15, 480, 100, 20);
	}
	
	private void createDateLabel(int currMonth, int currYear) {
		String s = "" + Month.values()[currMonth].toString() + " " + currYear;
		
		monthLabel.setText(s);	
	}

	public void update(int month, int year, int day) {

		// Enable previous and next buttons
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		btnFilterDoctor.setEnabled(true);

		/*
		// If month is January and 100 years less than current year
		if (month == 0 && year <= Integer.parseInt((String) cmbYear.getItemAt(0)))
			btnPrev.setEnabled(false);
		// If month is December and 100 years more than current year
		if (month == 11 && year >= Integer.parseInt((String) (cmbYear.getItemAt(cmbYear.getItemCount() - 1))))
			btnNext.setEnabled(false);
		*/

		// Set month label to current month
		this.createDateLabel(month, year);

		// Set all table values to null
		this.setTableNull();

		// Set table values
		this.setTableValues(CalendarCalculator.getNoDays(year, month), CalendarCalculator.getStartMonth(year, month));
		
		// Select cell to current day
		this.setSelectedCell(day);
				
		// Set selected
		calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new TableRenderer());
	}

	public void setTableValues(int nod, int som) {
		// System.out.println("" + nod + " " + som);
		for (int i = 1; i <= nod; i++) {
			int row = new Integer((i + som - 2) / 7);
			int column = (i + som - 2) % 7;
			modelCalendarTable.setValueAt(i, row, column);
		}
	}
	public void setTableNull() {
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 7; j++)
				modelCalendarTable.setValueAt(null, i, j);
	}
	
	public void setSelectedCell(int day) {
		for(int i = 0; i < calendarTable.getRowCount(); i++) {
			for(int j = 0; j < calendarTable.getColumnCount(); j++) {
				if(calendarTable.getValueAt(i, j) != null && (Integer)calendarTable.getValueAt(i, j) == day)
					calendarTable.changeSelection(i, j, false, false);
			}
		}
	}

	public int[] getIndexOfDay(int day) {
		int[] indexes = new int[2];
		
		indexes[0] = -1;
		indexes[1] = -1;
		
		for(int i = 0; i < calendarTable.getRowCount(); i++) {
			for(int j = 0; j < calendarTable.getColumnCount(); j++) {
				if(calendarTable.getValueAt(i, j) != null && (Integer)calendarTable.getValueAt(i, j) == day) {
					indexes[0] = i;
					indexes[1] = j;
					break;
				}		
			}
		}
		
		return indexes;
	}
	public void enableBtnCreate() {
		if(!btnCreate.isEnabled())
			btnCreate.setEnabled(true);
	}
	
	public void addActionListeners() {
		btnNext.addActionListener(new BtnNext());
		btnPrev.addActionListener(new BtnPrev());
		btnCreate.addChangeListener(new RecolorBtnCreate());
		btnCreate.addActionListener(new BtnCreate());
		btnFilterDoctor.addActionListener(new BtnFilterDoctor());
		calendarTable.addMouseListener(new ChangeDate());
	}
	
	class BtnFilterDoctor implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			System.out.println(doctorList.getSelectedValuesList());
		}
		
	}
	
	class BtnNext implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			controller.addMonth();
		}
	}
	
	class BtnPrev implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			controller.subMonth();
		}
	}
	
	class RecolorBtnCreate implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			if(!btnCreate.isEnabled()) {
				btnCreate.setBackground(Color.LIGHT_GRAY);
			}
			else btnCreate.setBackground(Color.decode("#BC9C98"));
		}
	}
	
	class BtnCreate implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.setMainPanel(ClientViewController.PANEL_CREATE);
			if(btnCreate.isEnabled())
				btnCreate.setEnabled(false);
		}
	}
	
	class FilterCheckBox implements ActionListener {
		@Override 
		public void actionPerformed(ActionEvent e) {
			controller.updateView();
		}
	}

	class ChangeDate implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			JTable table = (JTable)e.getSource();
			
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();
			if(table.getValueAt(row, col) != null) {
				int day = (Integer)table.getValueAt(row, col);
				controller.setDayToday(day);
			}
			else {
				int[] indexes = getIndexOfDay(1);
				int day = (Integer)table.getValueAt(indexes[0], indexes[1]);
				setSelectedCell(1);
				controller.setDayToday(day);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
