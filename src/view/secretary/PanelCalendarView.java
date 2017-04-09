package view.secretary;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.border.Border;

public class PanelCalendarView extends JPanel{

	private ViewController vc;

	private JScrollPane scrollCalendar;
	private JTable calendarTable;
    private DefaultTableModel modelCalendarTable;
    private TableColumnModel tcm;
    private TableRenderer masterTable;

    private int month,
    			day,
    			year;
    private Boolean displayDoc1,
    				displayDoc2,
    				displayDoc3,
    				dailyORweekly;
	public PanelCalendarView(ViewController vc){

		this.vc = vc;

		this.setSize(550, 500);
		this.setLayout(null);
		this.setBackground(new Color(31, 31, 31));
		this.setBorder(BorderFactory.createTitledBorder(""));

		this.initParts();
		this.addsetParts();
		
	}
	public void initParts(){

		modelCalendarTable = new DefaultTableModel(){
        	public boolean isCellEditable(int rowIndex, int mColIndex){
                   return false; 
            }
        };
		calendarTable = new JTable(modelCalendarTable);
		scrollCalendar = new JScrollPane(calendarTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		masterTable = new TableRenderer();
		
		calendarTable.setShowGrid(true);
		calendarTable.getParent().setBackground(calendarTable.getBackground()); 
		calendarTable.getTableHeader().setResizingAllowed(false);
		calendarTable.getTableHeader().setReorderingAllowed(false);
		calendarTable.setColumnSelectionAllowed(true);
		calendarTable.setRowSelectionAllowed(true);
		calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		calendarTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		calendarTable.setRowHeight(30);
		calendarTable.setFont(new Font("Charlemagne STD", Font.BOLD, 18));
		
		tcm = calendarTable.getColumnModel();
		dailyORweekly = true;

	}
	public void addsetParts(){

		this.add(scrollCalendar);
		scrollCalendar.setBounds(30, 30, 490, 460);

	}
	public void setToDaily(){

		dailyORweekly = true;
		modelCalendarTable.setColumnCount(0);
		modelCalendarTable.addColumn("Hr");
		modelCalendarTable.addColumn("Min");
        modelCalendarTable.addColumn("Reservations for, "+ month + " / " + day + " / " + year);
		modelCalendarTable.setColumnCount(3);
		modelCalendarTable.setRowCount(48);
		
        tcm.getColumn(0).setPreferredWidth(25);
		tcm.getColumn(1).setPreferredWidth(25);
		tcm.getColumn(2).setPreferredWidth(1000);

		calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), masterTable);
		this.setTableBlankDaily();
	}
	public void setToWeekly(){

		dailyORweekly = false;
		modelCalendarTable.setColumnCount(0);
		modelCalendarTable.addColumn("Hr");
		modelCalendarTable.addColumn("Min");
        modelCalendarTable.addColumn("Reservations for Monday, "+ month + " / " + day + " / " + year);
        modelCalendarTable.addColumn("Reservations for Tuesday, "+ month + " / " + (day+1) + " / " + year);
        modelCalendarTable.addColumn("Reservations for Wednesday, "+ month + " / " + (day+2) +" / " + year);
        modelCalendarTable.addColumn("Reservations for Thursday, "+ month + " / " + (day+3) + " / " + year);
        modelCalendarTable.addColumn("Reservations for Friday, "+ month + " / " + (day+4) + " / " + year);
		modelCalendarTable.setColumnCount(7);
		modelCalendarTable.setRowCount(48);
		
        tcm.getColumn(0).setPreferredWidth(25);
		tcm.getColumn(1).setPreferredWidth(25);
		tcm.getColumn(2).setPreferredWidth(300);
		tcm.getColumn(3).setPreferredWidth(300);
		tcm.getColumn(4).setPreferredWidth(300);
		tcm.getColumn(5).setPreferredWidth(300);
		tcm.getColumn(6).setPreferredWidth(300);

		calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), masterTable);
		this.setTableBlankWeekly();
	}
	public void setTableBlankDaily(){
		int i =0;
		int row = 0;
		while(row<48){
			modelCalendarTable.setValueAt(i, row, 0);
			if(row%2==0){
				modelCalendarTable.setValueAt("00", row, 1);
			}
			else {
				modelCalendarTable.setValueAt("30", row, 1);
				i++;
			}
			
			row++;
		}
	}
	public void setTableBlankWeekly(){
		int i =0;
		int row = 0;
		while(row<48){
			modelCalendarTable.setValueAt(i, row, 0);
			if(row%2==0){
				modelCalendarTable.setValueAt("00", row, 1);
			}
			else {
				modelCalendarTable.setValueAt("30", row, 1);
				i++;
			}
			
			row++;
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
	/*
	public void updateCalendarView(/*listofAppointmentsDoc1,listofAppointmentsDoc2, listofAppointmentsDoc3 ){

		if(displayDoc1){
			if(dailyORweekly){// true = daily ; false = weekly
				for(int i=0 ; i<48; i++){
					int ctr = 0;
					Object cellHour = dayTable.getValueAt(i, 0);
					Object cellMinute = dayTable.getValueAt(i, 1);

					cellHour = Integer.parseInt((String) cellHour); 
					cellMinute = Integer.parseInt((String) cellMinute); 

					for(/*Appointment temp : /*listofAppointmentsDoc1){
						if( givenMonth == temp.getMonth() &&
							givenDay == temp.getDay() &&
							givenYear == temp.getYear() &&
							cellHour ==  temp.getSHour() &&
							cellMinute == temp.getSMin()){
							dayTable.setValueAt(temp.getName(), i, 2);
							hasTaskorEvent = true;
							ctr = temp.getEHour() - temp.getSHour();

							if(temp.getSMin() - temp.getEMin() == 0 ){
								ctr = i + (ctr*2)-1;
							}
							else if(temp.getSMin() - temp.getEMin() == -30  ){
								ctr = i + (ctr*2);
							}
							else if (temp.getSMin() - temp.getEMin() == 30  ){
								ctr = i + (ctr*2)-2;
							}
							
						}
					}
				}
			}
			else{
				for(int i=0 ; i<48; i++){
					int ctr = 0;
					Object cellHour = dayTable.getValueAt(i, 0);
					Object cellMinute = dayTable.getValueAt(i, 1);

					cellHour = Integer.parseInt((String) cellHour); 
					cellMinute = Integer.parseInt((String) cellMinute); 

					for(int j=0; j<5;j++){
						for(/*Appointment temp : /*listofAppointmentsDoc1){
							if( month == temp.getMonth() && day == temp.getDay()+j &&
								year == temp.getYear() &&
								cellHour ==  temp.getSHour() && cellMinute == temp.getSMin()){

								dayTable.setValueAt(temp.getName(), i, 2+j);
								hasTaskorEvent = true;
								ctr = temp.getEHour() - temp.getSHour();

								if(temp.getSMin() - temp.getEMin() == 0 ){
									ctr = i + (ctr*2)-1;
								}
								else if(temp.getSMin() - temp.getEMin() == -30  ){
									ctr = i + (ctr*2);
								}
								else if (temp.getSMin() - temp.getEMin() == 30  ){
									ctr = i + (ctr*2)-2;
								}
									
								//ctr to be used for the color stuff

							}
						}
					}
				}
			}
		}
			

		if(displayDoc2){
			if(dailyORweekly){// true = daily ; false = weekly
				for(int i=0 ; i<48; i++){
					int ctr = 0;
					Object cellHour = dayTable.getValueAt(i, 0);
					Object cellMinute = dayTable.getValueAt(i, 1);

					cellHour = Integer.parseInt((String) cellHour); 
					cellMinute = Integer.parseInt((String) cellMinute); 

					for(/*Appointment temp : /*listofAppointmentsDoc2){
						if( givenMonth == temp.getMonth() &&
							givenDay == temp.getDay() &&
							givenYear == temp.getYear() &&
							cellHour ==  temp.getSHour() &&
							cellMinute == temp.getSMin()){
							dayTable.setValueAt(temp.getName(), i, 2);
							hasTaskorEvent = true;
							ctr = temp.getEHour() - temp.getSHour();

							if(temp.getSMin() - temp.getEMin() == 0 ){
								ctr = i + (ctr*2)-1;
							}
							else if(temp.getSMin() - temp.getEMin() == -30  ){
								ctr = i + (ctr*2);
							}
							else if (temp.getSMin() - temp.getEMin() == 30  ){
								ctr = i + (ctr*2)-2;
							}
							
						}
					}
				}
			}
			else{
				for(int i=0 ; i<48; i++){
					int ctr = 0;
					Object cellHour = dayTable.getValueAt(i, 0);
					Object cellMinute = dayTable.getValueAt(i, 1);

					cellHour = Integer.parseInt((String) cellHour); 
					cellMinute = Integer.parseInt((String) cellMinute); 

					for(int j=0; j<5;j++){
						for(/*Appointment temp : /*listofAppointmentsDoc2){
							if( month == temp.getMonth() && day == temp.getDay()+j &&
								year == temp.getYear() &&
								cellHour ==  temp.getSHour() && cellMinute == temp.getSMin()){

								dayTable.setValueAt(temp.getName(), i, 2+j);
								hasTaskorEvent = true;
								ctr = temp.getEHour() - temp.getSHour();

								if(temp.getSMin() - temp.getEMin() == 0 ){
									ctr = i + (ctr*2)-1;
								}
								else if(temp.getSMin() - temp.getEMin() == -30  ){
									ctr = i + (ctr*2);
								}
								else if (temp.getSMin() - temp.getEMin() == 30  ){
									ctr = i + (ctr*2)-2;
								}
									
								//ctr to be used for the color stuff

							}
						}
					}
				}
			}
		}
		if(displayDoc3){
			if(dailyORweekly){// true = daily ; false = weekly
				for(int i=0 ; i<48; i++){
					int ctr = 0;
					Object cellHour = dayTable.getValueAt(i, 0);
					Object cellMinute = dayTable.getValueAt(i, 1);

					cellHour = Integer.parseInt((String) cellHour); 
					cellMinute = Integer.parseInt((String) cellMinute); 

					for(/*Appointment temp : /*listofAppointmentsDoc3){
						if( givenMonth == temp.getMonth() &&
							givenDay == temp.getDay() &&
							givenYear == temp.getYear() &&
							cellHour ==  temp.getSHour() &&
							cellMinute == temp.getSMin()){
							dayTable.setValueAt(temp.getName(), i, 2);
							hasTaskorEvent = true;
							ctr = temp.getEHour() - temp.getSHour();

							if(temp.getSMin() - temp.getEMin() == 0 ){
								ctr = i + (ctr*2)-1;
							}
							else if(temp.getSMin() - temp.getEMin() == -30  ){
								ctr = i + (ctr*2);
							}
							else if (temp.getSMin() - temp.getEMin() == 30  ){
								ctr = i + (ctr*2)-2;
							}
							
						}
					}
				}
			}
			else{
				for(int i=0 ; i<48; i++){
					int ctr = 0;
					Object cellHour = dayTable.getValueAt(i, 0);
					Object cellMinute = dayTable.getValueAt(i, 1);

					cellHour = Integer.parseInt((String) cellHour); 
					cellMinute = Integer.parseInt((String) cellMinute); 

					for(int j=0; j<5;j++){
						for(/*Appointment temp : listofAppointmentsDoc3){
							if( month == temp.getMonth() && day == temp.getDay()+j &&
								year == temp.getYear() &&
								cellHour ==  temp.getSHour() && cellMinute == temp.getSMin()){

								dayTable.setValueAt(temp.getName(), i, 2+j);
								hasTaskorEvent = true;
								ctr = temp.getEHour() - temp.getSHour();

								if(temp.getSMin() - temp.getEMin() == 0 ){
									ctr = i + (ctr*2)-1;
								}
								else if(temp.getSMin() - temp.getEMin() == -30  ){
									ctr = i + (ctr*2);
								}
								else if (temp.getSMin() - temp.getEMin() == 30  ){
									ctr = i + (ctr*2)-2;
								}
									
								//ctr to be used for the color stuff

							}
						}
					}
				}
			}
		}

	}*/



}


