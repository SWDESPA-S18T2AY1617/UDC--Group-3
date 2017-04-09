/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.doctor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import model.Appointment;



public class TableRendererAgenda extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column)
    {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            
            System.out.println("TABLERENDERERAGENDA =============== " + column);
            if(column == 0 || column == 1) {
            	setText((String)value);
            	setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
            	setForeground(Color.BLACK);
            	setFont(new Font("Sans Serif", Font.PLAIN, 16));
            	if(column == 0) {
            		setFont(new Font("Sans Serif", Font.BOLD, 16));
            		setForeground(Color.BLUE);
            	}
            } else if (column == 2 || column == 3){
            	setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
            	if(value instanceof Appointment) {
            		System.out.println("This is an appointment");
            		Appointment t = (Appointment)value;
            		if(column == 2) {
            			System.out.println("COLUMN 222222222222222222222222222222222222222222222222");
            			setText("Dr. " + t.getDoctorName());
            			if(!t.isAvailable())
            				table.setValueAt(t.getClientName(), row, column + 1);
            		}
            		
            		
            		if(t.isAvailable())
            			setForeground(ColorParser.getColor(PanelDay.COLOR_AVAILABLE));
            		else {
            			setForeground(ColorParser.getColor(PanelDay.COLOR_TAKEN));
            		}
            		//setFont(new Font("Sans Serif", Font.BOLD, 16));
            	}
            }
            if(column == 3) {
            	System.out.println("COLUMN 333333333333333333333333333333333333333333333333");
				setForeground(ColorParser.getColor(PanelDay.COLOR_TAKEN));
            }
            setFont(new Font("Sans Serif", Font.BOLD, 16));
            setVerticalAlignment(DefaultTableCellRenderer.CENTER);
            return this;  
    }
	
	public void setForeground(Color c) {
		super.setForeground(c);
	}
}
	