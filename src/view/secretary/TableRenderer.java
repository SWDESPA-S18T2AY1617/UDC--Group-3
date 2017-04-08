package view.secretary;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
//import java.util.ArrayList;
public class TableRenderer extends DefaultTableCellRenderer
{
    private int cellHighlight;

 /*   private ArrayList<Integer> listofRed = new ArrayList<Integer>();
    private ArrayList<Integer> listofGreen = new ArrayList<Integer>();
    private ArrayList<Integer> listofBlue = new ArrayList<Integer>();
*/

    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
            Component cellComp = super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            setBackground(new Color(70, 70, 70));


            if(cellHighlight!=0 && value!=null){
            	if(cellHighlight == Integer.parseInt(value.toString()))
            		setBackground(new Color(179, 236, 255));
            }

    /*       if(isDay && listofRed.size() + listofGreen.size() + listofBlue.size() + listofYellow.size()!=0){
                if(column==2){
                    for(int tmp : listofRed){
                        if(tmp == row){
                            setBackground(Color.RED);
                        }
                    }
                    for(int tmp : listofGreen){
                        if(tmp == row){
                            setBackground(Color.GREEN);
                        }
                    }
                    for(int tmp : listofBlue){
                        if(tmp == row){
                            setBackground(Color.BLUE);
                        }
                    }
                    for(int tmp : listofYellow){
                        if(tmp == row){
                            setBackground(Color.YELLOW);
                        }
                    }
                }
            }*/

            setBorder(null);
            setForeground(new Color(32, 194, 14));
            return cellComp;  
    }
   public void setHighlight(int cell){
    	cellHighlight = cell;
    }
   /*  public void setCalendar(Boolean setting){
        isCalendar= setting;
    }
    public void setDay(Boolean setting){
        isDay = setting;
    }
    public void addRed(int redRow){
        listofRed.add(redRow);
    }
    public void addGreen(int greenRow){
        listofGreen.add(greenRow);
    }
    public void addBlue(int blueRow){
        listofBlue.add(blueRow);
    }
    public void addYellow(int yellowRow){
        listofYellow.add(yellowRow);
    }*/


}
