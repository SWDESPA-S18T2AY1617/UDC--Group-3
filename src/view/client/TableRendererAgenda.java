/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.client;

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
           
            if(value instanceof String[]) {
            	String[] colTextArr = (String[]) value;
            	if(column == 0) {
            		setText(colTextArr[1]);
	            	setForeground(Color.BLACK);
	            	setFont(new Font("Sans Serif", Font.PLAIN, 16));
	            	setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
	            }
	            else if(column == 1) {
	            	setText(colTextArr[0]);
	            	setForeground(ColorParser.getColor(colTextArr[2]));
	            	setFont(new Font("Sans Serif", Font.BOLD, 16));
	            	setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
	            }
            }
            setVerticalAlignment(DefaultTableCellRenderer.CENTER);
            return this;  
    }
}
	