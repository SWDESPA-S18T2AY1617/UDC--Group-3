package view.secretary;

import controller.SecretaryController;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.border.Border;
import java.util.Iterator;

public class PanelAgendaView extends JPanel{
	private SecretaryController vc;
	private JScrollPane scrollAgenda;
	private JTable agendaTable;
    private DefaultTableModel modelCalendarTable;
    private TableColumnModel tcm;
    private TableRendererCalenAgen masterTable;

	private int month,
    			day,
    			year;
	private Boolean displayDoc1,
    				displayDoc2,
    				displayDoc3,
    				dailyORweekly;
    private String doctor1,
    			   doctor2,
    			   doctor3;
	public PanelAgendaView(SecretaryController vc, ArrayList<String> namesofDoctors){
		this.vc = vc;
		
		this.setSize(550, 500);
		this.setLayout(null);
		this.setBackground(new Color(31, 31, 31));
		this.setBorder(BorderFactory.createTitledBorder(""));

		this.initParts(namesofDoctors);
		this.addsetParts();
	}
	public void initParts(ArrayList<String> namesofDoctors){

		doctor1 = namesofDoctors.get(0);
		doctor2 = namesofDoctors.get(1);
		doctor3 = namesofDoctors.get(2);

		modelCalendarTable = new DefaultTableModel(){
        	public boolean isCellEditable(int rowIndex, int mColIndex){
                   return false; 
            }
        };
		agendaTable = new JTable(modelCalendarTable);
		scrollAgenda = new JScrollPane(agendaTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		masterTable = new TableRendererCalenAgen();

		agendaTable.setShowGrid(true);
		agendaTable.getParent().setBackground(agendaTable.getBackground()); 
		agendaTable.getTableHeader().setResizingAllowed(false);
		agendaTable.getTableHeader().setReorderingAllowed(false);
		agendaTable.setColumnSelectionAllowed(true);
		agendaTable.setRowSelectionAllowed(true);
		agendaTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		agendaTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		agendaTable.setRowHeight(30);
		agendaTable.setFont(new Font("Charlemagne STD", Font.BOLD, 18));

		modelCalendarTable.addColumn("Agenda");
		modelCalendarTable.setColumnCount(1);
		modelCalendarTable.setRowCount(50);
		
		tcm = agendaTable.getColumnModel();
		dailyORweekly = true;

	}
	public void addsetParts(){

		this.add(scrollAgenda);
		scrollAgenda.setBounds(30, 30, 490, 460);
	}
	public void setToDaily(){

		dailyORweekly = true;
		modelCalendarTable.setColumnCount(0);
		modelCalendarTable.addColumn("Reservations for "+ month + " / " + day + " / " + year);
		modelCalendarTable.setColumnCount(1);
		modelCalendarTable.setRowCount(50);
		
        tcm.getColumn(0).setPreferredWidth(1000);

		this.setTableBlankDaily();
	}
	public void setToWeekly(){

		dailyORweekly = false;
		modelCalendarTable.setColumnCount(0);
        modelCalendarTable.addColumn("Reservations for Monday");
        modelCalendarTable.addColumn("Reservations for Tuesday");
        modelCalendarTable.addColumn("Reservations for Wednesday");
        modelCalendarTable.addColumn("Reservations for Thursday");
        modelCalendarTable.addColumn("Reservations for Friday");
		modelCalendarTable.setColumnCount(5);
		modelCalendarTable.setRowCount(50);
		
        tcm.getColumn(0).setPreferredWidth(300);
		tcm.getColumn(1).setPreferredWidth(300);
		tcm.getColumn(2).setPreferredWidth(300);
		tcm.getColumn(3).setPreferredWidth(300);
		tcm.getColumn(4).setPreferredWidth(300);

		this.setTableBlankWeekly();
	}

	public void setTableBlankDaily(){
		for(int i=0; i<50; i++)
			modelCalendarTable.setValueAt(" ", i, 0);
	}
	public void setTableBlankWeekly(){
		for(int i=0; i<50; i++){
			for(int j=0; j<5; j++){
				modelCalendarTable.setValueAt(" ", i, j);
			}
		}
	}
	public void setDate(int month, int day, int year){// true = daily ; false = weekly
		this.month = month;
		this.day = day;
		this.year = year;

		if(dailyORweekly)
			this.setToDaily();
		else
			this.setToWeekly();
	}
	public void setDoc1 (Boolean setting){
		displayDoc1 = setting;
	}
	public void setDoc2 (Boolean setting){
		displayDoc2 = setting;
	}
	public void setDoc3 (Boolean setting){
		displayDoc3 = setting;
	}

	public void updateAgendaView(Iterator<Appointment> listofappointments){

		masterTable = new TableRendererCalenAgen();
		masterTable.setCalendarorAgenda(false);


		while(listofAppointments.hasNext()){

			Appointment app = listofappointments.next();

		if(displayDoc1 && app.getDoctorName().equals(doctor1)){
			if(dailyORweekly){// true = daily ; false = weekly

				masterTable.setDailyorWeekly(true);

				if( month == app.getMonth() && day == app.getDay() && year == app.getYear()){
					agendaTable.setValueAt(app.getSHour() + ":" +
										   app.getSMin()/10 + "0 :" +
										   app.getName(), row, 0);
					if(app.isFree())
						masterTable.addGreen(row);
					else
						masterTable.addRed(row);
					row++;
				}
	
			}
			else{
				masterTable.setDailyorWeekly(false);

				for(Appointments app : listofAppointmentsDoc1){

					for(int i = 0; i<5; i++){
						if( month == app.getMonth() && day+i == app.getDay() && year == app.getYear()){
							agendaTable.setValueAt(app.getSHour() + ":" +
												   app.getSMin()/10 + "0 :" +
												   app.getName(), row, i);
							if(app.isFree()){
								masterTable.addGreen(row);
								masterTable.addGreen(i);
							}
							else{
								masterTable.addRed(row);
								masterTable.addRed(i);
							}
							row++;
						}
					}
				}

			}

		}
		if(displayDoc2 && app.getDoctorName().equals(doctor2)){
			if(dailyORweekly){// true = daily ; false = weekly

				masterTable.setDailyorWeekly(true);

				if( month == app.getMonth() && day == app.getDay() && year == app.getYear()){
					agendaTable.setValueAt(app.getSHour() + ":" +
										   app.getSMin()/10 + "0 :" +
										   app.getName(), row, 0);
					if(app.isFree())
						masterTable.addGreen(row);
					else
						masterTable.addRed(row);
					row++;
				}

			}
			else{
				masterTable.setDailyorWeekly(false);
				
				for(Appointments app : listofAppointmentsDoc1){

					for(int i = 0; i<5; i++){
						if( month == app.getMonth() && day+i == app.getDay() && year == app.getYear()){
							agendaTable.setValueAt(app.getSHour() + ":" +
												   app.getSMin()/10 + "0 :" +
												   app.getName(), row, i);
							if(app.isFree()){
								masterTable.addGreen(row);
								masterTable.addGreen(i);
							}
							else{
								masterTable.addRed(row);
								masterTable.addRed(i);
							}
							row++;
						}
					}
				}

			}
		}	
		if(displayDoc3 && app.getDoctorName().equals(doctor3)){
			if(dailyORweekly){// true = daily ; false = weekly

				masterTable.setDailyorWeekly(true);

				if( month == app.getMonth() && day == app.getDay() && year == app.getYear()){
					agendaTable.setValueAt(app.getSHour() + ":" +
										   app.getSMin()/10 + "0 :" +
										   app.getName(), row, 0);
					if(app.isFree())
						masterTable.addGreen(row);
					else
						masterTable.addRed(row);
					row++;
				}

			}
			else{
				masterTable.setDailyorWeekly(false);
				
				for(Appointments app : listofAppointmentsDoc1){

					for(int i = 0; i<5; i++){
						if( month == app.getMonth() && day+i == app.getDay() && year == app.getYear()){
							agendaTable.setValueAt(app.getSHour() + ":" +
												   app.getSMin()/10 + "0 :" +
												   app.getName(), row, i);
							if(app.isFree()){
								masterTable.addGreen(row);
								masterTable.addGreen(i);
							}
							else{
								masterTable.addRed(row);
								masterTable.addRed(i);
							}
							row++;
						}
					}
				}

			}
		}

		agendaTable.setDefaultRenderer(agendaTable.getColumnClass(0), masterTable);
	}

}


