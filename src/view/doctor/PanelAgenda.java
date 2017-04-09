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
import javax.swing.RowFilter.Entry;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.DoctorController;
import model.CalendarCalculator;
import values.AppStrings;
import values.Month;

public class PanelAgenda extends JPanel {

	// private JLabel lblToDoLeft;

	private JTable agendaTable;

	// private JButton btnMarkDone;
	// private JButton btnDeleteDone;

	private TableModel modelAgendaTable;
	private JScrollPane scrollAgendaTable;

	private Calendar[] calWeekScope;
	private List<TestAppointment> listTA;
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
		listTA = new ArrayList<TestAppointment>();

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
		modelAgendaTable = new AgendaTableModel(100, 3);

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

		((DefaultTableModel) modelAgendaTable).setColumnCount(3);
		((DefaultTableModel) modelAgendaTable).setRowCount(1);

		scrollAgendaTable = new JScrollPane(agendaTable);
		scrollAgendaTable.getViewport().setBackground(Color.WHITE);
		scrollAgendaTable.setBorder(BorderFactory.createEmptyBorder());
		scrollAgendaTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		agendaTable.setTableHeader(null);
		agendaTable.setShowGrid(false);
		agendaTable.setDefaultRenderer(agendaTable.getColumnClass(0), new TableRendererAgenda());
		agendaTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		agendaTable.setRowHeight(30);
		agendaTable.getColumnModel().getColumn(0).setPreferredWidth(125);
		agendaTable.getColumnModel().getColumn(1).setPreferredWidth(125);
		agendaTable.getColumnModel().getColumn(2).setPreferredWidth(385);
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

	public void setWeekAppointments(Iterator<TestAppointment> ta) {
		listTA.removeAll(listTA);

		while (ta != null && ta.hasNext()) {
			listTA.add(ta.next());
		}
	}

	public void update(int year, int month, int day, Iterator<TestAppointment> appointments) {

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
	
	public void updateWeek(int year, int month, int day, Iterator<TestAppointment> appointments) {
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

		for (TestAppointment t : listTA) {
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

				rwCnt++;
			}
		}

		revalidate();
		repaint();
	}

	public void setTableValuesWeek(int year, int month, int day) {

		int rwCnt = 0;

		((DefaultTableModel) modelAgendaTable).setRowCount(1);

		for (TestAppointment t : listTA) {
			System.out.println("searching for act " + month + " " + day + " " + year);
			System.out.println(t.getName());
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

	private boolean isToday(TestAppointment t, int year, int month, int day) {
		return t.isMonth(month) && t.isDay(day) && t.isYear(year);
	}

	private boolean isThisWeek(TestAppointment t) {
		System.out.println("PUTANGINAAAAAAAAAA");
		for (int i = 0; i < calWeekScope.length; i++) {
			Calendar c = calWeekScope[i];
			if (t.isMonth(c.get(Calendar.MONTH)) && t.isDay(c.get(Calendar.DAY_OF_MONTH))
					&& t.isYear(c.get(Calendar.YEAR))) {
				System.out.println("PASOK BES");
				return true;
			}
		}
		return false;
	}

	/*
	 * public void update(int month, int day, int year, Iterator<Activity>
	 * activity) { this.setTableNull(); this.setTableValues(month, day, year,
	 * activity); this.updateLabel(year, month, day); this.deleteBtnDisable(); }
	 * 
	 * public void updateLabel(int year, int month, int day) {
	 * lblToDoLeft.setText("Unfinished task count: " +
	 * controller.getToDoLeft(year, month, day)); }
	 *//*
		 * public void deleteBtnDisable() {
		 * 
		 * boolean flag = false; for (int i = 0; i < agendaTable.getRowCount();
		 * i++) { if ((agendaTable.getValueAt(i, 0) != null && !((String[])
		 * agendaTable.getValueAt(i, 0))[1].contains("-") && !((String[])
		 * agendaTable.getValueAt(i,
		 * 1))[0].contains(AppStrings.NOEVENTS.toString()) && !((String[])
		 * agendaTable.getValueAt(i, 1))[2].contains("lightgray"))) { flag =
		 * true; } } btnMarkDone.setEnabled(flag);
		 * 
		 * flag = false; if
		 * (btnDeleteDone.getText().equalsIgnoreCase("Discard completed tasks"))
		 * { for (int i = 0; i < agendaTable.getRowCount(); i++) { if
		 * (((String[]) agendaTable.getValueAt(i,
		 * 1))[2].contains(Activity.COLOR_TODO_DONE)) { flag = true; } }
		 * btnDeleteDone.setEnabled(flag); }
		 * 
		 * }
		 * 
		 * public void TESTupdate(int month, int day, int year, Activity
		 * activity) { this.setTableNull(); this.setTableValues(month, day,
		 * year, activity); }
		 * 
		 * public void setTableNull() { for (int i = 0; i <
		 * agendaTable.getRowCount(); i++) { for (int j = 0; j <
		 * agendaTable.getColumnCount(); j++) { agendaTable.setValueAt(null, i,
		 * j); } } }
		 * 
		 * public void setAgendaColumn(int n) { if (n == 2) {
		 * modelAgendaTable.setColumnCount(2);
		 * agendaTable.getColumnModel().getColumn(0).setPreferredWidth(180);
		 * agendaTable.getColumnModel().getColumn(1).setPreferredWidth(455); }
		 * else if (n == 3) { modelAgendaTable.setColumnCount(3);
		 * agendaTable.getColumnModel().getColumn(0).setPreferredWidth(180);
		 * agendaTable.getColumnModel().getColumn(1).setPreferredWidth(400);
		 * agendaTable.getColumnModel().getColumn(2).setPreferredWidth(55); } }
		 * 
		 * public void setTableValues(int month, int day, int year,
		 * Iterator<Activity> activity) {
		 * System.out.println("Setting table values"); // ArrayList<Activity>
		 * listAppointment = new ArrayList<>(); listAppointment = new
		 * ArrayList<>(); int rwCnt = 0;
		 * 
		 * modelAgendaTable.setRowCount(1); modelAgendaTable.setValueAt(new
		 * String[] { AppStrings.NOEVENTS.toString(), "", "black", "" }, 0, 0);
		 * modelAgendaTable.setValueAt(new String[] {
		 * AppStrings.NOEVENTS.toString(), "", "black", "" }, 0, 1);
		 * 
		 * if (activity == null) { System.out.println("No activities."); } else
		 * { // Iterator -> ArrayList while (activity != null &&
		 * activity.hasNext()) { listAppointment.add(activity.next()); } if
		 * (listAppointment.size() > 0)
		 * modelAgendaTable.setRowCount(listAppointment.size()); for (int i = 0;
		 * i < listAppointment.size(); i++) {
		 * System.out.println("searching for act"); if
		 * (listAppointment.get(i).isMonth(month) &&
		 * listAppointment.get(i).isDay(day) &&
		 * listAppointment.get(i).isYear(year)) {
		 * System.out.println("add activity to agenda"); String[] sArr =
		 * listAppointment.get(i).toStringArr();
		 * 
		 * modelAgendaTable.setValueAt(sArr, rwCnt, 0);
		 * modelAgendaTable.setValueAt(sArr, rwCnt, 1);
		 * 
		 * rwCnt++; } } } revalidate(); repaint(); }
		 * 
		 * public void setTableValues(int month, int day, int year, Activity
		 * activity) { // FOR TESTING int rwCnt = 0;
		 * 
		 * if (activity == null) { System.out.println("No activities."); } else
		 * { if (activity.isMonth(month) && activity.isDay(day) &&
		 * activity.isYear(year)) { String[] sArr = activity.toStringArr();
		 * 
		 * for (int i = 0; i < sArr.length; i++) { System.out.println(sArr[i]);
		 * }
		 * 
		 * modelAgendaTable.setValueAt(sArr, rwCnt, 0);
		 * modelAgendaTable.setValueAt(sArr, rwCnt, 1);
		 * 
		 * rwCnt++; } }
		 * 
		 * revalidate(); repaint(); }
		 * 
		 * private void addListeners() { btnMarkDone.addActionListener(new
		 * MarkDone()); btnDeleteDone.addActionListener(new DeleteDone()); }
		 * 
		 * class MarkDone implements ActionListener {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { if
		 * (btnMarkDone.getText().equalsIgnoreCase("Mark tasks as completed..."
		 * )) { btnMarkDone.setText("Mark as completed");
		 * btnDeleteDone.setText("Cancel");
		 * 
		 * btnMarkDone.setEnabled(true); btnDeleteDone.setEnabled(true);
		 * 
		 * setAgendaColumn(3);
		 * 
		 * for (int i = 0; i < agendaTable.getRowCount(); i++) { if
		 * (agendaTable.getValueAt(i, 0) != null && !((String[])
		 * agendaTable.getValueAt(i, 0))[1].contains("-") && ((String[])
		 * agendaTable.getValueAt(i, 0))[1].contains(":") && !((String[])
		 * agendaTable.getValueAt(i, 1))[2].contains("lightgray")) {
		 * ((AgendaTableModel) agendaTable.getModel()).setCellEditable(i, 2,
		 * true);
		 * 
		 * String[] sArr = (String[]) agendaTable.getValueAt(i, 0); for (int j =
		 * 0; j < sArr.length; j++) { System.out.println("MARK " + sArr[j]); }
		 * 
		 * } else { ((AgendaTableModel)
		 * agendaTable.getModel()).setCellEditable(i, 2, false); } } } else if
		 * (btnMarkDone.getText().equalsIgnoreCase("Mark as completed")) {
		 * btnMarkDone.setText("Mark tasks as completed...");
		 * btnDeleteDone.setText("Discard completed tasks");
		 * 
		 * System.out.println("RWCNT: " + agendaTable.getRowCount());
		 * 
		 * for (int i = 0; i < agendaTable.getRowCount(); i++) { if
		 * (agendaTable.getValueAt(i, 2) != null && (boolean)
		 * agendaTable.getValueAt(i, 2)) { String[] sArr = (String[])
		 * agendaTable.getValueAt(i, 0);
		 * 
		 * for (int j = 0; j < sArr.length; j++) {
		 * System.out.println("COMPLETE " + sArr[j]); }
		 * controller.markDone(Integer.parseInt(sArr[3])); } }
		 * controller.updateView(); setAgendaColumn(2); }
		 * 
		 * }
		 * 
		 * }
		 * 
		 * class DeleteDone implements ActionListener {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { if
		 * (btnDeleteDone.getText().equalsIgnoreCase("Cancel")) {
		 * btnMarkDone.setText("Mark tasks as completed...");
		 * btnDeleteDone.setText("Discard completed tasks");
		 * 
		 * setAgendaColumn(2); } else if
		 * (btnDeleteDone.getText().equalsIgnoreCase("Discard completed tasks"))
		 * {
		 * 
		 * setAgendaColumn(3);
		 * 
		 * for (int i = agendaTable.getRowCount() - 1; i >= 0; i--) { if
		 * (agendaTable.getValueAt(i, 1) != null && ((String[])
		 * agendaTable.getValueAt(i, 1))[2].contains(Activity.COLOR_TODO_DONE))
		 * { String[] sArr = (String[]) agendaTable.getValueAt(i, 0);
		 * controller.deleteActivity(Integer.parseInt(sArr[3])); } }
		 * setAgendaColumn(2); } controller.updateView(); }
		 * 
		 * }
		 */

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