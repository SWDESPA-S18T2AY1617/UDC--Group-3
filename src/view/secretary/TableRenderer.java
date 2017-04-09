package view.secretary;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableRenderer extends DefaultTableCellRenderer
{
    private int cellHighlight;

    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
            Component cellComp = super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            setBackground(new Color(70, 70, 70));


            if(cellHighlight!=0 && value!=null){
            	if(cellHighlight == Integer.parseInt(value.toString()))
            		setBackground(new Color(179, 236, 255));
            }

            setBorder(null);
            setForeground(new Color(32, 194, 14));
            return cellComp;  
    }
   public void setHighlight(int cell){
    	cellHighlight = cell;
    }
}
