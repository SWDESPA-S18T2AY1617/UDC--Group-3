package view.secretary;

import controller.SecretaryController;
import model.Appointment;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.border.Border;

public class PanelCalendarView extends JPanel{

	private SecretaryController vc;

	private JScrollPane scrollCalendar;
	private JTable calendarTable;
    private DefaultTableModel modelCalendarTable;
    private TableColumnModel tcm;
    private TableRendererCalenAgen masterTable;

    private int month,
    			day,
    			year;

    private String doctor1,
    			   doctor2,
    			   doctor3;
    private Boolean displayDoc1,
    				displayDoc2,
    				displayDoc3,
    				dailyORweekly;
	public PanelCalendarView(SecretaryController vc, ArrayList<String> namesofDoctors){

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
		calendarTable = new JTable(modelCalendarTable);
		scrollCalendar = new JScrollPane(calendarTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		masterTable = new TableRendererCalenAgen();
		
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
        modelCalendarTable.addColumn("Reservations for Monday");
        modelCalendarTable.addColumn("Reservations for Tuesday");
        modelCalendarTable.addColumn("Reservations for Wednesday");
        modelCalendarTable.addColumn("Reservations for Thursday");
        modelCalendarTable.addColumn("Reservations for Friday");
		modelCalendarTable.setColumnCount(7);
		modelCalendarTable.setRowCount(48);
		
        tcm.getColumn(0).setPreferredWidth(25);
		tcm.getColumn(1).setPreferredWidth(25);
		tcm.getColumn(2).setPreferredWidth(400);
		tcm.getColumn(3).setPreferredWidth(400);
		tcm.getColumn(4).setPreferredWidth(400);
		tcm.getColumn(5).setPreferredWidth(400);
		tcm.getColumn(6).setPreferredWidth(400);

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
	
	public void updateCalendarView(Iterator<Appointment> listofAppointments){
		
		masterTable = new TableRendererCalenAgen();
		masterTable.setCalendarorAgenda(true);

		while(listofAppointments.hasNext()){

			Appointment app = listofAppointments.next();

			if(displayDoc1 && app.getDoctorName().equals(doctor1)){

				if(dailyORweekly){// true = daily ; false = weekly
					masterTable.setDailyorWeekly(true);
					for(int i=0 ; i<48; i++){
						int ctr = 0;
						Object cellHour = calendarTable.getValueAt(i, 0);
						Object cellMinute = calendarTable.getValueAt(i, 1);

						cellHour = Integer.parseInt((String) cellHour); 
						cellMinute = Integer.parseInt((String) cellMinute); 

						if( month == app.getMonth() && day == app.getDay() && year == app.getYear() &&
							(int)cellHour ==  app.getStartHour() && (int)cellMinute == app.getStartMinute()){
							calendarTable.setValueAt(app.getDoctorName(), i, 2);
							ctr = app.getEndHour() - app.getStartHour();

							if(app.getStartMinute() - app.getEndMinute() == 0 ){
								ctr = i + (ctr*2)-1;
							}
							else if(app.getStartMinute() - app.getEndMinute() == -30  ){
								ctr = i + (ctr*2);
							}
							else if (app.getStartMinute() - app.getEndMinute() == 30  ){
								ctr = i + (ctr*2)-2;
							}

							if(app.isAvailable()){
								for(int j=i; j<ctr; j++)
									masterTable.addGreen(j);
							}
							else{
								for(int j=i; j<ctr; j++)
									masterTable.addRed(j);
							}

							
						}
						
					}
				}
				else{
					masterTable.setDailyorWeekly(false);
					for(int i=0 ; i<48; i++){
						int ctr = 0;
						Object cellHour = calendarTable.getValueAt(i, 0);
						Object cellMinute = calendarTable.getValueAt(i, 1);

						cellHour = Integer.parseInt((String) cellHour); 
						cellMinute = Integer.parseInt((String) cellMinute); 

						for(int k=0; k<5; k++){
							if( month == app.getMonth() && day+k == app.getDay() && year == app.getYear() &&
								(int)cellHour ==  app.getStartHour() && (int)cellMinute == app.getStartMinute()){
								calendarTable.setValueAt(app.getDoctorName(), i, 2);
								ctr = app.getEndHour() - app.getStartHour();

								if(app.getStartMinute() - app.getEndMinute() == 0 ){
									ctr = i + (ctr*2)-1;
								}
								else if(app.getStartMinute() - app.getEndMinute() == -30  ){
									ctr = i + (ctr*2);
								}
								else if (app.getStartMinute() - app.getEndMinute() == 30  ){
									ctr = i + (ctr*2)-2;
								}

								if(app.isAvailable()){
									for(int j=i; j<ctr; j++){
										masterTable.addGreen(j);
										masterTable.addGreen(k);
									}

								}
								else{
									for(int j=i; j<ctr; j++){
										masterTable.addRed(j);
										masterTable.addRed(k);
									}
								}

								
							}
						}
						
					}
				}
			}
				

			if(displayDoc2 && app.getDoctorName().equals(doctor2)){
				if(dailyORweekly){// true = daily ; false = weekly
					masterTable.setDailyorWeekly(true);
					for(int i=0 ; i<48; i++){
						int ctr = 0;
						Object cellHour = calendarTable.getValueAt(i, 0);
						Object cellMinute = calendarTable.getValueAt(i, 1);

						cellHour = Integer.parseInt((String) cellHour); 
						cellMinute = Integer.parseInt((String) cellMinute); 

						if( month == app.getMonth() && day == app.getDay() && year == app.getYear() &&
							(int) cellHour ==  app.getStartHour() && (int)cellMinute == app.getStartMinute()){
							calendarTable.setValueAt(app.getDoctorName(), i, 2);
							ctr = app.getEndHour() - app.getStartHour();

							if(app.getStartMinute() - app.getEndMinute() == 0 ){
								ctr = i + (ctr*2)-1;
							}
							else if(app.getStartMinute() - app.getEndMinute() == -30  ){
								ctr = i + (ctr*2);
							}
							else if (app.getStartMinute() - app.getEndMinute() == 30  ){
								ctr = i + (ctr*2)-2;
							}

							if(app.isAvailable()){
								for(int j=i; j<ctr; j++)
									masterTable.addGreen(j);
							}
							else{
								for(int j=i; j<ctr; j++)
									masterTable.addRed(j);
							}

							
						}
						
					}
				}
				else{
					masterTable.setDailyorWeekly(false);
					for(int i=0 ; i<48; i++){
						int ctr = 0;
						Object cellHour = calendarTable.getValueAt(i, 0);
						Object cellMinute = calendarTable.getValueAt(i, 1);

						cellHour = Integer.parseInt((String) cellHour); 
						cellMinute = Integer.parseInt((String) cellMinute); 

						for(int k=0; k<5; k++){
							if( month == app.getMonth() && day == app.getDay() && year == app.getYear() &&
									(int) cellHour ==  app.getStartHour() && (int)cellMinute == app.getStartMinute()){
									calendarTable.setValueAt(app.getDoctorName(), i, 2);
									ctr = app.getEndHour() - app.getStartHour();

									if(app.getStartMinute() - app.getEndMinute() == 0 ){
										ctr = i + (ctr*2)-1;
									}
									else if(app.getStartMinute() - app.getEndMinute() == -30  ){
										ctr = i + (ctr*2);
									}
									else if (app.getStartMinute() - app.getEndMinute() == 30  ){
										ctr = i + (ctr*2)-2;
									}

								if(app.isAvailable()){
									for(int j=i; j<ctr; j++){
										masterTable.addGreen(j);
										masterTable.addGreen(k);
									}

								}
								else{
									for(int j=i; j<ctr; j++){
										masterTable.addRed(j);
										masterTable.addRed(k);
									}
								}

								
							}
						}
						
					}
				}
			}
			if(displayDoc3 && app.getDoctorName().equals(doctor3)){
				if(dailyORweekly){// true = daily ; false = weekly
					masterTable.setDailyorWeekly(true);
					for(int i=0 ; i<48; i++){
						int ctr = 0;
						Object cellHour = calendarTable.getValueAt(i, 0);
						Object cellMinute = calendarTable.getValueAt(i, 1);

						cellHour = Integer.parseInt((String) cellHour); 
						cellMinute = Integer.parseInt((String) cellMinute); 

						if( month == app.getMonth() && day == app.getDay() && year == app.getYear() &&
								(int) cellHour ==  app.getStartHour() && (int)cellMinute == app.getStartMinute()){
								calendarTable.setValueAt(app.getDoctorName(), i, 2);
								ctr = app.getEndHour() - app.getStartHour();

								if(app.getStartMinute() - app.getEndMinute() == 0 ){
									ctr = i + (ctr*2)-1;
								}
								else if(app.getStartMinute() - app.getEndMinute() == -30  ){
									ctr = i + (ctr*2);
								}
								else if (app.getStartMinute() - app.getEndMinute() == 30  ){
									ctr = i + (ctr*2)-2;
								}

							if(app.isAvailable()){
								for(int j=i; j<ctr; j++)
									masterTable.addGreen(j);
							}
							else{
								for(int j=i; j<ctr; j++)
									masterTable.addRed(j);
							}

							
						}
						
					}
				}
				else{
					masterTable.setDailyorWeekly(false);
					for(int i=0 ; i<48; i++){
						int ctr = 0;
						Object cellHour = calendarTable.getValueAt(i, 0);
						Object cellMinute = calendarTable.getValueAt(i, 1);

						cellHour = Integer.parseInt((String) cellHour); 
						cellMinute = Integer.parseInt((String) cellMinute); 

						for(int k=0; k<5; k++){
							if( month == app.getMonth() && day == app.getDay() && year == app.getYear() &&
									(int) cellHour ==  app.getStartHour() && (int)cellMinute == app.getStartMinute()){
									calendarTable.setValueAt(app.getDoctorName(), i, 2);
									ctr = app.getEndHour() - app.getStartHour();

									if(app.getStartMinute() - app.getEndMinute() == 0 ){
										ctr = i + (ctr*2)-1;
									}
									else if(app.getStartMinute() - app.getEndMinute() == -30  ){
										ctr = i + (ctr*2);
									}
									else if (app.getStartMinute() - app.getEndMinute() == 30  ){
										ctr = i + (ctr*2)-2;
									}

								if(app.isAvailable()){
									for(int j=i; j<ctr; j++){
										masterTable.addGreen(j);
										masterTable.addGreen(k);
									}

								}
								else{
									for(int j=i; j<ctr; j++){
										masterTable.addRed(j);
										masterTable.addRed(k);
									}
								}

								
							}
						}
						
					}
				}
			}



		}
		

	}



}


