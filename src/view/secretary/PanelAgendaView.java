package view.secretary;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.border.Border;

public class PanelAgendaView extends JPanel{
	private SecretaryController sc;
	private JScrollPane scrollAgenda;
	private JTable agendaTable;
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

	public PanelAgendaView(SecretaryController sc){
		this.sc = sc;
		
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
		agendaTable = new JTable(modelCalendarTable);
		scrollAgenda = new JScrollPane(agendaTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		masterTable = new TableRenderer();

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
		modelCalendarTable.addColumn("Reservations for, "+ month + " / " + day + " / " + year);
		modelCalendarTable.setColumnCount(1);
		modelCalendarTable.setRowCount(50);
		
        tcm.getColumn(0).setPreferredWidth(1000);


		agendaTable.setDefaultRenderer(agendaTable.getColumnClass(0), masterTable);
		this.setTableBlankDaily();
	}
	public void setToWeekly(){

		dailyORweekly = false;
		modelCalendarTable.setColumnCount(0);
        modelCalendarTable.addColumn("Reservations for Monday, "+ month + " / " + day + " / " + year);
        modelCalendarTable.addColumn("Reservations for Tuesday, "+ month + " / " + (day+1) + " / " + year);
        modelCalendarTable.addColumn("Reservations for Wednesday, "+ month + " / " + (day+2) +" / " + year);
        modelCalendarTable.addColumn("Reservations for Thursday, "+ month + " / " + (day+3) + " / " + year);
        modelCalendarTable.addColumn("Reservations for Friday, "+ month + " / " + (day+4) + " / " + year);
		modelCalendarTable.setColumnCount(5);
		modelCalendarTable.setRowCount(50);
		
        tcm.getColumn(0).setPreferredWidth(300);
		tcm.getColumn(1).setPreferredWidth(300);
		tcm.getColumn(2).setPreferredWidth(300);
		tcm.getColumn(3).setPreferredWidth(300);
		tcm.getColumn(4).setPreferredWidth(300);

		agendaTable.setDefaultRenderer(agendaTable.getColumnClass(0), masterTable);
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

	public void updateAgendaView(){
		if(displayDoc1){
			if(dailyORweekly){

			}
			else{

			}

		}
		if(displayDoc2){
			if(dailyORweekly){

			}
			else{
				
			}
		}	
		if(displayDoc3){
			if(dailyORweekly){

			}
			else{
				
			}
		}


	}

}


