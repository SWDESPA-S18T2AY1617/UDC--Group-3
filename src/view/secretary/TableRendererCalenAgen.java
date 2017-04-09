package view.secretary;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.ArrayList;

public class TableRendererCalenAgen extends DefaultTableCellRenderer
{   
    private ArrayList<Integer> listofGreen = new ArrayList<Integer>();
    private ArrayList<Integer> listofRed = new ArrayList<Integer>();

    private Boolean calendarORagenda;
    private Boolean dailyORweekly;
    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
            Component cellComp = super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        
        setBackground(new Color(70, 70, 70));
        setForeground(new Color(31, 31, 31));

       if( listofRed.size() + listofGreen.size() !=0){
            if(dailyORweekly){
                for(int tmp : listofGreen)
                    if(tmp == row)
                        if(calendarORagenda){
                            if(column == 2)
                                setBackground(Color.GREEN);
                        }
                        else
                            setForeground(Color.GREEN);
                for(int tmp : listofRed)
                    if(tmp == row)
                        if(calendarORagenda){
                            if(column == 2)
                                setBackground(Color.RED);
                        }
                        else
                            setForeground(Color.RED);
            }
            else{ // !dailyORweekly
                int i = 0;
                while(i<listofGreen.size()){
                    if(1<column){
                        if(listofGreen.get(i)  == row && listofGreen.get(i+1) == column)
                            if(calendarORagenda)
                                setBackground(Color.GREEN);
                            else
                                setForeground(Color.GREEN);
                    }
                    i+=2;
                }
                    
                i = 0;
                while(i<listofRed.size()){
                    if(1<column){
                        if(listofRed.get(i)  == row && listofRed.get(i+1) == column)
                            if(calendarORagenda)
                                setBackground(Color.RED);
                            else
                                setForeground(Color.RED);
                    }
                    i+=2;
                }

            }
        }

        setBorder(null);
        return cellComp;  
    }
    public void addRed(int redRow){
        listofRed.add(redRow);
    }
    public void addGreen(int greenRow){
        listofGreen.add(greenRow);
    }
    public void setDailyorWeekly(Boolean setting){
        dailyORweekly = setting;
    }
    public void setCalendarorAgenda(Boolean setting){
        calendarORagenda = setting;
    }

}