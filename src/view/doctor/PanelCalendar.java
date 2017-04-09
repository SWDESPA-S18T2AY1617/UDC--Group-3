package view.doctor;

import values.DayHeader;
import values.Month;
import model.CalendarCalculator;
//import model.Event;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.*;
import javax.swing.table.*;

import controller.DoctorController;

//import controller.Controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;

public class PanelCalendar extends JPanel {

	private JLabel monthLabel, filterLabel, doctorLabel;
	private JButton btnPrev, btnNext;
	private JScrollPane scrollCalendarTable;

	private JTable calendarTable;
	private DefaultTableModel modelCalendarTable;

	private JButton btnCreate;
	
	private JCheckBox chckAvailable;
	private JCheckBox chckUnavailable;
	
	private DoctorController docController;
	
	public PanelCalendar(DoctorController docController, int currYear, int currMonth, int currDay) {
		//this.controller = controller;
		
		this.docController = docController;
		// Set panel properties
		this.setPreferredSize(new Dimension(220, 670));
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
		// Initialize panel components
		this.initComp(currMonth);

		// Add panel components
		this.addPlaceComp();
		
		// Add table cell content
		this.update(currMonth, currYear, currDay);
		
		// Select cell to current day
		this.setSelectedCell(currDay);
		
		// Add listeners
		//this.addActionListeners();
		this.testAddListeners();
	}

	public void initComp(int currMonth) {
		monthLabel = new JLabel(Month.values()[currMonth].toString());
		doctorLabel = new JLabel("Welcome Dr. ");
		filterLabel = new JLabel("Filter");
		btnPrev = new JButton("<");
		btnNext = new JButton(">");
		btnCreate = new JButton("Create");
		chckAvailable = new JCheckBox("Available");
		chckUnavailable = new JCheckBox("Unavailable");
		
		doctorLabel.setFont(new Font("Sans Serif", Font.BOLD, 14));
		filterLabel.setFont(new Font("Sans Serif", Font.BOLD, 16));
		
		btnCreate.setForeground(Color.WHITE);
		btnCreate.setFont(new Font("Sans Serif", Font.BOLD, 16));
		btnCreate.setContentAreaFilled(false);
		btnCreate.setOpaque(true);
		btnCreate.setFocusPainted(false);
		btnCreate.setBackground(new Color(77, 148, 179));
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
		
		chckAvailable.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		chckUnavailable.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		chckAvailable.setSelected(true);
		chckUnavailable.setSelected(true);
		chckAvailable.setOpaque(false);
		chckUnavailable.setOpaque(false);
		
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
		this.add(filterLabel);
		this.add(chckAvailable);
		this.add(chckUnavailable);
		this.add(doctorLabel);
		
		// Place components on layout (includes setting of size)
		monthLabel.setBounds(15, 100, 100, 20);
		btnPrev.setBounds(150, 95, 25, 25);
		btnNext.setBounds(180, 95, 25, 25);
		doctorLabel.setBounds(15, 10, 190, 30);
		btnCreate.setBounds(15, 45, 190, 40);
		scrollCalendarTable.setBounds(15, 130, 190, 205);
		filterLabel.setBounds(15, 340, 100, 40);
		chckAvailable.setBounds(30, 375, 100, 40);
		chckUnavailable.setBounds(30, 415, 120, 40);
	}
	
	public void setDoctorName(String name) {
		doctorLabel.setText("Welcome Dr. " + name);
	}
	
	private void createDateLabel(int currMonth, int currYear) {
		String s = "" + Month.values()[currMonth].toString() + " " + currYear;
		
		monthLabel.setText(s);	
	}

	public void update(int month, int year, int day) {

		// Enable previous and next buttons
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);

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

	public boolean isTaskSelected() {
		return chckUnavailable.isSelected();
	}

	public boolean isEventSelected() {
		return chckAvailable.isSelected();
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
	
	public void testAddListeners() {
		btnCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				docController.setMainPanel(docController.PANEL_CREATE);
			}
			
		});
		chckAvailable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(chckAvailable.isSelected()) {
					docController.showAvailable(true);
				} else {
					docController.showAvailable(false);
				}
			}
			
		});
		chckUnavailable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(chckUnavailable.isSelected()) {
					docController.showUnavailable(true);
				} else {
					docController.showUnavailable(false);
				}
			}
			
		});
		btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				docController.addMonth();
				docController.updateView();
			}
			
		});
		btnPrev.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				docController.subMonth();
				docController.updateView();
			}
			
		});
		calendarTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JTable table = (JTable)e.getSource();
				
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				if(table.getValueAt(row, col) != null) {
					int day = (Integer)table.getValueAt(row, col);
					docController.setDayCurr(day);
					docController.updateView();
				}
				else {
					int[] indexes = getIndexOfDay(1);
					int day = (Integer)table.getValueAt(indexes[0], indexes[1]);
					setSelectedCell(1);
					docController.setDayCurr(day);
					docController.updateView();
				}
			}
		});
	}	
}
