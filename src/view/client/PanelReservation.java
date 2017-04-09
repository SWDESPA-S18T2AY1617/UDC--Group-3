package view.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter.Entry;
import javax.swing.table.DefaultTableModel;

import controller.ClientController;
import values.AppStrings;

public class PanelReservation extends JPanel {

	//private JLabel lblToDoLeft;

	private JTable agendaTable;

	//private JButton btnMarkDone;
	private JButton btnCancel;

	private AgendaTableModel modelAgendaTable;
	private JScrollPane scrollAgendaTable;


	private ClientController controller;

	public PanelReservation(ClientController controller, int year, int month, int day) {

		// Set panel properties
		this.setLayout(null);
		this.setBackground(Color.WHITE);

		// Initialize panel components
		this.initComp();
		this.controller = controller;

		// Add panel components
		this.addPlaceComp();

		// Add listeners
		//this.addListeners();
	}

	private void initComp() {
		// Button components
	//	btnMarkDone = new JButton("Mark appointment as completed...");
		btnCancel = new JButton("Cancel Appointment");

	//	btnMarkDone.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		btnCancel.setFont(new Font("Sans Serif", Font.PLAIN, 14));
	//	btnMarkDone.setContentAreaFilled(false);
		btnCancel.setContentAreaFilled(false);

		// Label component
	//	lblToDoLeft = new JLabel("Unfinished task count: 0");
	//	lblToDoLeft.setFont(new Font("Sans Serif", Font.PLAIN, 14));

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
				case 2:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};

		modelAgendaTable.setColumnCount(2);
		modelAgendaTable.setRowCount(1);

		scrollAgendaTable = new JScrollPane(agendaTable);
		scrollAgendaTable.getViewport().setBackground(Color.WHITE);
		scrollAgendaTable.setBorder(BorderFactory.createEmptyBorder());
		scrollAgendaTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		agendaTable.setTableHeader(null);
		agendaTable.setShowGrid(false);
		agendaTable.setDefaultRenderer(agendaTable.getColumnClass(0), new TableRendererAgenda());
		agendaTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		agendaTable.setRowHeight(30);
		agendaTable.getColumnModel().getColumn(0).setPreferredWidth(180);
		agendaTable.getColumnModel().getColumn(1).setPreferredWidth(455);
		agendaTable.getColumnModel().setColumnMargin(20);
		agendaTable.setFocusable(false);
		agendaTable.setRowSelectionAllowed(false);
	}

	private void addPlaceComp() {
		// Add panel components
		this.add(scrollAgendaTable);
	//	this.add(btnMarkDone);
		this.add(btnCancel);
	//	this.add(lblToDoLeft);

	//	lblToDoLeft.setBounds(15, 15, 200, 30);
		scrollAgendaTable.setBounds(15, 60, 650, 450);
	//	btnMarkDone.setBounds(240, 15, 200, 30);
		btnCancel.setBounds(450, 15, 200, 30);
	}
	public void update(int month, int day, int year) {
		this.setTableNull();
		this.setTableValues(month, day, year);
	//	this.updateLabel(year, month, day);
		this.deleteBtnDisable();
	}

	public void deleteBtnDisable() {

		boolean flag = false;
		for (int i = 0; i < agendaTable.getRowCount(); i++) {
			if ((agendaTable.getValueAt(i, 0) != null && !((String[]) agendaTable.getValueAt(i, 0))[1].contains("-")
					&& !((String[]) agendaTable.getValueAt(i, 1))[0].contains(AppStrings.NOEVENTS.toString())
					&& !((String[]) agendaTable.getValueAt(i, 1))[2].contains("lightgray"))) {
				flag = true;
			}
		}
	//	btnMarkDone.setEnabled(flag);
/*
		flag = false;
		if (btnCancel.getText().equalsIgnoreCase("Discard completed tasks")) {
			for (int i = 0; i < agendaTable.getRowCount(); i++) {
				if (((String[]) agendaTable.getValueAt(i, 1))[2].contains(Activity.COLOR_TODO_DONE)) {
					flag = true;
				}
			}
			btnCancel.setEnabled(flag);
		}
*/
	}
/*
	public void TESTupdate(int month, int day, int year, Activity activity) {
		this.setTableNull();
		this.setTableValues(month, day, year, activity);
	}
*/
	public void setTableNull() {
		for (int i = 0; i < agendaTable.getRowCount(); i++) {
			for (int j = 0; j < agendaTable.getColumnCount(); j++) {
				agendaTable.setValueAt(null, i, j);
			}
		}
	}

	public void setAgendaColumn(int n) {
		
		if (n == 2) {
			modelAgendaTable.setColumnCount(2);
			agendaTable.getColumnModel().getColumn(0).setPreferredWidth(180);
			agendaTable.getColumnModel().getColumn(1).setPreferredWidth(455);
		} else if (n == 3) {
			modelAgendaTable.setColumnCount(3);
			agendaTable.getColumnModel().getColumn(0).setPreferredWidth(180);
			agendaTable.getColumnModel().getColumn(1).setPreferredWidth(400);
			agendaTable.getColumnModel().getColumn(2).setPreferredWidth(55);
		}
	}
	public void setTableValues(int month, int day, int year) {
		System.out.println("Setting table values");
		// ArrayList<Activity> activityList = new ArrayList<>();
		//activityList = new ArrayList<>();
		int rwCnt = 0;

		modelAgendaTable.setRowCount(1);
		modelAgendaTable.setValueAt(new String[] { AppStrings.NOEVENTS.toString(), "", "black", "" }, 0, 0);
		modelAgendaTable.setValueAt(new String[] { AppStrings.NOEVENTS.toString(), "", "black", "" }, 0, 1);
/*
		if (activity == null) {
			System.out.println("No activities.");
		} else {
			// Iterator -> ArrayList
			while (activity != null && activity.hasNext()) {
				activityList.add(activity.next());
			}
			if (activityList.size() > 0)
				modelAgendaTable.setRowCount(activityList.size());
			for (int i = 0; i < activityList.size(); i++) {
				System.out.println("searching for act");
				if (activityList.get(i).isMonth(month) && activityList.get(i).isDay(day)
						&& activityList.get(i).isYear(year)) {
					System.out.println("add activity to agenda");
					String[] sArr = activityList.get(i).toStringArr();

					modelAgendaTable.setValueAt(sArr, rwCnt, 0);
					modelAgendaTable.setValueAt(sArr, rwCnt, 1);

					rwCnt++;
				}
			}
		}
		revalidate();
		repaint();
		*/
	}
/*
	public void setTableValues(int month, int day, int year, Activity activity) {
		// FOR TESTING
		int rwCnt = 0;

		if (activity == null) {
			System.out.println("No activities.");
		} else {
			if (activity.isMonth(month) && activity.isDay(day) && activity.isYear(year)) {
				String[] sArr = activity.toStringArr();

				for (int i = 0; i < sArr.length; i++) {
					System.out.println(sArr[i]);
				}

				modelAgendaTable.setValueAt(sArr, rwCnt, 0);
				modelAgendaTable.setValueAt(sArr, rwCnt, 1);

				rwCnt++;
			}
		}

		revalidate();
		repaint();
	}
*/
/*	private void addListeners() {
	//	btnMarkDone.addActionListener(new MarkDone());
		btnCancel.addActionListener(new DeleteAppointment());
	}
	

	class DeleteAppointment implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (btnCancel.getText().equalsIgnoreCase("Cancel")) {
				btnCancel.setText("Cancel Appointment");

				setAgendaColumn(2);
			} else if (btnCancel.getText().equalsIgnoreCase("Cancel Appointment")) {

				setAgendaColumn(3);

				for (int i = agendaTable.getRowCount() - 1; i >= 0; i--) {
					if (agendaTable.getValueAt(i, 1) != null
							&& ((String[]) agendaTable.getValueAt(i, 1))[2].contains(Activity.COLOR_TODO_DONE)) {
						String[] sArr = (String[]) agendaTable.getValueAt(i, 0);
						controller.deleteActivity(Integer.parseInt(sArr[3]));
					}
				}
				setAgendaColumn(2);
			}
			controller.updateView();
		}

	}
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