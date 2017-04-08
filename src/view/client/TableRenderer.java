/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.client;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Arturo III
 */
public class TableRenderer extends DefaultTableCellRenderer {
	
	public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column)
    {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            if (selected)
                setBackground( table.getSelectionBackground() );
            else
                setBackground(Color.WHITE);
            /*
            if (selected)
                setBackground( table.getSelectionBackground() );
            else
                setBackground( table.getBackground() );
            */
                        
            setBorder(null);
            setForeground(Color.black);
            setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            setVerticalAlignment(DefaultTableCellRenderer.CENTER);
            return this;  
    }
}
