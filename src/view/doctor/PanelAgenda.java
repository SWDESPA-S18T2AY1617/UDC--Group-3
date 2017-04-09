package view.doctor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowFilter.Entry;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.DoctorController;
import model.Appointment;
import model.CalendarCalculator;
import model.Client;
import values.AppStrings;
import values.Month;

public class PanelAgenda extends JPanel {

	// private JLabel lblToDoLeft;

	private JTable agendaTable;
	private TableRowSorter<TableModel> filter;
	// private JButton btnMarkDone;
	// private JButton btnDeleteDone;

	private TableModel modelAgendaTable;
	private JScrollPane scrollAgendaTable;

	private Calendar[] calWeekScope;
	private List<Appointment> listTA;
	// private ArrayList<Activity> listAppointment;

	private DoctorController docController;

	public PanelAgenda(DoctorController docController, int year, int month, int day) {
		// Set controller
		this.docController = docController;
	
		// Set panel properties
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(670, 830));

		// Initialize components
		this.initComp();
		this.setWeekScope(year, month, day);
		// this.controller = controller;

		// Add panel components
		this.addPlaceComp();

		// Add listeners
		// this.addListeners();
	}

	private void initComp() {
		// Calendar scope
		calWeekScope = new GregorianCalendar[5];
		listTA = new ArrayList<Appointment>();

		// Button components
		// btnMarkDone = new JButton("Mark tasks as completed...");
		// btnDeleteDone = new JButton("Discard completed tasks");

		// btnMarkDone.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		// btnDeleteDone.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		// btnMarkDone.setContentAreaFilled(false);
		// btnDeleteDone.setContentAreaFilled(false);

		// Label component
		// lblToDoLeft = new JLabel("Unfinished task count: 0");
		// lblToDoLeft.setFont(new Font("Sans Serif", Font.PLAIN, 14));

		// Table components
		modelAgendaTable = new AgendaTableModel(100, 4);
		filter = new TableRowSorter<TableModel>(modelAgendaTable);
		agendaTable = new JTable(modelAgendaTable) {
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				default:
					return String.class;
				}
			}
		};

		((DefaultTableModel) modelAgendaTable).setColumnCount(4);
		((DefaultTableModel) modelAgendaTable).setRowCount(1);

		scrollAgendaTable = new JScrollPane(agendaTable);
		scrollAgendaTable.getViewport().setBackground(Color.WHITE);
		scrollAgendaTable.setBorder(BorderFactory.createEmptyBorder());
		scrollAgendaTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		agendaTable.setTableHeader(null);
		agendaTable.setRowSorter(filter);
		agendaTable.setShowGrid(false);
		agendaTable.setDefaultRenderer(agendaTable.getColumnClass(0), new TableRendererAgenda());
		agendaTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		agendaTable.setRowHeight(30);
		agendaTable.getColumnModel().getColumn(0).setPreferredWidth(125);
		agendaTable.getColumnModel().getColumn(1).setPreferredWidth(125);
		agendaTable.getColumnModel().getColumn(2).setPreferredWidth(162);
		agendaTable.getColumnModel().getColumn(3).setPreferredWidth(162);
		agendaTable.getColumnModel().setColumnMargin(20);
		agendaTable.setFocusable(false);
		agendaTable.setRowSelectionAllowed(false);
	}

	private void addPlaceComp() {
		// Add panel components
		this.add(scrollAgendaTable);
		// this.add(btnMarkDone);
		// this.add(btnDeleteDone);
		// this.add(lblToDoLeft);

		// lblToDoLeft.setBounds(15, 15, 200, 30);
		scrollAgendaTable.setBounds(15, 60, 650, 450);
		// btnMarkDone.setBounds(240, 15, 200, 30);
		// btnDeleteDone.setBounds(450, 15, 200, 30);
	}

	private void setWeekScope(int year, int month, int day) {
		int monday = CalendarCalculator.getFirstMondayOfWeek(year, month, day);

		int currMonth = month;
		int maxMonth = CalendarCalculator.getNoDays(year, currMonth);
		for (int i = 1; i < calWeekScope.length + 1; i++) {
			if (monday > day && CalendarCalculator.getDayOfWeek(year, month, day) != Calendar.SUNDAY
					&& month == currMonth) {
				currMonth = CalendarCalculator.getPrevMonth(currMonth);
				if (Month.values()[currMonth] == Month.DECEMBER) {
					year--;
				}
				maxMonth = CalendarCalculator.getNoDays(year, currMonth);
			}
			if (monday + i - 1 > maxMonth || (day == maxMonth && month == currMonth
					&& CalendarCalculator.getDayOfWeek(year, month, day) == Calendar.SUNDAY)) {
				currMonth = CalendarCalculator.getNextMonth(currMonth);
				if (Month.values()[currMonth] == Month.JANUARY) {
					year++;
				}
				maxMonth = CalendarCalculator.getNoDays(year, currMonth);
				monday = (i * -1) + 2;
			}
			calWeekScope[i - 1] = new GregorianCalendar(year, currMonth, (monday + i - 1));
		}
	}

	public void showAvailable(boolean show) {
		if(!show) {
			RowFilter<TableModel, Object> rf = null;
			
			rf = RowFilter.regexFilter("(patient)", 0);
			
			filter.setRowFilter(rf);
		} else {
			filter.setRowFilter(null);
		}
	}
	
	public void setWeekAppointments(Iterator<Appointment> ta) {
		listTA.removeAll(listTA);

		while (ta != null && ta.hasNext()) {
			listTA.add(ta.next());
		}
	}

	public void update(int year, int month, int day, Iterator<Appointment> appointments) {

		this.setWeekScope(year, month, day);
		this.setWeekAppointments(appointments);
		this.setTableNull();
		this.setTableValues(year, month, day);
	}

	public void update(int year, int month, int day) {
		this.setWeekScope(year, month, day);
		this.setTableNull();
		this.setTableValues(year, month, day);
	}
	
	public void updateWeek(int year, int month, int day, Iterator<Appointment> appointments) {
		this.setWeekScope(year, month, day);
		this.setWeekAppointments(appointments);
		this.setTableNull();
		this.setTableValuesWeek(year, month, day);
	}

	public void updateWeek(int year, int month, int day) {
		this.setWeekScope(year, month, day);
		this.setTableNull();
		this.setTableValuesWeek(year, month, day);
	}
	
	public void setTableNull() {
		for (int i = 0; i < agendaTable.getRowCount(); i++) {
			for (int j = 0; j < agendaTable.getColumnCount(); j++) {
				agendaTable.setValueAt(null, i, j);
			}
		}
	}

	public void setTableValues(int year, int month, int day) {
		int rwCnt = 0;

		((DefaultTableModel) modelAgendaTable).setRowCount(1);

		for (Appointment t : listTA) {
			System.out.println("searching for act " + month + " " + day + " " + year);

			if (isToday(t, year, month, day)) {
				System.out.println("found");
				((DefaultTableModel) modelAgendaTable).setRowCount((modelAgendaTable.getRowCount()) + 1);
				DecimalFormat d = new DecimalFormat("00");

				String date = (t.getMonth() + 1) + "/" + (t.getDay()) + "/" + t.getYear();
				String time = t.getStartHour() + ":" + d.format(t.getStartMinute()) + " - " + t.getEndHour() + ":"
						+ d.format(t.getEndMinute());

				modelAgendaTable.setValueAt(date, rwCnt, 0);
				modelAgendaTable.setValueAt(time, rwCnt, 1);
				modelAgendaTable.setValueAt(t, rwCnt, 2);
				if(!t.isAvailable()) {
					modelAgendaTable.setValueAt(t, rwCnt, 3);
				}
					
				
				rwCnt++;
			}
		}

		revalidate();
		repaint();
	}

	public void setTableValuesWeek(int year, int month, int day) {

		int rwCnt = 0;

		((DefaultTableModel) modelAgendaTable).setRowCount(1);

		for (Appointment t : listTA) {
			System.out.println("searching for act " + month + " " + day + " " + year);
			System.out.println(t.toString());
			if (isThisWeek(t)) {
				System.out.println("found");
				((DefaultTableModel) modelAgendaTable).setRowCount((modelAgendaTable.getRowCount()) + 1);
				DecimalFormat d = new DecimalFormat("00");

				String date = (t.getMonth() + 1) + "/" + (t.getDay()) + "/" + t.getYear();
				String time = t.getStartHour() + ":" + d.format(t.getStartMinute()) + " - " + t.getEndHour() + ":"
						+ d.format(t.getEndMinute());

				modelAgendaTable.setValueAt(date, rwCnt, 0);
				modelAgendaTable.setValueAt(time, rwCnt, 1);
				modelAgendaTable.setValueAt(t, rwCnt, 2);

				rwCnt++;
			}
		}

		revalidate();
		repaint();
	}

	private boolean isToday(Appointment t, int year, int month, int day) {
		return t.isMonth(month) && t.isDay(day) && t.isYear(year);
	}

	private boolean isThisWeek(Appointment t) {
		//System.out.println("PUTANGINAAAAAAAAAA");
		for (int i = 0; i < calWeekScope.length; i++) {
			Calendar c = calWeekScope[i];
			if (t.isMonth(c.get(Calendar.MONTH)) && t.isDay(c.get(Calendar.DAY_OF_MONTH))
					&& t.isYear(c.get(Calendar.YEAR))) {
				//System.out.println("PASOK BES");
				return true;
			}
		}
		return false;
	}
}

class AgendaTableModel extends DefaultTableModel {
	private boolean[][] editable_cells;

	AgendaTableModel(int row, int cols) {
		super(row, cols);
		this.editable_cells = new boolean[row][cols];
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return this.editable_cells[row][column];
	}

	public void setCellEditable(int row, int col, boolean value) {
		this.editable_cells[row][col] = value;
		this.fireTableCellUpdated(row, col);
	}
}