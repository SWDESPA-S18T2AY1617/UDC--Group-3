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



public class TableRendererAgenda extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column)
    {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            
            if(column == 0 || column == 1) {
            	setText((String)value);
            	setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
            	setForeground(Color.BLACK);
            	setFont(new Font("Sans Serif", Font.PLAIN, 16));
            	if(column == 0) {
            		setFont(new Font("Sans Serif", Font.BOLD, 16));
            		setForeground(Color.BLUE);
            	}
            } else if (column == 2){
            	setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
            	if(value instanceof TestAppointment) {
            		TestAppointment t = (TestAppointment)value;
            		setText(t.getName());
            		setForeground(t.getColor());
            		setFont(new Font("Sans Serif", Font.BOLD, 16));
            	}
            }
            
            setVerticalAlignment(DefaultTableCellRenderer.CENTER);
            return this;  
    }
	
	public void setForeground(Color c) {
		super.setForeground(c);
	}
}
	