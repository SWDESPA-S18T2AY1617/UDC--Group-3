package view.secretary;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import controller.SecretaryController;

import javax.swing.border.Border;

public class PanelMiniCalendar extends JPanel{
	private SecretaryController vc;

    private JLabel monthLabel, 
    			   yearLabel,
				   lblViewHeader;

	private JScrollPane scrollCalendarTable;
	private JTable calendarTable;
    private DefaultTableModel modelCalendarTable;

    private JButton btnPrev, 
		 		   btnNext,
		 		   btnBook;

    private JCheckBox chckDoc1,
    				  chckDoc2,
    				  chckDoc3;

    private int yearBound, 
			   monthBound, 
			   dayBound,
			   cellDay;
	private Boolean highlight;

	public PanelMiniCalendar(SecretaryController secretaryController){
		this.vc = secretaryController;

        this.setSize( 350, 500);
		this.setLayout(null);
		this.setBackground(new Color(31, 31, 31));
		this.setBorder(BorderFactory.createTitledBorder(""));

		this.initParts();
		this.addsetParts();
		this.addActionListeners();

	}
		
	public void initParts(){
		

		monthLabel = new JLabel ("what");
		yearLabel = new JLabel ("what");
		btnPrev = new JButton ("<");
		btnNext = new JButton (">");
		btnBook = new JButton ("Book Appointment");
		chckDoc1 = new JCheckBox("DOCTOR 1 GOES HERE ");
		chckDoc2 = new JCheckBox("DOCTOR 2 GOES HERE ");
		chckDoc3 = new JCheckBox("DOCTOR 3 GOES HERE ");

		highlight = true;
		cellDay = dayBound;

		monthLabel.setForeground(new Color(120, 120, 120));
		yearLabel.setForeground(new Color(120, 120, 120));
		
		btnPrev.setBorderPainted(false);
		btnPrev.setOpaque(true);
		btnPrev.setContentAreaFilled(false);
		btnPrev.setBorderPainted(false);
		btnPrev.setForeground(new Color(120, 120, 120));

		btnNext.setBorderPainted(false);
		btnNext.setOpaque(true);
		btnNext.setContentAreaFilled(false);
		btnNext.setBorderPainted(false);
		btnNext.setForeground(new Color(120, 120, 120));

		btnBook.setBorderPainted(true);
		btnBook.setOpaque(false);
		btnBook.setContentAreaFilled(false);
		btnBook.setForeground(new Color(120, 120, 120));
		btnBook.setFont(new Font("Charlemagne STD", Font.BOLD, 20));
		
		modelCalendarTable = new DefaultTableModel(){
        	public boolean isCellEditable(int rowIndex, int mColIndex){
                   return false;
            }
        };
		calendarTable = new JTable(modelCalendarTable);
		scrollCalendarTable = new JScrollPane(calendarTable);
        
		GregorianCalendar cal = new GregorianCalendar();
		dayBound = cal.get(GregorianCalendar.DAY_OF_MONTH);
		monthBound = cal.get(GregorianCalendar.MONTH);
		yearBound = cal.get(GregorianCalendar.YEAR);

		calendarTable.setShowGrid(true);
		calendarTable.getParent().setBackground(calendarTable.getBackground()); //Set background

		calendarTable.getTableHeader().setResizingAllowed(false);
		calendarTable.getTableHeader().setReorderingAllowed(false);

		calendarTable.setColumnSelectionAllowed(true);
		calendarTable.setRowSelectionAllowed(true);
		calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		calendarTable.setRowHeight(30);

		String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
		for (int i=0; i<7; i++){
			modelCalendarTable.addColumn(headers[i]);
		}
		modelCalendarTable.setColumnCount(7);
		modelCalendarTable.setRowCount(6);

		lblViewHeader = new JLabel("Doctors:");
		lblViewHeader.setFont(new Font("Charlemagne STD", Font.BOLD, 15));
		lblViewHeader.setForeground(new Color(120, 120, 120));

		chckDoc1.setOpaque(false);
		chckDoc1.setContentAreaFilled(false);
		chckDoc1.setForeground(new Color(120, 120, 120));

    	chckDoc2.setOpaque(false);
		chckDoc2.setContentAreaFilled(false);
    	chckDoc2.setForeground(new Color(120, 120, 120));
    	
    	chckDoc3.setOpaque(false);
		chckDoc3.setContentAreaFilled(false);
    	chckDoc3.setForeground(new Color(120, 120, 120));
		
		refreshCalendar(monthBound, yearBound);
		vc.setViewDate(monthBound+1, dayBound, yearBound); 
	}

	public void addsetParts(){
		
		this.add(monthLabel);
		this.add(yearLabel);
		this.add(btnPrev);
		this.add(btnNext);
		this.add(btnBook);
		this.add(scrollCalendarTable);
		this.add(lblViewHeader);
		this.add(chckDoc1);
    	this.add(chckDoc2);
    	this.add(chckDoc3);

		monthLabel.setBounds(70, 10, 100, 40);
		yearLabel.setBounds(20, 10, 50, 40);
		btnPrev.setBounds(240, 20, 50, 25);
		btnNext.setBounds(280, 20, 50, 25);
		btnBook.setBounds(20, 420, 300, 60);
		scrollCalendarTable.setBounds(20, 50, 300, 200);
		lblViewHeader.setBounds(20, 270, 200, 50);
		chckDoc1.setBounds(40, 300, 200, 40);
		chckDoc2.setBounds(40, 330, 200, 40);
		chckDoc3.setBounds(40, 360, 200, 40);


	}
	public void addActionListeners(){
		btnPrev.addActionListener(new btnPrevAction());
		btnNext.addActionListener(new btnNextAction());
		btnBook.addActionListener(new btnBookAction());
		calendarTable.addMouseListener(new dateSelectAction());
		chckDoc1.addItemListener(new chckDoc1Action());
		chckDoc2.addItemListener(new chckDoc2Action());
     	chckDoc3.addItemListener(new chckDoc3Action());

	}
	public void sendDateToView(){
		vc.setViewDate(monthBound+1, dayBound, yearBound);
	}
	public void refreshCalendar(int month, int year){
    	
		String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		int nod, som, i, j;
			
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		
		if(month == 0 && year <= yearBound-10)
          btnPrev.setEnabled(false);
		if(month == 11 && year >= yearBound+100)
          btnNext.setEnabled(false);
                
		monthLabel.setText(months[month]);
        yearLabel.setText(""+year);
		//cmbYear.setSelectedItem(""+year);
		
		for (i = 0; i < 6; i++)
			for (j = 0; j < 7; j++)
				modelCalendarTable.setValueAt(null, i, j);
		
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);
		
		for (i = 1; i <= nod; i++){
			int row = new Integer((i+som-2)/7);
			int column  =  (i+som-2)%7;
			modelCalendarTable.setValueAt(i, row, column);
		}
		
		TableRenderer temptable = new TableRenderer();

        if(highlight){
        	temptable.setHighlight(cellDay);
        	highlight = false;
        }

		calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), temptable);
		
	}

	class btnPrevAction implements ActionListener{
		public void actionPerformed (ActionEvent e) {
			if (monthBound == 0){
				monthBound = 11;
				yearBound -= 1;
			}
			else{
				monthBound -= 1;
			}
			refreshCalendar(monthBound, yearBound);
		}
	}
	class btnNextAction implements ActionListener{
		public void actionPerformed (ActionEvent e){
			if (monthBound == 11){
				monthBound = 0;
				yearBound += 1;
			}
			else{
				monthBound += 1;
			}
			refreshCalendar(monthBound, yearBound);
		}
	}
	class btnBookAction implements ActionListener{
		public void actionPerformed (ActionEvent e){
			vc.setBookingPanel();
		}
	}
	class dateSelectAction implements MouseListener{
    	public void mouseClicked(MouseEvent evt){
    		
    	   final int col = calendarTable.getSelectedColumn();  
           final int row = calendarTable.getSelectedRow();
           final Object cellVal = calendarTable.getValueAt(row, col);

    		if(evt.getClickCount() == 1){
    		  try{
	    		   highlight = true;
	    		   cellDay = Integer.parseInt(cellVal.toString());
	               refreshCalendar(monthBound, yearBound);
	               vc.setViewDate(monthBound+1, cellDay, yearBound);
	           }
	           catch(NullPointerException e){
	           		System.out.println("No");
	           }
            }
    	}
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

    }
    class chckDoc1Action implements ItemListener{
    	public void itemStateChanged(ItemEvent evt){
    		if(evt.getStateChange() == ItemEvent.SELECTED){
				vc.setDoc1(true);
			}
			else{
				vc.setDoc1(false);
			}
		}
    }
    class chckDoc2Action implements ItemListener{
    	public void itemStateChanged(ItemEvent evt){
			if(evt.getStateChange() == ItemEvent.SELECTED){
				vc.setDoc2(true);
			}
			else{
				vc.setDoc2(false);
			}
		}
    }
    class chckDoc3Action implements ItemListener{
    	public void itemStateChanged(ItemEvent evt){
			if(evt.getStateChange() == ItemEvent.SELECTED){
				vc.setDoc3(true);
			}
			else{
				vc.setDoc3(false);
			}
		}
    }

}
