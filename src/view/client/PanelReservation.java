package view.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter.Entry;
import javax.swing.table.DefaultTableModel;

import controller.ClientController;
import model.Appointment;
import values.AppStrings;

public class PanelReservation extends JPanel {

	//private JLabel lblToDoLeft;

	private JTable agendaTable;

	//private JButton btnMarkDone;
	private JButton btnCancel;
	private JList appointments;

	private AgendaTableModel modelAgendaTable;
	private JScrollPane scrollAgendaTable;


	private ArrayList<Appointment> activityList;

	private ClientController controller;

	public PanelReservation(ClientController controller, int year, int month, int day) {

		// Set panel properties
		this.setLayout(null);
		this.setBackground(Color.WHITE);

		// Initialize panel components
		this.initComp();

		// Add panel components
		this.addPlaceComp();

		// Add listeners
		this.addListeners();
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
	public void update(int month, int day, int year,Iterator<Appointment> activity) {
		this.setTableNull();
		this.setTableValues(activity);
		this.deleteBtnDisable();
	}

	public void deleteBtnDisable() {

		boolean flag = false;
		
		if (btnCancel.getText().equalsIgnoreCase("Cancel Appointment")) {
			for (int i = 0; i < agendaTable.getRowCount(); i++) {
					flag = true;
				}
			}
		
			btnCancel.setEnabled(flag);
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
	
	//displays all reservation/appointment
	public void setTableValues(Iterator<Appointment> activity) {
		System.out.println("Setting table values");
		
		activityList = new ArrayList<>();
		
		int rwCnt = 0;

		modelAgendaTable.setRowCount(1);
		modelAgendaTable.setValueAt(new String[] { AppStrings.NOEVENTS.toString(), "", "black", "" }, 0, 0);
		modelAgendaTable.setValueAt(new String[] { AppStrings.NOEVENTS.toString(), "", "black", "" }, 0, 1);
		
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
				DecimalFormat d = new DecimalFormat("00");

				String date = (activityList.get(i).getMonth() + 1) + "/" + (activityList.get(i).getDay()) + "/" + activityList.get(i).getYear();
				String time = activityList.get(i).getStartHour() + ":" + d.format(activityList.get(i).getStartMinute()) + " - " + activityList.get(i).getEndHour() + ":"
						+ d.format(activityList.get(i).getEndMinute());

				modelAgendaTable.setValueAt(date, rwCnt, 0);
				modelAgendaTable.setValueAt(time, rwCnt, 1);
				modelAgendaTable.setValueAt(activityList.get(i), rwCnt, 2);

				rwCnt++;
				}
			}
		
		revalidate();
		repaint();
	}
	private void addListeners() {
		
		btnCancel.addActionListener(new CancelAppointment());
	}
	

	class CancelAppointment implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (btnCancel.getText().equalsIgnoreCase("Cancel")) {
				btnCancel.setText("Cancel Appointment");

				setAgendaColumn(2);
			} else if (btnCancel.getText().equalsIgnoreCase("Cancel Appointment")) {

				setAgendaColumn(3);

				for (int i = agendaTable.getRowCount() - 1; i >= 0; i--) {
					if (agendaTable.getValueAt(i, 1) != null) {
						String[] sArr = (String[]) agendaTable.getValueAt(i, 0);
						controller.cancelAppointment(/*Integer.parseInt(sArr[3])*/);
					}
				}
				setAgendaColumn(2);
			}
			controller.updateView();
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
}
